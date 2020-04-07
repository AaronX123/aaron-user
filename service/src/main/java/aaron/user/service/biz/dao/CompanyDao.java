package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.Company;
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
}
