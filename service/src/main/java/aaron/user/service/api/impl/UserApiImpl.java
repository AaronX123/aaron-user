package aaron.user.service.api.impl;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.user.api.api.UserApi;
import aaron.user.api.constant.ApiConstant;
import aaron.user.api.dto.UserDto;
import aaron.user.api.dto.CompanyAndUserVo;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.biz.service.UserService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@RestController
public class UserApiImpl implements UserApi {
    @Autowired
    UserService userService;

    @Autowired
    CommonState state;

    @Autowired
    CompanyService companyService;

    /**
     * 获取公司和用户名称
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping(ApiConstant.GET_USER_NAME)
    public CommonResponse<CompanyAndUserVo> getUserInfo(@RequestBody CommonRequest<List<Long>> request) {
        try {
            CompanyAndUserVo res = userService.getUserData(request.getData());
            return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,res);
        }catch (Exception e){
            if (e instanceof UserException){
                UserException userException = (UserException) e;
                return new CommonResponse<>(userException.getErrorCode(),userException.getMessage(),null);
            }
            return new CommonResponse<>(state.FAIL,state.FAIL_MSG,null);
        }
    }

    /**
     * 通过用户id查询用户名
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping(ApiConstant.GET_USER_NAME_BY_ID)
    public CommonResponse<String> getUserNameById(@RequestBody CommonRequest<Long> request) {
        try {
            String name = userService.getUserName(request.getData());
            return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,name);
        }catch (Exception e){
            if (e instanceof UserException){
                UserException userException = (UserException) e;
                return new CommonResponse<>(userException.getErrorCode(),userException.getMessage(),null);
            }
            return new CommonResponse<>(state.FAIL,state.FAIL_MSG,null);
        }
    }

    /**
     * 查询阅卷官
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping(ApiConstant.GET_SCORING_OFFICER)
    public CommonResponse<List<UserDto>> queryScoringOfficer(@RequestBody CommonRequest<UserDto> request) {
        try {
            List<UserDto> userDtoList = userService.queryScoringOfficerList(request.getData());
            return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,userDtoList);
        }catch (Exception e){
            if (e instanceof UserException){
                UserException userException = (UserException) e;
                return new CommonResponse<>(userException.getErrorCode(),userException.getMessage(),null);
            }
            return new CommonResponse<>(state.FAIL,state.FAIL_MSG,null);
        }
    }

    /**
     * 由于是同一公司才能访问因此发生重名概率很低，而且只是通过名称获取id
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping(ApiConstant.GET_ID_BY_NAME)
    public CommonResponse<Long> getUserIdByName(@RequestBody CommonRequest<String> request) {
        try {
            Long mostPossibleId = userService.getMostPossibleUserId(request.getData());
            return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,mostPossibleId);
        }catch (Exception e){
            if (e instanceof UserException){
                UserException userException = (UserException) e;
                return new CommonResponse<>(userException.getErrorCode(),userException.getMessage(),null);
            }
            return new CommonResponse<>(state.FAIL,state.FAIL_MSG,null);
        }
    }

    /**
     * 通过公司Id获取公司名称
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping(ApiConstant.GET_COMPANY_NAME)
    public CommonResponse<String> getCompanyById(@RequestBody CommonRequest<Long> request) {
        try {
            String company = companyService.getNameById(request.getData());
            return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,company);
        }catch (Exception e){
            if (e instanceof UserException){
                UserException userException = (UserException) e;
                return new CommonResponse<>(userException.getErrorCode(),userException.getMessage(),null);
            }
            return new CommonResponse<>(state.FAIL,state.FAIL_MSG,null);
        }
    }
}
