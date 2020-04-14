package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.SnowFlake;
import aaron.user.api.dto.*;
import aaron.user.service.biz.dao.RoleDao;
import aaron.user.service.biz.service.RoleResourceService;
import aaron.user.service.biz.service.RoleService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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
        if (baseMapper.update(role) == 1){
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
    @Override
    public boolean delete(List<RoleDto> roleDtoList) {
        List<Role> roleList = CommonUtils.convertList(roleDtoList,Role.class);
        for (Role role : roleList) {
            role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        if (baseMapper.selectByIdList(roleList) > 0){
            throw new UserException(UserError.ROLE_IS_IN_USE);
        }
        if (removeByIds(roleList)){
            return true;
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
        role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        return baseMapper.query(role);
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
        List<RoleResource> resourceList = CommonUtils.convertList(resourceDtos,RoleResource.class);
        List<RoleResource> roles = resourceList.stream().filter(distinctByKey(RoleResource::getRoleId)).collect(Collectors.toList());
        for (RoleResource role : roles) {
            baseMapper.deleteResourceForRole(role);
        }
        if (resourceList.size() != 0){
            for (RoleResource resource : resourceList) {
                resource.setId(snowFlake.nextId());
            }
            if (roleResourceService.saveBatch(roles)){
                return true;
            }
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public List<UserForRole> queryUserRole(Role role) {
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
