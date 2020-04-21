package aaron.user.service.biz.service.impl;

import aaron.common.utils.CommonUtils;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.UserOnlineInfoDto;
import aaron.user.service.biz.dao.UserOnlineInfoDao;
import aaron.user.service.biz.service.UserOnlineInfoService;
import aaron.user.service.common.utils.AdminUtil;
import aaron.user.service.pojo.model.UserOnlineInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserOnlineInfoServiceImpl extends ServiceImpl<UserOnlineInfoDao, UserOnlineInfo> implements UserOnlineInfoService {
    @Override
    public List<UserOnlineInfo> queryByCondition(UserOnlineInfo userOnlineInfo) {
        if (AdminUtil.isSuperAdmin()){
            return list();
        }
        if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())){
            userOnlineInfo.setJudgeId(null);
        }else {
            userOnlineInfo.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        return baseMapper.query(userOnlineInfo);
    }

    /**
     * 根据请求条件查询符合条件的全部用户在线记录集合
     *
     * @param userOnlineInfoDTO 请求条件查询信息
     * @return
     */
    @Override
    public List<UserOnlineInfoDto> queryAllByCondition(UserOnlineInfoDto userOnlineInfoDTO) {
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(userOnlineInfoDTO,UserOnlineInfo.class);
        if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())){
            userOnlineInfo.setJudgeId(null);
        }
        List<UserOnlineInfo> userOnlineInfoList = baseMapper.query(userOnlineInfo);
        return CommonUtils.convertList(userOnlineInfoList,UserOnlineInfoDto.class);
    }
}
