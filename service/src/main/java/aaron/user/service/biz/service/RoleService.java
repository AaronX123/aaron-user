package aaron.user.service.biz.service;

import aaron.user.api.dto.*;
import aaron.user.service.pojo.model.Role;
import aaron.user.service.pojo.model.UserForRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface RoleService extends IService<Role> {
    boolean save(RoleDto roleDto);

    boolean update(RoleDto roleDto);

    /**
     * 删除角色记录
     * @param roleDtoList 角色List集合
     * @return 删除成功条数
     */
    boolean delete(List<RoleDto> roleDtoList);

    /**
     * 根据请求条件查询符合条件的角色记录集合
     * @param role 请求条件查询信息
     * @return
     */
    List<Role> queryByCondition(Role role);

    boolean addUserForRole(List<RoleUserDto> roleUserDtoList);

    boolean addResourceForRole(List<RoleResourceDto> resourceDtos);

    List<UserForRole> queryUserRole(Role role);

    /**
     * 查询角色分配资源的树集合
     * @param roleDTO 角色信息
     * @return 以树（treelist）形式返回资源数据
     */
    List<TreeListDto> queryResourceForRole(RoleDto roleDTO);


    /**
     * 查询角色
     * @return 角色集合
     */
    List<UserOptionsDto> queryRole();

}
