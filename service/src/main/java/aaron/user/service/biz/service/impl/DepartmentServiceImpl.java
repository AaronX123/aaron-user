package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.ExceptionUtils;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.CompanyDto;
import aaron.user.api.dto.DepartmentDto;
import aaron.user.service.biz.dao.DepartmentDao;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.biz.service.DepartmentService;
import aaron.user.service.biz.service.OrganizationService;
import aaron.user.service.common.utils.AdminUtil;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.model.Department;
import aaron.user.service.pojo.model.Organization;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {
    @Resource
    DepartmentDao departmentDao;

    @Autowired
    OrganizationService organizationService;


    @Autowired
    CompanyService companyService;

    @FullCommonFieldU
    @Override
    public boolean save(DepartmentDto departmentDto) {
        Department department = CommonUtils.copyProperties(departmentDto,Department.class);
        if (save(department)){
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(DepartmentDto departmentDto) {
        Department department = CommonUtils.copyProperties(departmentDto,Department.class);
        if (baseMapper.update(department) == 1){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<DepartmentDto> departmentDtoList) {
        List<Department> departments = CommonUtils.convertList(departmentDtoList,Department.class);
        for (Department department : departments) {
            department.setJudgeId(CommonUtils.judgeCompanyAndOrg());
            if (department.getJudgeId().equals(TokenUtils.getUser().getOrgId())){
                department.setJudgeId(null);
            }
        }
        try {
            if (baseMapper.deleteByIdList(departments) < departmentDtoList.size()){
                throw new UserException(UserError.DELETE_FAIL);
            }
        }catch (Exception e){
            if (ExceptionUtils.isForeignKeyViolation(e)){
                throw new UserException(UserError.EXIST_WORKER);
            }
        }
        return true;
    }

    @Override
    public List<Department> query(Department department) {
        if (AdminUtil.isSuperAdmin()){
            return list();
        }
        if (department.getJudgeId().equals(TokenUtils.getUser().getOrgId())){
            department.setJudgeId(null);
        }
        List<Department> data = baseMapper.query(department);
        // 去掉不是该用户的机构的数据 id为companyId
        List<Company> companyList = companyService.queryCompany(CompanyDto.builder().orgId(TokenUtils.getUser().getOrgId()).build());
        List<Department> res = new ArrayList<>();
        for (Company company : companyList) {
            for (Department d : data) {
                if (d.getCompanyId().equals(company.getId())){
                    res.add(d);
                }
            }
        }
        return res;
    }

    @Override
    public List<Department> queryLevel() {
        return baseMapper.queryLevel();
    }

    @Override
    public List<Department> queryParent() {
        return baseMapper.queryParent();
    }

    @Override
    public List<TreeList> queryTreeData() {
        // 超级管理员
        if (AdminUtil.isSuperAdmin()){
            List<TreeList> res = new ArrayList<>();
            List<Organization> organizationList = organizationService.list();
            for (Organization organization : organizationList) {
                res.addAll(departmentDao.getQueryListData(organization.getId()));
            }
            return res;
        }
        Long id = null;
        if (!CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())){
            id = CommonUtils.judgeCompanyAndOrg();
        }
        List<TreeList> data = departmentDao.getQueryListData(id);
        // 去掉不是该用户的机构的数据 id为companyId
        List<Company> companyList = companyService.queryCompany(CompanyDto.builder().orgId(TokenUtils.getUser().getOrgId()).build());
        List<TreeList> res = new ArrayList<>();
        for (Company company : companyList) {
            for (TreeList treeList : data) {
                if (treeList.getId().equals(company.getId())){
                    res.add(treeList);
                }
            }
        }
        return res;
    }
}
