package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.UserOnlineInfo;
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
public interface UserOnlineInfoDao extends BaseMapper<UserOnlineInfo> {
    /**
     * 模糊查询及查询全部记录
     * @param userOnlineInfo 用户在线查询条件
     * @return 查询符合条件的用户在线记录集合
     */
    @Select("<script>" +
            "SELECT a.id,a.user_id,a.code,a.name,ip,online_time,offline_time,stop_time,a.status " +
            "FROM user_online_info a " +
            "LEFT JOIN user b ON a.user_id = b.id " +
            "<where>" +
            "<if test=\"onlineTime!=null and onlineTime!=''\">" +
            "AND DATE(online_time) &lt; #{offlineTime} AND DATE(offline_time) &gt; #{onlineTime}" +
            "</if>" +
            "<if test=\"code!=null and code!=''\">" +
            "AND a.code LIKE CONCAT(#{code},'%')" +
            "</if>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND b.company_id = #{judgeId} " +
            "</if>" +
            "</where>" +
            "ORDER BY status DESC,online_time ASC" +
            "</script>")
    List<UserOnlineInfo> query(UserOnlineInfo userOnlineInfo);
}
