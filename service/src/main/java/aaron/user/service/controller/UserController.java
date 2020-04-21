package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.TreeListDto;
import aaron.user.api.dto.UserDto;
import aaron.user.api.dto.UserOptionsDto;
import aaron.user.api.dto.UserRoleDto;
import aaron.user.service.biz.service.PositionService;
import aaron.user.service.biz.service.RoleService;
import aaron.user.service.biz.service.UserService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.TreeList;
import aaron.user.service.pojo.model.User;
import aaron.user.service.pojo.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Slf4j
@RestController
@RequestMapping(ControllerConstants.USER)
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class UserController {
    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PositionService positionService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_U)
    public CommonResponse<Boolean> saveUser(@RequestBody @Valid CommonRequest<UserItemVo> request){
        UserDto userDto = CommonUtils.copyProperties(request.getData(),UserDto.class);
        userService.save(userDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_U)
    public CommonResponse<Boolean> updateUser(@RequestBody @Valid CommonRequest<UserItemVo> request){
        UserDto userDto = CommonUtils.copyProperties(request.getData(),UserDto.class);
        userDto.setOldVersion(userDto.getVersion());
        userService.update(userDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_U)
    public CommonResponse<UserListVo> getUpdateFormUser(@RequestBody @Valid CommonRequest<Long> request){
        User user = userService.getById(request.getData());
        UserListVo userListVo = CommonUtils.copyProperties(user,UserListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,userListVo);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_U)
    public CommonResponse<Boolean> deleteUser(@RequestBody @Valid CommonRequest<List<UserItemVo>> request){
        List<UserDto> userDtoList = CommonUtils.convertList(request.getData(),UserDto.class);
        userService.delete(userDtoList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_U)
    public CommonResponse<Map> queryUser(@RequestBody @Valid CommonRequest<UserQueryVo> request){
        UserDto userDto = CommonUtils.copyProperties(request.getData(),UserDto.class);
        userDto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<UserListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<User> userList = userService.queryByCondition(userDto);
        List<UserListVo> userListVoList = CommonUtils.convertList(userList,UserListVo.class);
        Map map = PageMapUtil.getPageMap(userListVoList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_OPTIONS_U)
    public CommonResponse<Map> queryUserOptions(){
        List<UserOptionsDto> userRole = roleService.queryRole();
        List<UserOptionsDto> userPosition = positionService.queryPosition();
        Map<String,List> map = new HashMap<>(2);
        map.put("role",userRole);
        map.put("position",userPosition);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_LIST_U)
    public CommonResponse<List> queryUserTree(){
        List<TreeList> treeListDtoList = userService.getQueryListData(CommonUtils.judgeCompanyAndOrg());
        List<TreeListVo> treeListVos = CommonUtils.convertList(treeListDtoList,TreeListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,treeListVos);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.ALLOC_USER)
    public CommonResponse<Boolean> allocRoleUser(@RequestBody @Valid CommonRequest<UserRoleDto> request){
        userService.addRoleForUser(request.getData());
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }
}
