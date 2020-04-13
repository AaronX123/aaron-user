package aaron.user.service.biz.service;

import aaron.user.api.dto.UserDto;
import aaron.user.service.pojo.vo.UserInfo;
import aaron.user.service.pojo.vo.UserMenu;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-13
 */
public interface LoginService {
    /**
     * 登录
     * @param userDTO
     * @return
     * @throws Exception
     */
    String login(UserDto userDTO);

    /**
     * 获取用户信息
     * @return
     * @throws Exception
     */
    UserInfo getUserInfo();

    /**
     * 更新用户面板
     * @return
     * @throws Exception
     */
    List<UserMenu> getUserMenu();

    /**
     * 登出
     * @param ids
     * @return
     * @throws Exception
     */
    boolean logout(List<Long> ids);
}
