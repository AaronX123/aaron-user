package aaron.user.service.api.impl;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.user.api.api.UserApi;
import aaron.user.api.dto.UserDto;
import aaron.user.api.dto.UserInfoDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@RestController
public class UserApiImpl implements UserApi {
    /**
     * 通过用户Id获取用户名称
     *
     * @param request
     * @return
     */
    public CommonResponse<UserInfoDto> getUserInfo(CommonRequest<List<Long>> request) {
        return null;
    }

    /**
     * 通过用户id查询用户名
     *
     * @param request
     * @return
     */
    public CommonResponse<String> getUserNameById(CommonRequest<Long> request) {
        return null;
    }

    /**
     * 查询阅卷官
     *
     * @param request
     * @return
     */
    public CommonResponse<UserDto> queryScoringOfficer(CommonRequest<UserDto> request) {
        return null;
    }

    /**
     * 由于是同一公司才能访问因此发生重名概率很低，而且只是通过名称获取id
     *
     * @param request
     * @return
     */
    public CommonResponse<String> getUserIdByName(CommonRequest<String> request) {
        return null;
    }

    /**
     * 通过公司Id获取公司名称
     *
     * @param request
     * @return
     */
    public CommonResponse<String> getCompanyById(CommonRequest<Long> request) {
        return null;
    }
}
