package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.UserOnlineInfoDao;
import aaron.user.service.biz.service.UserOnlineInfoService;
import aaron.user.service.pojo.model.UserOnlineInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserOnlineInfoServiceImpl extends ServiceImpl<UserOnlineInfoDao, UserOnlineInfo> implements UserOnlineInfoService {
}
