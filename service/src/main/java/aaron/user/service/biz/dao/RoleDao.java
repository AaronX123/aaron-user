package aaron.user.service.biz.dao;

import aaron.user.api.dto.UserOptionsDto;
import aaron.user.service.pojo.model.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    /**
     * 查询角色
     * @return 所拥有的角色集合
     */
    @Select("SELECT id AS roleId,name AS roleName,company_id FROM t_role ORDER BY LENGTH(name) ")
    List<UserOptionsDto> queryRole();

    /**
     * 查询角色所拥有的资源
     * @param role 角色
     * @return 角色对应的资源集合
     */
    @Select("SELECT a.id,a.name,a.parent_id,a.version from t_resource a,t_role_resource b where b.role_id = #{id} and a.id = b.resource_id and b.type = 0")
    List<TreeList> queryResourceForRole(Role role);

    /**
     * 查询角色所拥有的资源
     * @param role 角色
     * @return 角色对应的资源集合
     */
    @Select("<script>" +
            "SELECT e.id,e.code,e.name,e.roleName,e.departmentName,e.positionName,f.flag FROM " +
            "(SELECT a.id,a.code,a.name,GROUP_CONCAT(b.name SEPARATOR ',') AS roleName,c.name AS departmentName,d.name AS positionName " +
            "FROM t_user a " +
            "LEFT JOIN t_user_role e ON a.id = e.user_id " +
            "LEFT JOIN t_role b ON e.role_id = b.id " +
            "LEFT JOIN t_department c ON c.id = a.department_id " +
            "LEFT JOIN t_position d ON d.id = a.position_id " +
            "WHERE (b.company_id = #{judgeId} or b.org_id = #{judgeId}) " +
            "<if test=\"companyId!=null and companyId!=''\">" +
            "AND a.company_id = #{companyId} " +
            "</if>" +
            "GROUP BY a.id ORDER BY a.updated_time DESC) e " +
            "LEFT JOIN (SELECT user_id,CONCAT(user_id) as flag " +
            "FROM t_user_role " +
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
     * 删除角色资源
     * @param roleResource 角色资源
     * @return 删除条数
     */
    @Delete("DELETE FROM t_role_resource where role_id = #{roleId}")
    boolean deleteResourceForRole(RoleResource roleResource);

    /**
     * 插入用户角色
     * @param roleUser 用户角色
     * @return 插入条数
     */
    @Insert("<script>" +
            "INSERT INTO t_user_role (id,role_id,user_id) VALUES (#{id},#{roleId},#{userId}) " +
            "</script>")
    int insertUserForRole(RoleUser roleUser);

    /**
     * 删除用户角色
     * @param roleUser 用户角色
     * @return 删除条数
     */
    @Delete("DELETE FROM t_user_role where role_id = #{roleId}")
    boolean deleteUserForRole(RoleUser roleUser);

    /**
     * 模糊查询及查询全部记录
     * @param role 角色查询条件
     * @return 查询符合条件的角色记录集合
     */
    @Select("<script>" +
            "SELECT a.*,b.name AS orgName,c.name AS companyName FROM t_role a " +
            "LEFT JOIN t_organization b ON a.org_id = b.id " +
            "LEFT JOIN t_company c ON a.company_id = c.id " +
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
            "UPDATE t_role " +
            "SET id = #{id},company_id = #{companyId},org_id = #{orgId},name = #{name},code = #{code}, remark = #{remark}," +
            "status = #{status},created_by = #{createdBy},created_time = #{createdTime}," +
            "updated_by = #{updatedBy},updated_time = #{updatedTime},version = #{version} " +
            "WHERE id = #{id} AND version = #{oldVersion} " +
            "AND (id = #{judgeId} OR org_id = #{judgeId})" +
            "</script>")
    int update(Role role);

    @Delete("<script>" +
            "DELETE FROM t_role " +
            "WHERE id = #{id} AND version = #{version} " +
            "AND (id = #{judgeId} OR org_id = #{judgeId})" +
            "</script>")
    int deleteById(Role role);

    @SelectProvider(type = RoleProvider.class, method = "batchSelect")
    int selectByIdList(List<Role> roles);

    @DeleteProvider(type = RoleProvider.class, method = "batchDelete")
    int deleteByIdList(List<Role> roles);

    class RoleProvider {
        /* 查询用户是否被使用 */
        public String batchSelect(Map map) {
            List<Role> roles = (List<Role>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(id) FROM t_user_role WHERE ");
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
            sb.append("DELETE FROM t_department WHERE ");
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
