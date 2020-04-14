package aaron.user.api.api;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.user.api.constant.ApiConstant;
import aaron.user.api.dto.UserDto;
import aaron.user.api.dto.CompanyAndUserVo;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface UserApi {
    /**
     * 获取公司和用户名称
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_USER_NAME)
    CommonResponse<CompanyAndUserVo> getUserInfo(CommonRequest<List<Long>> request);

    /**
     * 通过用户id查询用户名
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_USER_NAME_BY_ID)
    CommonResponse<String> getUserNameById(CommonRequest<Long> request);

    /**
     * 查询阅卷官
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_SCORING_OFFICER)
    CommonResponse<List<UserDto>> queryScoringOfficer(CommonRequest<UserDto> request);

    /**
     * 由于是同一公司才能访问因此发生重名概率很低，而且只是通过名称获取id
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_ID_BY_NAME)
    CommonResponse<Long> getUserIdByName(CommonRequest<String> request);

    /**
     * 通过公司Id获取公司名称
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_COMPANY_NAME)
    CommonResponse<String> getCompanyById(CommonRequest<Long> request);
}
