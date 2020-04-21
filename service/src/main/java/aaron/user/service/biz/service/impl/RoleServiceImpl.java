package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.data.common.CacheConstants;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.SnowFlake;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.*;
import aaron.user.service.biz.dao.RoleDao;
import aaron.user.service.biz.service.*;
import aaron.user.service.common.utils.AdminUtil;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Autowired
    SnowFlake snowFlake;

    @Autowired
    RoleResourceService roleResourceService;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CompanyService companyService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @FullCommonFieldU
    @Override
    public boolean save(RoleDto roleDto) {
        Role role = CommonUtils.copyProperties(roleDto,Role.class);
        if (save(role)){
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(RoleDto roleDto) {
        Role role = CommonUtils.copyProperties(roleDto,Role.class);
        if (baseMapper.updateById(role) == 1){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    /**
     * 删除角色记录
     *
     * @param roleDtoList 角色List集合
     * @return 删除成功条数
     */
    //@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<RoleDto> roleDtoList) {
        List<Role> roleList = CommonUtils.convertList(roleDtoList,Role.class);
        for (Role role : roleList) {
            role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        // 先判断是否有人使用这个角色，如果没有就直接删除，将role_resource表记录级联删除掉
        if (baseMapper.selectByIdList(roleList.stream().map(Role::getId).collect(Collectors.toList())).size() > 0){
            throw new UserException(UserError.ROLE_IS_IN_USE);
        }
        try {
            // 删除role_resource
            roleResourceService.removeByRoleList(roleList);
            // 删除role
            int count = 0;
            for (Role role : roleList) {
                if (baseMapper.delete(role.getId())){
                    count++;
                }else {
                    throw new UserException(UserError.DEL_ROLE_FAIL,role.getName());
                }
            }
            if (count == roleList.size()){
                return true;
            }
        }catch (Exception e){
            throw new UserException(UserError.ROLE_POSSIBLY_IN_USE);
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    /**
     * 根据请求条件查询符合条件的角色记录集合
     *
     * @param role 请求条件查询信息
     * @return
     */
    @Override
    public List<Role> queryByCondition(Role role) {
        // 说明是超级管理员，直接显示所有角色
        List<Role> roleList = new ArrayList<>();
        if (AdminUtil.isSuperAdmin()){
            roleList = list();
        }else{
            role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
            roleList = baseMapper.query(role);
        }
        // 设置company和org
        Cache companyCache = cacheManager.getCache(CacheConstants.COMPANY_VAL);
        Cache orgCache = cacheManager.getCache(CacheConstants.ORG_VAL);
        for (Role role2 : roleList) {
            if (role2.getCompanyId() != null){
                Cache.ValueWrapper valueWrapper = companyCache.get(role2.getCompanyId());
                if (valueWrapper != null){
                    role2.setCompanyName((String) valueWrapper.get());
                }else {
                    String company = companyService.getNameById(role2.getCompanyId());
                    role2.setCompanyName(company);
                    companyCache.put(role2.getCompanyId(),company);
                }
            }
            if (role2.getOrgId() != null){
                Cache.ValueWrapper valueWrapper = orgCache.get(role2.getOrgId());
                if (valueWrapper != null){
                    role2.setOrgName((String) valueWrapper.get());
                }else {
                    String org = organizationService.getNameById(role2.getOrgId());
                    role2.setOrgName(org);
                    orgCache.put(role2.getOrgId(),org);
                }
            }
        }
        return roleList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUserForRole(List<RoleUserDto> roleUserDtoList) {
        List<RoleUser> roleUsers = CommonUtils.convertList(roleUserDtoList,RoleUser.class);
        try {
            baseMapper.deleteUserForRole(roleUsers.get(0));
            for (RoleUser roleUser : roleUsers) {
                roleUser.setId(snowFlake.nextId());
                baseMapper.insertUserForRole(roleUser);
            }
        }catch (Exception e){
            throw new UserException(UserError.SAVE_FAIL);
        }
        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addResourceForRole(List<RoleResourceDto> resourceDtos) {
        List<RoleResource> roles = CommonUtils.convertList(resourceDtos,RoleResource.class);
        Set<Long> idSet = roles.stream().map(RoleResource::getRoleId).collect(Collectors.toSet());
        for (long id: idSet) {
            baseMapper.deleteResourceForRole(id);
        }
        if (roles.size() != 0){
            for (RoleResource resource : roles) {
                resource.setId(snowFlake.nextId());
            }
            for (RoleResource role : roles) {
                roleResourceService.save(role);
            }
            return true;
//            if (roleResourceService.saveBatch(roles)){
//                return true;
//            }
        }
        throw new UserException(UserError.SAVE_FAIL);
    }


    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public List<UserForRole> queryUserRole(Role role) {
        if (AdminUtil.isSuperAdmin()){

            return baseMapper.queryUserForRoleSP();

        }
        return baseMapper.queryUserForRole(role);
    }

    /**
     * 查询角色分配资源的树集合
     *
     * @param roleDTO 角色信息
     * @return 以树（treelist）形式返回资源数据
     */
    @Override
    public List<TreeListDto> queryResourceForRole(RoleDto roleDTO) {
        Role role = CommonUtils.copyProperties(roleDTO,Role.class);
        List<TreeList> resourceTree = baseMapper.queryResourceForRole(role);
        return CommonUtils.convertList(resourceTree,TreeListDto.class);
    }


    /**
     * 查询角色
     *
     * @return 角色集合
     */
    @Override
    public List<UserOptionsDto> queryRole() {
        return baseMapper.queryRole();
    }
}
