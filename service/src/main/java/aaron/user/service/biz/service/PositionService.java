package aaron.user.service.biz.service;

import aaron.user.api.dto.PositionDto;
import aaron.user.api.dto.UserOptionsDto;
import aaron.user.service.pojo.model.Position;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface PositionService extends IService<Position> {
    boolean save(PositionDto positionDto);

    boolean update(PositionDto positionDto);

    boolean delete(List<Position> positionList);

    List<Position> list(Position position);

    List<Position> listCompany();

    /**
     * 查询职位
     * @return 职位集合
     */
    List<UserOptionsDto> queryPosition();
}
