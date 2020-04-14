package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface CompanyDao extends BaseMapper<Company> {
    @Select("SELECT name FROM t_company WHERE id = #{id}")
    String selectNameById(Long id);

    /**
     * 模糊查询
     * @param company 查询条件
     * @return 符合条件的公司记录
     */
    @Select("<script>" +
            "SELECT a.*,b.name AS orgName FROM t_company a " +
            "LEFT JOIN t_organization b ON a.org_id = b.id"  +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"orgId!=null and orgId!=''\">" +
            "AND a.org_id = #{orgId}" +
            "</if>" +
            "AND (a.id = #{judgeId} OR a.org_id = #{judgeId}) " +
            "</where>" +
            "ORDER BY a.updated_time DESC" +
            "</script>")
    List<Company> query(Company company);

    /**
     * 查询树
     * @return 树的相关数据
     */
    @Select("SELECT id,name,org_id as parent_id,version from t_company " +
            "WHERE id = #{judgeId} OR org_id = #{judgeId} " +
            "UNION " +
            "SELECT id,name,null as parent_id,version FROM t_organization " +
            "ORDER BY parent_id")
    List<TreeList> getQueryListData(Long judgeId);
}
