package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.SystemParamDto;
import aaron.user.service.biz.dao.SystemParamDao;
import aaron.user.service.biz.service.SystemParamService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.SystemParam;
import aaron.user.service.pojo.vo.SystemParamItemVo;
import aaron.user.service.pojo.vo.SystemParamListVo;
import aaron.user.service.pojo.vo.SystemParamQueryVo;
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
@RequestMapping(ControllerConstants.SYSTEM_PARAM)
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class SystemParamController {
    @Autowired
    SystemParamService systemParamService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_SP)
    public CommonResponse<Boolean> saveSystemParam(@RequestBody @Valid CommonRequest<SystemParamItemVo> request){
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request.getData(),SystemParamDto.class);
        systemParamService.save(systemParamDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_SP)
    public CommonResponse<Boolean> updateSystemParam(@RequestBody @Valid CommonRequest<SystemParamItemVo> request){
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request.getData(),SystemParamDto.class);
        systemParamDto.setOldVersion(systemParamDto.getVersion());
        systemParamService.update(systemParamDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_SP)
    public CommonResponse<Boolean> deleteSystemParam(@RequestBody @Valid CommonRequest<List<SystemParamItemVo>> request){
        List<SystemParamDto> systemParamDtoList = CommonUtils.convertList(request.getData(),SystemParamDto.class);
        systemParamService.delete(systemParamDtoList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_SP)
    public CommonResponse<Map> querySystemParam(@RequestBody @Valid CommonRequest<SystemParamQueryVo> request){
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request.getData(),SystemParamDto.class);
        Page<SystemParamListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<SystemParamListVo> systemParamList = systemParamService.queryByCondition(systemParamDto);
        Map map = PageMapUtil.getPageMap(systemParamList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_SP)
    public CommonResponse<SystemParamListVo> getUpdateFormSystemParam(@RequestBody @Valid CommonRequest<Long> request){
        SystemParam systemParam = systemParamService.getById(request.getData());
        SystemParamListVo systemParamListVo = CommonUtils.copyProperties(systemParam,SystemParamListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,systemParamListVo);
    }


    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_LIST_SP)
    public CommonResponse<List> getTreeSystemParam(){
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,systemParamService.getQueryListData());
    }
}
