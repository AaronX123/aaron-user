package aaron.user.service.biz.service;

import aaron.user.api.dto.OrganizationDto;
import aaron.user.service.pojo.model.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface OrganizationService extends IService<Organization> {
    boolean save(OrganizationDto organizationDto);

    boolean update(OrganizationDto organizationDto);

    boolean delete(List<OrganizationDto> list);

    List<Organization> list(OrganizationDto organizationDto);

    String getNameById(long id);
}
