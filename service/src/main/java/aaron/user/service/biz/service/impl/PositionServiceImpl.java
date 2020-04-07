package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.PositionDao;
import aaron.user.service.biz.service.PositionService;
import aaron.user.service.pojo.model.Position;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionDao, Position> implements PositionService {
}
