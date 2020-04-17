package aaron.user.service.controller;

import aaron.common.data.common.CacheConstants;
import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.utils.CommonUtils;
import aaron.user.api.dto.UserDto;
import aaron.user.service.biz.service.LoginService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.User;
import aaron.user.service.pojo.vo.UserInfo;
import aaron.user.service.pojo.vo.UserMenu;
import aaron.user.service.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.LOGIN)
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    CommonState state;

    @PostMapping(ControllerConstants.LOGIN)
    public CommonResponse<Map> login(@RequestBody UserVo userVo){
        UserDto user = CommonUtils.copyProperties(userVo,UserDto.class);
        String token = loginService.login(user);
        Map<String,Object> data = new HashMap<String, Object>(2);
        data.put("token",token);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,data);
    }


    @GetMapping(ControllerConstants.USER_INFO)
    public CommonResponse<UserInfo> getUserInfo(){
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,loginService.getUserInfo());
    }

    @PostMapping(ControllerConstants.USER_MENU)
    public CommonResponse<List<UserMenu>> getUserMenu(){
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,loginService.getUserMenu());
    }

    @PostMapping(ControllerConstants.LOGOUT)
    public CommonResponse<Boolean> logout(@RequestBody @Valid CommonRequest<List<Long>> request){
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,loginService.logout(request.getData()));
    }
}
