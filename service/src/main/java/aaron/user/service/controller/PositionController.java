package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.PositionDto;
import aaron.user.service.biz.service.PositionService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.Position;
import aaron.user.service.pojo.vo.PositionItemVo;
import aaron.user.service.pojo.vo.PositionListVo;
import aaron.user.service.pojo.vo.PositionQueryVo;
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
@RequestMapping(ControllerConstants.POSITION)
public class PositionController {
    @Autowired
    PositionService positionService;

    @Autowired
    CommonState state;


    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE)
    public CommonResponse<Boolean> save(@RequestBody @Valid CommonRequest<PositionItemVo> request){
        PositionDto positionDto = CommonUtils.copyProperties(request.getData(),PositionDto.class);
        positionService.save(positionDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.UPDATE)
    public CommonResponse<Boolean> update(@RequestBody @Valid CommonRequest<PositionItemVo> request){
        PositionDto positionDto = CommonUtils.copyProperties(request.getData(),PositionDto.class);
        positionDto.setOldVersion(positionDto.getVersion());
        positionService.update(positionDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.DELETE)
    public CommonResponse<Boolean> delete(@RequestBody @Valid CommonRequest<List<PositionItemVo>> request){
        List<Position> positionList = CommonUtils.convertList(request.getData(),Position.class);
        positionService.delete(positionList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_UPDATE_FORM)
    public CommonResponse<PositionListVo> getUpdateForm(@RequestBody @Valid CommonRequest<Long> request){
        Position position = positionService.getById(request.getData());
        PositionListVo positionListVo = CommonUtils.copyProperties(position,PositionListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,positionListVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY)
    public CommonResponse<Map> query(@RequestBody @Valid CommonRequest<PositionQueryVo> request){
        Position position = CommonUtils.copyProperties(request.getData(),Position.class);
        position.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<PositionListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<Position> positionList = positionService.list(position);
        List<PositionListVo> listVos = CommonUtils.convertList(positionList,PositionListVo.class);
        Map map = PageMapUtil.getPageMap(listVos,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_OPTIONS)
    public CommonResponse<List> queryCompOptions(){
        List<Position> positionList = positionService.listCompany();
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,CommonUtils.convertList(positionList,PositionListVo.class));
    }
}
