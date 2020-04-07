package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.OrganizationDao;
import aaron.user.service.biz.service.OrganizationService;
import aaron.user.service.pojo.model.Organization;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, Organization> implements OrganizationService {
}
