package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.RoleResourceDao;
import aaron.user.service.biz.service.RoleResourceService;
import aaron.user.service.pojo.model.RoleResource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-14
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceDao, RoleResource> implements RoleResourceService {
}
