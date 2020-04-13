package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonField;
import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.DepartmentDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.dao.DepartmentDao;
import aaron.user.service.biz.service.DepartmentService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Department;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @FullCommonField
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
        if (baseMapper.getLeafCount(departments) > 0){
            throw new UserException(UserError.EXIST_SUB_DEP);
        }
        if (baseMapper.deleteByIdList(departments) < departmentDtoList.size()){
            throw new UserException(UserError.DELETE_FAIL);
        }
        return true;
    }

    @Override
    public List<Department> query(Department department) {
        if (department.getJudgeId().equals(TokenUtils.getUser().getOrgId())){
            department.setJudgeId(null);
        }
        return baseMapper.query(department);
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
        long id = CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId()) ? null : CommonUtils.judgeCompanyAndOrg();
        return departmentDao.getQueryListData(id);
    }
}
