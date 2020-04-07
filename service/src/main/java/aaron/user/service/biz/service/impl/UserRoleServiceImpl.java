package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.UserRoleDao;
import aaron.user.service.biz.service.UserRoleService;
import aaron.user.service.pojo.model.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
}
