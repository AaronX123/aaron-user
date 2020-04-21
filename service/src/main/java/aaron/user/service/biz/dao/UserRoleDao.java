package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {
    @Select("<script>" +
            "SELECT * FROM user_role WHERE role_id IN " +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    List<UserRole> listByRoleIdList(@Param("ids") List<Long> ids);


    @Select("<script>" +
            "SELECT * FROM user_role WHERE user_id IN " +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    List<UserRole> listByUserIdList(@Param("ids") List<Long> ids);
}
