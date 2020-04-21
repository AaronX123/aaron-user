package aaron.user.service.biz.dao;

import aaron.common.utils.jwt.UserPermission;
import aaron.user.service.pojo.model.TreeList;
import aaron.user.service.pojo.model.User;
import aaron.user.service.pojo.vo.UserInfo;
import aaron.user.service.pojo.vo.UserMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    @Delete("DELETE FROM user WHERE id = #{id}")
    boolean delById(long id);


    @Select("SELECT id FROM user WHERE name = #{userName} ")
    Long selectIdByName(String userName);
    /**
     * 查询角色为阅卷官的用户
     * @param user 查询条件
     * @return 符合条件的阅卷官记录集合
     */
    @Select("<script>" +
            "SELECT a.id,a.name " +
            "FROM user a,role b,user_role c " +
            "WHERE a.id = c.user_id AND b.id = c.role_id AND b.name = '阅卷官' " +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"companyId!=null and companyId!=''\">" +
            "AND a.company_id = #{companyId}" +
            "</if>" +
            "ORDER BY a.updated_time" +
            "</script>")
    List<User> queryOfficerList(User user);

    /**
     * 模糊查询及查询全部记录
     * @param user 用户查询条件
     * @return 查询符合条件的用户记录集合
     */
    @Select("<script>" +
            "SELECT a.id,a.code,a.password,a.name,a.sex,a.birthday,a.tel,a.email,a.other,a.remark,a.status,b.name AS positionName,GROUP_CONCAT(d.name SEPARATOR ',') AS roleName,c.name AS departmentName,f.name AS companyName,a.version " +
            "FROM user a " +
            "LEFT JOIN position b ON a.position_id = b.id " +
            "LEFT JOIN department c ON a.department_id = c.id " +
            "LEFT JOIN user_role e ON a.id = e.user_id " +
            "LEFT JOIN role d ON e.role_id = d.id " +
            "LEFT JOIN company f ON a.company_id = f.id " +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"code!=null and code!=''\">" +
            "AND a.code LIKE CONCAT(#{code},'%')" +
            "</if>" +
            "<if test=\"tel!=null and tel!=''\">" +
            "AND a.tel LIKE CONCAT(#{tel},'%')" +
            "</if>" +
            "<if test=\"roleId!=null and roleId!=''\">" +
            "AND e.role_id = #{roleId} " +
            "</if>" +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND a.company_id = #{judgeId} " +
            "</if>" +
            "</where>" +
            "GROUP BY a.id ORDER BY a.updated_time DESC" +
            "</script>")
    List<User> query(User user);

    @Select("SELECT DISTINCT a.id,a.code,a.name AS userName,a.company_id,c.org_id FROM user a,user_role b,role c " +
            "where a.code = #{code} and a.password = #{password} and a.id = b.user_id and b.role_id = c.id")
    UserPermission checkUser(User user);


    @Select("select id,name,profile_picture,company_id from user where id = #{id} ")
    UserInfo getUserInfo(UserPermission userPermission);

    @Select("SELECT distinct d.id,d.name,d.code,d.parent_id,d.url,d.open_img,d.close_img,d.resource_type " +
            "FROM role_resource a,user_role b,user c,resource d " +
            "WHERE a.role_id = b.role_id AND b.user_id = c.id AND d.id = a.resource_id AND c.id = #{id} " +
            "ORDER BY id")
    List<UserMenu> getUserMenu(UserPermission userPermission);

    /**
     * 根据用户Id删除对应角色
     * @param id 用户Id
     * @return 删除条数
     */
    @Delete("<script>" +
            "DELETE FROM user_role WHERE user_id = #{id} " +
            "</script>")
    int deleteRole(Long id);

    @DeleteProvider(type = DeleteUserRoleProvider.class, method = "batchDelete")
    int deleteRoleList(List<User> users);

    class DeleteUserRoleProvider {
        /* 批量删除中间表 */
        public String batchDelete(Map map) {
            List<User> userList = (List<User>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM user_role WHERE ");
            for (int i = 0; i < userList.size(); i++) {
                sb.append("(user_id = ").append(userList.get(i).getId()).append(")");
                if (i != userList.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }


    /**
     * 查询用户记录输出树
     * @return treelist记录
     */
    @Select("<script>" +
            "SELECT id,name,company_id AS parent_id,company_id AS root_id,version from department " +
            "WHERE level = '1' " +
            "<if test=\"_parameter!=null and _parameter!=''\">" +
            "AND company_id = #{_parameter} " +
            "</if>" +
            "UNION  " +
            "SELECT id,name,parent_id,company_id AS root_id,version from department " +
            "WHERE level != '1' " +
            "<if test=\"_parameter!=null and _parameter!=''\">" +
            "AND company_id = #{_parameter} " +
            "</if>" +
            "UNION  " +
            "SELECT id,name,null AS parent_id,id AS root_id,version FROM company " +
            "<where>" +
            "<if test=\"_parameter!=null and _parameter!=''\">" +
            "id = #{_parameter} " +
            "</if>" +
            "</where>" +
            "ORDER BY parent_id" +
            "</script>")
    List<TreeList> getQueryListData(Long judgeId);
}
