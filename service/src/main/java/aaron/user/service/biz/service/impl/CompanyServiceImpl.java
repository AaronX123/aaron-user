package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonField;
import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.user.api.dto.CompanyDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.dao.CompanyDao;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, Company> implements CompanyService {
    @FullCommonField
    @Override
    public boolean save(CompanyDto companyDto) {
        Company company = CommonUtils.copyProperties(companyDto,Company.class);
        return save(company);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(CompanyDto companyDto) {
        Company company = CommonUtils.copyProperties(companyDto,Company.class);
        return updateById(company);
    }

    @Override
    public List<Company> queryCompany(CompanyDto companyDto) {
        Company company = CommonUtils.copyProperties(companyDto,Company.class);
        return baseMapper.query(company);
    }

    @Override
    public List<TreeListDto> getCompanyTree(long id) {
        List<TreeList> treeList = baseMapper.getQueryListData(id);
        if (treeList == null){
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        return CommonUtils.convertList(treeList,TreeListDto.class);
    }
}
