package aaron.user.service.biz.service;

import aaron.user.api.dto.CompanyDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.pojo.model.Company;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface CompanyService extends IService<Company> {
    boolean save(CompanyDto companyDto);

    boolean update(CompanyDto companyDto);

    List<Company> queryCompany(CompanyDto companyDto);

    List<TreeListDto> getCompanyTree(long id);

    String getNameById(long id);
}
