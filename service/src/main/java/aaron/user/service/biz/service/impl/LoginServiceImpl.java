package aaron.user.service.biz.service.impl;

import aaron.common.data.common.CacheConstants;
import aaron.common.data.exception.StarterError;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.SnowFlake;
import aaron.common.utils.TokenUtils;
import aaron.common.utils.jwt.JwtUtil;
import aaron.common.utils.jwt.UserPermission;
import aaron.user.api.dto.UserDto;
import aaron.user.service.biz.dao.UserDao;
import aaron.user.service.biz.dao.UserOnlineInfoDao;
import aaron.user.service.biz.service.LoginService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.User;
import aaron.user.service.pojo.model.UserOnlineInfo;
import aaron.user.service.pojo.vo.UserInfo;
import aaron.user.service.pojo.vo.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-13
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserDao userDao;

    @Resource
    UserOnlineInfoDao userOnlineInfoDao;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    SnowFlake snowFlake;
    /**
     * 登录
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    @Override
    public String login(UserDto userDTO){
        User user= CommonUtils.copyProperties(userDTO,User.class);
        // 查询该用户
        UserPermission userPermission = userDao.checkUser(user);
        if (userPermission == null){
            throw new UserException(UserError.USER_OR_PASSWORD_ERROR);
        }
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(userDTO,UserOnlineInfo.class);
        userOnlineInfo.setId(snowFlake.nextId());
        if (userPermission.getId() != null){
            userOnlineInfo.setUserId(userPermission.getId());
        }
        userOnlineInfo.setName(userPermission.getUserName());
        userOnlineInfo.setOnlineTime(new Date());
        userOnlineInfo.setStatus((byte) 1);
        // 增加在线用户
        userOnlineInfoDao.insert(userOnlineInfo);
        userPermission.setUserOnlineId(userOnlineInfo.getId());
        String token = JwtUtil.createJwt(userPermission);
        Cache userPermissionCache = cacheManager.getCache(CacheConstants.USER_PERMISSION);
        // 根据userId查询是否已经有缓存
        Cache.ValueWrapper valueWrapper = userPermissionCache.get(userPermission.getId());
        if (valueWrapper != null){
            List<Long> ids = new ArrayList<>();
            ids.add(userPermission.getId());
            logout(ids);
        }
        // 存token
        userPermissionCache.put(userPermission.getId(),userPermission);
        return token;
    }

    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo getUserInfo(){
        UserPermission userPermission = TokenUtils.getUser();
        return userDao.getUserInfo(userPermission);
    }

    /**
     * 更新用户面板
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UserMenu> getUserMenu(){
        UserPermission userPermission = TokenUtils.getUser();
        return userDao.getUserMenu(userPermission);
    }

    /**
     * 登出
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public boolean logout(List<Long> ids){
        Cache cache = cacheManager.getCache(CacheConstants.TOKEN);
        Cache resourceCache = cacheManager.getCache(CacheConstants.RESOURCE_MAP);
        for (Long id : ids) {
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper != null){
                resourceCache.evict(id);
                UserPermission userPermission;
                try {
                    userPermission = JwtUtil.parseJwt(String.valueOf(valueWrapper.get()));
                } catch (Exception e) {
                    throw new UserException(StarterError.SYSTEM_TOKEN_PARSE_ERROR);
                }
                cache.evict(id);
                UserOnlineInfo userOnlineInfo = new UserOnlineInfo();
                userOnlineInfo.setId(userPermission.getUserOnlineId());
                userOnlineInfo.setUserId(userPermission.getId());
                userOnlineInfo.setStatus((byte) 0);
                userOnlineInfo.setOfflineTime(new Date());
                userOnlineInfoDao.updateById(userOnlineInfo);
            }
        }
        return true;
    }
}
