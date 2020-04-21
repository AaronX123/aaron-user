package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.Department;
import aaron.user.service.pojo.model.TreeList;
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
public interface DepartmentDao extends BaseMapper<Department> {

    @Select("SELECT * FROM department WHERE company_id = #{id}")
    List<Department> listByCompanyId(@Param("id") long id);

    /**
     * 根据Id更新部门，判别Version和DepartmentId
     * @param department 部门信息
     * @return 更新成功记录数
     */
    @Update("<script>" +
            "UPDATE department " +
            "SET id = #{id},company_id = #{companyId},name = #{name},mnemonic_code = #{mnemonicCode},code = #{code}," +
            "level = #{level}, parent_id = #{parentId},master = #{master},descript = #{descript},status = #{status},created_by = #{createdBy}," +
            "created_time = #{createdTime},updated_by = #{updatedBy},updated_time = #{updatedTime},version = #{version} " +
            "WHERE id = #{id} AND version = #{oldVersion} " +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND company_id = #{judgeId}" +
            "</if>" +
            "</script>")
    int update(Department department);

    /**
     * 获取父节点个数，判断是否为叶节点
     * @param department 部门信息
     * @return 父节点个数
     */
    @SelectProvider(type = DepartmentProvider.class, method = "batchSelect")
    int getLeafCount(List<Department> department);

    @DeleteProvider(type = DepartmentProvider.class, method = "batchDelete")
    int deleteByIdList(List<Department> departments);

    class DepartmentProvider {
        /* 查询部门是否存在下级部门 */
        public String batchSelect(Map map) {
            List<Department> departments = (List<Department>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(id) FROM department WHERE ");
            for (int i = 0; i < departments.size(); i++) {
                sb.append("parent_id = ").append(departments.get(i).getId());
                if (i != departments.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Department> departments = (List<Department>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM department WHERE ");
            for (int i = 0; i < departments.size(); i++) {
                sb.append("(id = ").append(departments.get(i).getId()).append(" AND ")
                        .append("version = ").append(departments.get(i).getVersion());
                if (departments.get(i).getJudgeId() != null) {
                    sb.append(" AND ").append("company_id = ").append(departments.get(i).getJudgeId()).append(")");
                } else {
                    sb.append(")");
                }
                if (i != departments.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }


    /**
     * 模糊查询
     * @param department 查询条件
     * @return 符合条件的部门记录
     */
    @Select("<script>" +
            "SELECT a.*,b.name AS parentDepartment FROM department a " +
            "LEFT JOIN department b ON b.id = a.parent_id " +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"level!=null and level!=''\">" +
            "AND a.level = #{level}" +
            "</if>" +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND a.company_id = #{judgeId} " +
            "</if>" +
            "</where>" +
            "ORDER BY updated_time DESC" +
            "</script>")
    List<Department> query(Department department);

    /**
     * 去重查询部门等级
     * @return 部门等级
     */
    @Select("SELECT DISTINCT level FROM department ORDER BY level")
    List<Department> queryLevel();

    /**
     * 根据等级排序查询id及name
     * @return 符合条件的部门记录集合
     */
    @Select("SELECT id,name FROM department ORDER BY level")
    List<Department> queryParent();

    /**
     * 查询部门记录输出树
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
