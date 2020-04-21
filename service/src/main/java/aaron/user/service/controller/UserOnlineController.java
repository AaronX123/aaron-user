package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.UserOnlineInfoDto;
import aaron.user.service.biz.service.LoginService;
import aaron.user.service.biz.service.UserOnlineInfoService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.UserOnlineInfo;
import aaron.user.service.pojo.vo.UserOnlineInfoListVo;
import aaron.user.service.pojo.vo.UserOnlineInfoQueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.USER_ONLINE)
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class UserOnlineController {
    @Autowired
    UserOnlineInfoService userOnlineInfoService;

    @Autowired
    LoginService loginService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_UO)
    public CommonResponse<Map> queryUserOnline(@RequestBody @Valid CommonRequest<UserOnlineInfoQueryVo> request){
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(request.getData(),UserOnlineInfo.class);
        List<UserOnlineInfo> onlineInfoList = userOnlineInfoService.queryByCondition(userOnlineInfo);
        Page<UserOnlineInfoListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        Map map = PageMapUtil.getPageMap(onlineInfoList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_ALL)
    public CommonResponse<List> queryAllUserOnline(@RequestBody CommonRequest<UserOnlineInfoQueryVo> request){
        UserOnlineInfoDto userOnlineInfoDto = CommonUtils.copyProperties(request.getData(),UserOnlineInfoDto.class);
        userOnlineInfoDto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        List<UserOnlineInfoDto> userOnlineInfoDtoList = userOnlineInfoService.queryAllByCondition(userOnlineInfoDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,CommonUtils.convertList(userOnlineInfoDtoList,UserOnlineInfoListVo.class));
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.OFFLINE)
    public CommonResponse<Boolean> kick(@RequestBody CommonRequest<List<Long>> request){
        loginService.logout(request.getData());
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }
}
