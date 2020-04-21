package aaron.user.service.biz.dao;

import aaron.user.api.dto.PositionDto;
import aaron.user.api.dto.UserOptionsDto;
import aaron.user.service.pojo.model.Position;
import aaron.user.service.pojo.model.User;
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
public interface PositionDao extends BaseMapper<Position> {
    /**
     * 查询职位集合
     * @return 职位集合
     */
    @Select("SELECT id AS positionId,name AS positionName,company_id FROM position WHERE company_id = #{id} ORDER BY LENGTH(name)")
    List<UserOptionsDto> queryPosition(@Param("id")long id);

    @Select("<script>" +
            "SELECT * FROM position WHERE company_id IN " +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    List<Position> listByCompanyId(@Param("ids") List<Long> ids);

    @Update("<script>" +
            "UPDATE position " +
            "SET id = #{id},company_id = #{companyId},name = #{name},code = #{code},remark = #{remark}," +
            "status = #{status},created_by = #{createdBy},created_time = #{createdTime}," +
            "updated_by = #{updatedBy},updated_time = #{updatedTime},version = #{version} " +
            "WHERE id = #{id} AND version = #{oldVersion} " +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND company_id = #{judgeId}" +
            "</if>" +
            "</script>")
    int update(Position position);


    @SelectProvider(type = PositionProvider.class, method = "batchSelect")
    int getLeafCount(List<Position> position);

    @Select("SELECT id AS companyId,name AS companyName FROM company")
    List<Position> queryOptions();

    @Delete("<script>" +
            "DELETE FROM position WHERE id = #{id} AND version = #{version} " +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND company_id = #{judgeId}" +
            "</if>" +
            "</script>")
    int deleteById(Position position);

    @DeleteProvider(type = PositionDao.PositionProvider.class, method = "batchDelete")
    int deleteByIdList(List<Position> positions);

    class PositionProvider {
        /* 查询职位是否被使用 */
        public String batchSelect(Map map) {
            List<Position> positions = (List<Position>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(id) FROM user WHERE ");
            for (int i = 0; i < positions.size(); i++) {
                sb.append("position_id = ").append(positions.get(i).getId());
                if (i != positions.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Position> positionList = (List<Position>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM position WHERE ");
            for (int i = 0; i < positionList.size(); i++) {
                sb.append("(id = ").append(positionList.get(i).getId()).append(" AND ")
                        .append("version = ").append(positionList.get(i).getVersion());
                if (positionList.get(i).getJudgeId() != null) {
                    sb.append(" AND ").append("company_id = ").append(positionList.get(i).getJudgeId()).append(")");
                }else {
                    sb.append(")");
                }
                if (i != positionList.size()-1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }

    /**
     * 模糊查询及查询全部记录
     * @param role 角色查询条件
     * @return 查询符合条件的角色记录集合
     */
    @Select("<script>" +
            "SELECT a.*,b.name AS companyName FROM position a " +
            "LEFT JOIN company b ON a.company_id = b.id " +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"judgeId!=null and judgeId!=''\">" +
            "AND a.company_id = #{judgeId} " +
            "</if>" +
            "</where>" +
            "ORDER BY a.updated_time DESC" +
            "</script>")
    List<Position> query(Position role);

}
