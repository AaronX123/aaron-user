package aaron.user.service.biz.service.impl;

import aaron.common.utils.CommonUtils;
import aaron.user.service.biz.dao.UserRoleDao;
import aaron.user.service.biz.service.UserRoleService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
    @Override
    public List<UserRole> listByRoleId(List<Long> roleId) {
        return baseMapper.listByRoleIdList(roleId);
    }

    @Override
    public List<UserRole> listByUserId(List<Long> userId) {
        return baseMapper.listByUserIdList(userId);
    }

    @Override
    public UserRole selectByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public UserRole selectByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }
}
