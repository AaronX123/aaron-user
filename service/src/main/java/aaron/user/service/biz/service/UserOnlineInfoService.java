package aaron.user.service.biz.service;

import aaron.user.api.dto.UserOnlineInfoDto;
import aaron.user.service.pojo.model.UserOnlineInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface UserOnlineInfoService extends IService<UserOnlineInfo> {
    List<UserOnlineInfo> queryByCondition(UserOnlineInfo userOnlineInfo);

    /**
     * 根据请求条件查询符合条件的全部用户在线记录集合
     * @param userOnlineInfoDTO 请求条件查询信息
     * @return
     */
    List<UserOnlineInfoDto> queryAllByCondition(UserOnlineInfoDto userOnlineInfoDTO);
}
