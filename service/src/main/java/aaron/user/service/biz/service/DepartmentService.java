package aaron.user.service.biz.service;

import aaron.user.api.dto.DepartmentDto;
import aaron.user.service.pojo.model.Department;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface DepartmentService extends IService<Department> {
    boolean save(DepartmentDto departmentDto);

    boolean update(DepartmentDto departmentDto);

    boolean delete(List<DepartmentDto> departmentDtoList);

    List<Department> query(Department department);

    List<Department> queryLevel();

    List<Department> queryParent();

    List<TreeList> queryTreeData();
}
