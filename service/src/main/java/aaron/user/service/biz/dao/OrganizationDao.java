package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.Organization;
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
public interface OrganizationDao extends BaseMapper<Organization> {
    /**
     * 模糊查询及查询全部记录
     * @param organization 组织机构查询条件
     * @return 查询符合条件的组织机构记录集合
     */
    @Select("<script>" +
            "SELECT * FROM t_organization " +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "</where>" +
            "ORDER BY updated_time DESC" +
            "</script>")
    List<Organization> query(Organization organization);
}
