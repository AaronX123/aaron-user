package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.SystemParam;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface SystemParamDao extends BaseMapper<SystemParam> {
    /**
     * 查询资源记录输出树
     * @return 以treelist集合形式返回数据生成树
     */
    @Select("SELECT DISTINCT param_type AS name FROM t_system_param")
    List<TreeList> getQueryListData();

    /**
     * 模糊查询及查询全部记录
     * @param systemParam 参数查询条件
     * @return 查询符合条件的参数记录集合
     */
    @Select("<script>" +
            "SELECT * FROM t_system_param " +
            "<where>" +
            "<if test=\"paramType!=null and paramType!=''\">" +
            "AND param_type = #{paramType}" +
            "</if>" +
            "<if test=\"param!=null and param!=''\">" +
            "AND param LIKE CONCAT(#{param},'%')" +
            "</if>" +
            "</where>" +
            "ORDER BY updated_time DESC" +
            "</script>")
    List<SystemParam> query(SystemParam systemParam);

    @Update("<script>" +
            "UPDATE t_system_param " +
            "SET id = #{id},org_id = #{orgId},param_type = #{paramType},param = #{param}, value = #{value}," +
            "status = #{status},created_by = #{createdBy},created_time = #{createdTime}," +
            "updated_by = #{updatedBy},updated_time = #{updatedTime},version = #{version} " +
            "WHERE id = #{id} AND version = #{oldVersion} " +
            "</script>")
    int update(SystemParam systemParam);

    @DeleteProvider(type = SystemParamProvider.class, method = "batchDelete")
    int deleteByIdList(List<SystemParam> systemParamList);

    class SystemParamProvider {
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<SystemParam> systemParams = (List<SystemParam>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM t_system_param WHERE ");
            for (int i = 0; i < systemParams.size(); i++) {
                sb.append("(id = ").append(systemParams.get(i).getId()).append(" AND ")
                        .append("version = ").append(systemParams.get(i).getVersion()).append(")");
                if (i != systemParams.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }
}
