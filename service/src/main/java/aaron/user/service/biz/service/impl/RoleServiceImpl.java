package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.RoleDao;
import aaron.user.service.biz.service.RoleService;
import aaron.user.service.pojo.model.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
}
