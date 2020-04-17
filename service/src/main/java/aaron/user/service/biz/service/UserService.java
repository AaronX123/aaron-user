package aaron.user.service.biz.service;

import aaron.user.api.dto.*;
import aaron.user.service.pojo.model.TreeList;
import aaron.user.service.pojo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface UserService extends IService<User> {
    Long getMostPossibleUserId(String name);
    /**
     * 通过名字查询符合条件的阅卷官信息
     * @param userDto 名字及公司Id
     * @return 阅卷官记录集合
     */
    List<UserDto> queryScoringOfficerList(UserDto userDto);

    CompanyAndUserVo getUserData(List<Long> longList);

    String getUserName(long id);

    boolean save(UserDto userDto);

    boolean update(UserDto userDto);


    /**
     * 删除用户记录
     * @param userDTOList 用户List集合
     * @return 删除成功条数
     */
    boolean delete(List<UserDto> userDTOList);

    /**
     * 根据请求条件查询符合条件的用户记录集合
     * @param userDto 请求条件查询信息
     * @return 符合条件的分页后的用户Map集合
     */
    List<User> queryByCondition(UserDto userDto);

    /**
     * 为用户分配角色
     * @param userRoleDTO 用户角色信息
     * @return 是否分配成功
     */
    boolean addRoleForUser(UserRoleDto userRoleDTO);

    /**
     * 获取用户树集合
     * @param judgeId 组织机构Id或公司Id
     * @return 以树（treelist）形式返回数据
     */
    List<TreeList> getQueryListData(Long judgeId);
}
