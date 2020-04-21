package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.RoleResourceDao;
import aaron.user.service.biz.service.RoleResourceService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Role;
import aaron.user.service.pojo.model.RoleResource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-14
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceDao, RoleResource> implements RoleResourceService {
    @Override
    public void removeByRoleList(List<Role> roleList) {
        baseMapper.removeBatch(roleList.stream().map(Role::getId).collect(Collectors.toList()));
    }
}
