package aaron.user.service.biz.dao;

import aaron.user.service.pojo.model.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface PositionDao extends BaseMapper<Position> {
}
