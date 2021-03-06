package aaron.user.service.biz.service;

import aaron.user.service.biz.dao.RoleResourceDao;
import aaron.user.service.pojo.model.Role;
import aaron.user.service.pojo.model.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface RoleResourceService extends IService<RoleResource> {
    void removeByRoleList(List<Role> roleList);
}
