package aaron.user.service.biz.service.impl;

import aaron.user.service.biz.dao.SystemParamDao;
import aaron.user.service.biz.service.SystemParamService;
import aaron.user.service.pojo.model.SystemParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class SystemParamServiceImpl extends ServiceImpl<SystemParamDao, SystemParam> implements SystemParamService {
}