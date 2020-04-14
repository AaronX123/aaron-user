package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.data.common.CacheConstants;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.SnowFlake;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.CompanyAndUserVo;
import aaron.user.api.dto.UserDto;
import aaron.user.api.dto.UserOptionsDto;
import aaron.user.api.dto.UserRoleDto;
import aaron.user.service.biz.dao.RoleDao;
import aaron.user.service.biz.dao.UserDao;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.biz.service.UserRoleService;
import aaron.user.service.biz.service.UserService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.model.User;
import aaron.user.service.pojo.model.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    SnowFlake snowFlake;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;


    @Transactional(rollbackFor = Exception.class)
    @FullCommonFieldU
    @Override
    public boolean save(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto,User.class);
        UserRole userRole = CommonUtils.copyProperties(user,UserRole.class);
        // 设置用户名为盐
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        // 32次散列
        String pwd = new SimpleHash("MD5",user.getPassword(),salt,32).toString();
        user.setPassword(pwd);
        try {
            save(user);
            userRole.setId(snowFlake.nextId());
            userRole.setUserId(user.getId());
            userRoleService.save(userRole);
        }catch (Exception e){
            throw new UserException(UserError.SAVE_FAIL);
        }
        return true;
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto,User.class);
        user.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        if (user.getJudgeId().equals(TokenUtils.getUser().getOrgId())){
            user.setJudgeId(null);
        }
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        String pwd = new SimpleHash("MD5",user.getPassword(),salt,32).toString();
        user.setPassword(pwd);
        if (updateById(user)){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    /**
     * 删除用户记录
     *
     * @param userDTOList 用户List集合
     * @return 删除成功条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<UserDto> userDTOList) {
        List<User> userList = CommonUtils.convertList(userDTOList,User.class);
        for (User user : userList) {
            user.setJudgeId(CommonUtils.judgeCompanyAndOrg());
            if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())){
                user.setJudgeId(null);
            }
        }
        // 先删除角色
        baseMapper.deleteRoleList(userList);
        // 再删除用户
        if (baseMapper.deleteBatchIds(userList) == userList.size()){
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    /**
     * 根据请求条件查询符合条件的用户记录集合
     *
     * @param userDto 请求条件查询信息
     * @return 符合条件的分页后的用户Map集合
     */
    @Override
    public List<User> queryByCondition(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto,User.class);
        if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())){
            user.setJudgeId(null);
        }
        return baseMapper.query(user);
    }

    /**
     * 为用户分配角色
     *
     * @param userRoleDTO 用户角色信息
     * @return 是否分配成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRoleForUser(UserRoleDto userRoleDTO) {
        try {
            removeById(userRoleDTO.getUserId());
            List<UserRole> userRoleList = new ArrayList<>();
            for (Long roleId : userRoleDTO.getRoleId()) {
                UserRole userRole = CommonUtils.copyProperties(userRoleDTO,UserRole.class);
                userRole.setId(snowFlake.nextId());
                userRole.setRoleId(roleId);
                userRoleList.add(userRole);
            }
            if (!CommonUtils.isEmpty(userRoleList)){
                userRoleService.saveBatch(userRoleList);
            }
        }catch (Exception e){
            throw new UserException(UserError.ALLOC_FAIL);
        }
        return true;
    }

    /**
     * 获取公司和用户名称 第一个为公司，第二个为用户
     * @param longList
     * @return
     */
    @Override
    public CompanyAndUserVo getUserData(List<Long> longList) {
        CompanyAndUserVo res = new CompanyAndUserVo();
        long company = longList.get(0);
        long user = longList.get(1);
        Cache companyCache = cacheManager.getCache(CacheConstants.COMPANY_VAL);
        Cache userCache = cacheManager.getCache(CacheConstants.USER_VAL);
        Cache.ValueWrapper companyWrapper = companyCache.get(company);
        if (companyWrapper == null){
            Company companyFromDatabase = companyService.getById(company);
            if (companyFromDatabase == null){
                throw new UserException(UserError.DATA_NOT_EXIST);
            }
            res.setCompany(companyFromDatabase.getName());
            companyCache.put(company,companyFromDatabase.getName());
        }else {
            res.setCompany((String) companyWrapper.get());
        }
        Cache.ValueWrapper userWrapper = userCache.get(user);
        if (userWrapper == null){
            User userFromDatabase = userService.getById(user);
            if (userFromDatabase == null){
                throw new UserException(UserError.DATA_NOT_EXIST);
            }
            res.setUser(userFromDatabase.getName());
            userCache.put(user,userFromDatabase.getName());
        }else {
            res.setUser((String) userWrapper.get());
        }
        return res;
    }

    @Override
    public String getUserName(long id) {
        Cache userCache = cacheManager.getCache(CacheConstants.USER_VAL);
        Cache.ValueWrapper userWrapper = userCache.get(id);
        if (userWrapper == null){
            User user = userService.getById(id);
            if (user == null){
                throw new UserException(UserError.DATA_NOT_EXIST);
            }
            userCache.put(id,user.getName());
            return user.getName();
        }else {
            return (String) userWrapper.get();
        }
    }

    /**
     * 通过名字查询符合条件的阅卷官信息
     *
     * @param userDto 名字及公司Id
     * @return 阅卷官记录集合
     */
    @Override
    public List<UserDto> queryScoringOfficerList(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto,User.class);
        List<User> userList = baseMapper.queryOfficerList(user);
        return CommonUtils.convertList(userList,UserDto.class);
    }

    @Override
    public Long getMostPossibleUserId(String name) {
        return baseMapper.selectIdByName(name);
    }
}
