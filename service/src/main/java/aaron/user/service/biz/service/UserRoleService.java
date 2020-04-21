package aaron.user.service.biz.service;

import aaron.user.service.pojo.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface UserRoleService extends IService<UserRole> {
    List<UserRole> listByRoleId(List<Long> roleId);

    List<UserRole> listByUserId(List<Long> userId);
}
