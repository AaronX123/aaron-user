package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonField;
import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.CompanyDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.dao.CompanyDao;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.biz.service.OrganizationService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.common.utils.AdminUtil;
import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.model.Organization;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, Company> implements CompanyService {
    @Autowired
    OrganizationService organizationService;

    @FullCommonFieldU
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
        // 如果当前用户id为1 则是超级管理员，直接显示所有公司
        if (TokenUtils.getUser().getId() == 1){
            return list();
        }
        // 根据orgId查询旗下公司
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        if (companyDto.getOrgId() != null){
            wrapper.eq("org_id",companyDto.getOrgId());
        }else {
            wrapper.eq("org_id", TokenUtils.getUser().getOrgId());
        }
        if (StringUtils.isNotBlank(companyDto.getName())){
            wrapper.likeRight("name",companyDto.getName());
        }
        return list(wrapper);
    }

    @Override
    public List<TreeListDto> getCompanyTree(Long id) {
        // 超级管理员直接返回所有结果
        if (AdminUtil.isSuperAdmin()){
            List<Company> companyList = list();
            // 设置上级
            List<Organization> organizationList = organizationService.list();
            List<TreeListDto> res = new ArrayList<>();
            for (Organization organization : organizationList) {
                for (Company company : companyList) {
                    if (company.getOrgId().equals(organization.getId())){
                        TreeListDto treeListDto = CommonUtils.copyProperties(company,TreeListDto.class);
                        treeListDto.setParentId(organization.getId());
                        res.add(treeListDto);
                    }
                }
                TreeListDto treeListDto = CommonUtils.copyProperties(organization,TreeListDto.class);
                res.add(treeListDto);
            }
            return res;
        }
        List<TreeList> treeList = baseMapper.getQueryListData(id);
        if (treeList == null){
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        Set<TreeList> res = new HashSet<>();
        List<Organization> organizationList = organizationService.list();
        for (TreeList list : treeList) {
            for (Organization organization : organizationList) {
               if ((list.getId().equals(organization.getId()) && organization.getId().equals(TokenUtils.getUser().getOrgId()))
                       || TokenUtils.getUser().getOrgId().equals(list.getParentId())){
                   res.add(list);
               }
            }
        }
        return CommonUtils.convertList(res,TreeListDto.class);
    }

    @Override
    public String getNameById(long id) {
        return baseMapper.selectNameById(id);
    }
}
