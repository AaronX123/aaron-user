package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.UserDao;
import aaron.user.service.biz.service.UserService;
import aaron.user.service.pojo.model.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
