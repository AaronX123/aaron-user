package aaron.user.service.biz.dao;

import aaron.user.api.dto.UserOptionsDto;
import aaron.user.service.pojo.model.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {


    @Delete("DELETE FROM role WHERE id = #{id}")
    boolean delete(long id);


    @Delete("<script>" +
            "DELETE FROM role WHERE id IN" +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    boolean batchDeleteById(@Param("ids") List<Long> id);
    /**
     * 查询角色
     * @return 所拥有的角色集合
     */
    @Select("SELECT id AS roleId,name AS roleName,company_id FROM role ORDER BY LENGTH(name) ")
    List<UserOptionsDto> queryRole();

    @Select("SELECT id AS roleId,name AS roleName,company_id FROM role where company_id = #{id}")
    List<UserOptionsDto> queryRoleByCompany(@Param("id") long companyId);

    @Select("SELECT id AS roleId,name AS roleName,company_id FROM role where org_id = #{id}")
    List<UserOptionsDto> queryRoleByOrg(@Param("id") long orgId);
    /**
     * 查询角色所拥有的资源
     * @param role 角色
     * @return 角色对应的资源集合
     */
    @Select("SELECT a.id,a.name,a.parent_id,a.version from resource a,role_resource b where b.role_id = #{id} and a.id = b.resource_id and b.type = 0")
    List<TreeList> queryResourceForRole(Role role);

    /**
     * 查询角色所拥有的资源
     * @param role 角色
     * @return 角色对应的资源集合
     */
    @Select("<script>" +
            "SELECT e.id,e.code,e.name,e.roleName,e.departmentName,e.positionName,f.flag FROM " +
            "(SELECT a.id,a.code,a.name,GROUP_CONCAT(b.name SEPARATOR ',') AS roleName,c.name AS departmentName,d.name AS positionName " +
            "FROM user a " +
            "LEFT JOIN user_role e ON a.id = e.user_id " +
            "LEFT JOIN role b ON e.role_id = b.id " +
            "LEFT JOIN department c ON c.id = a.department_id " +
            "LEFT JOIN position d ON d.id = a.position_id " +
            "WHERE (b.company_id = #{judgeId} or b.org_id = #{judgeId}) " +
            "<if test=\"companyId!=null and companyId!=''\">" +
            "AND a.company_id = #{companyId} " +
            "</if>" +
            "GROUP BY a.id ORDER BY a.updated_time DESC) e " +
            "LEFT JOIN (SELECT user_id,CONCAT(user_id) as flag " +
            "FROM user_role " +
            "WHERE role_id = #{id}) f " +
            "ON e.id = f.user_id" +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND e.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"code!=null and code!=''\">" +
            "AND e.code LIKE CONCAT(#{code},'%')" +
            "</if>" +
            "</where>" +
            "</script>")
    List<UserForRole> queryUserForRole(Role role);



    /**
     * 查询角色所拥有的资源
     * @return 角色对应的资源集合
     */
    @Select("<script>" +
            "SELECT e.id,e.code,e.name,e.roleName,e.departmentName,e.positionName,f.flag FROM " +
            "(SELECT a.id,a.code,a.name,GROUP_CONCAT(b.name SEPARATOR ',') AS roleName,c.name AS departmentName,d.name AS positionName " +
            "FROM user a " +
            "LEFT JOIN user_role e ON a.id = e.user_id " +
            "LEFT JOIN role b ON e.role_id = b.id " +
            "LEFT JOIN department c ON c.id = a.department_id " +
            "LEFT JOIN position d ON d.id = a.position_id " +
            "GROUP BY a.id ORDER BY a.updated_time DESC) e " +
            "LEFT JOIN (SELECT user_id,CONCAT(user_id) as flag " +
            "FROM user_role) f ON e.id = f.user_id " +
            "</script>")
    List<UserForRole> queryUserForRoleSP();
    /**
     * 删除角色资源
     * @param roleId 角色资源
     * @return 删除条数
     */
    @Delete("DELETE FROM role_resource where role_id = #{roleId}")
    boolean deleteResourceForRole(@Param("roleId") long roleId);

    /**
     * 插入用户角色
     * @param roleUser 用户角色
     * @return 插入条数
     */
    @Insert("<script>" +
            "INSERT INTO user_role (id,role_id,user_id) VALUES (#{id},#{roleId},#{userId}) " +
            "</script>")
    int insertUserForRole(RoleUser roleUser);

    /**
     * 删除用户角色
     * @param roleUser 用户角色
     * @return 删除条数
     */
    @Delete("DELETE FROM user_role where role_id = #{roleId}")
    boolean deleteUserForRole(RoleUser roleUser);


    @Delete("DELETE FROM user_role WHERE user_id = #{id}")
    boolean deleteUser(@Param("id") long userId);
    /**
     * 模糊查询及查询全部记录
     * @param role 角色查询条件
     * @return 查询符合条件的角色记录集合
     */
    @Select("<script>" +
            "SELECT a.*,b.name AS orgName,c.name AS companyName FROM role a " +
            "LEFT JOIN organization b ON a.org_id = b.id " +
            "LEFT JOIN company c ON a.company_id = c.id " +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "AND (a.company_id = #{judgeId} or a.org_id = #{judgeId}) " +
            "</where>" +
            "ORDER BY a.updated_time DESC" +
            "</script>")
    List<Role> query(Role role);

    /**
     * 根据Id更新角色，判别Version和CompanyId
     * @param role 角色信息
     * @return 更新成功记录数
     */
    @Update("<script>" +
            "UPDATE role " +
            "SET id = #{id},company_id = #{companyId},org_id = #{orgId},name = #{name},code = #{code}, remark = #{remark}," +
            "status = #{status},created_by = #{createdBy},created_time = #{createdTime}," +
            "updated_by = #{updatedBy},updated_time = #{updatedTime},version = #{version} " +
            "WHERE id = #{id} AND version = #{oldVersion} " +
            "AND (id = #{judgeId} OR org_id = #{judgeId})" +
            "</script>")
    int update(Role role);

    @Delete("<script>" +
            "DELETE FROM role " +
            "WHERE id = #{id} AND version = #{version} " +
            "AND (id = #{judgeId} OR org_id = #{judgeId})" +
            "</script>")
    int deleteById(Role role);

    /**
     * 查询是否有人在使用些角色
     * @param ids
     * @return
     */
//    @SelectProvider(type = RoleProvider.class, method = "batchSelect")
    @Select("<script>" +
            "SELECT * FROM user_role WHERE role_id IN" +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<UserRole> selectByIdList(@Param("ids") List<Long> ids);

    @DeleteProvider(type = RoleProvider.class, method = "batchDelete")
    int deleteByIdList(List<Role> roles);

    class RoleProvider {
        /* 查询用户是否被使用 */
        public String batchSelect(Map map) {
            List<Role> roles = (List<Role>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(id) FROM user_role WHERE ");
            for (int i = 0; i < roles.size(); i++) {
                sb.append("role_id = ").append(roles.get(i).getId());
                if (i != roles.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Role> roles = (List<Role>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM department WHERE ");
            for (int i = 0; i < roles.size(); i++) {
                sb.append("(id = ").append(roles.get(i).getId()).append(" AND ")
                        .append("version = ").append(roles.get(i).getVersion()).append(" AND ")
                        .append("(id = ").append(roles.get(i).getJudgeId()).append(" OR ")
                        .append("rog_id = ").append(roles.get(i).getJudgeId()).append(")");
                if (i != roles.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }
}
