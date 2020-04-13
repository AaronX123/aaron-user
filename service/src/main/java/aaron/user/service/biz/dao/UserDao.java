package aaron.user.service.biz.dao;

import aaron.common.utils.jwt.UserPermission;
import aaron.user.service.pojo.model.User;
import aaron.user.service.pojo.vo.UserInfo;
import aaron.user.service.pojo.vo.UserMenu;
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
public interface UserDao extends BaseMapper<User> {

    @Select("SELECT DISTINCT a.id,a.code,a.name AS userName,a.company_id,c.org_id FROM t_user a,t_user_role b,t_role c " +
            "where a.code = #{code} and a.password = #{password} and a.id = b.user_id and b.role_id = c.id")
    UserPermission checkUser(User user);


    @Select("select id,name,profile_picture,company_id from t_user where id = #{id} ")
    UserInfo getUserInfo(UserPermission userPermission);

    @Select("SELECT distinct d.id,d.name,d.code,d.parent_id,d.url,d.open_img,d.close_img,d.resource_type " +
            "FROM t_role_resource a,t_user_role b,t_user c,t_resource d " +
            "WHERE a.role_id = b.role_id AND b.user_id = c.id AND d.id = a.resource_id AND c.id = #{id} " +
            "ORDER BY id")
    List<UserMenu> getUserMenu(UserPermission userPermission);
}
