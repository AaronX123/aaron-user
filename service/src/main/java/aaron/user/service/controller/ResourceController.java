package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.ResourceDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.service.ResourceService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.Resource;
import aaron.user.service.pojo.vo.ResourceItemVo;
import aaron.user.service.pojo.vo.ResourceListVo;
import aaron.user.service.pojo.vo.ResourceQueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.RESOURCE)
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE)
    public CommonResponse<Boolean> save(@RequestBody @Valid CommonRequest<ResourceItemVo> request){
        ResourceDto resourceDto = CommonUtils.copyProperties(request.getData(),ResourceDto.class);
        resourceService.save(resourceDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_UPDATE_FORM)
    public CommonResponse<ResourceListVo> queryUpdateForm(@RequestBody CommonRequest<Long> request){
        Resource resource = resourceService.getById(request.getData());
        ResourceListVo resourceListVo = CommonUtils.copyProperties(resource,ResourceListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,resourceListVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.DELETE)
    public CommonResponse<Boolean> delete(@RequestBody CommonRequest<List<ResourceItemVo>> request){
        List<Resource> resourceList = CommonUtils.convertList(request.getData(),Resource.class);
        resourceService.delete(resourceList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.UPDATE)
    public CommonResponse<Boolean> update(@RequestBody CommonRequest<ResourceItemVo> request){
        ResourceDto resourceDto = CommonUtils.copyProperties(request.getData(),ResourceDto.class);
        resourceService.update(resourceDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY)
    public CommonResponse<Map> query(@RequestBody CommonRequest<ResourceQueryVo> request){
        ResourceDto resourceDto = CommonUtils.copyProperties(request.getData(),ResourceDto.class);
        Page<ResourceListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<Resource> resourceList = resourceService.list(resourceDto);
        List<ResourceListVo> listVos = CommonUtils.convertList(resourceList,ResourceListVo.class);
        Map map = PageMapUtil.getPageMap(listVos,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_RESOURCE_LIST)
    public CommonResponse<List<TreeListDto>> getResourceList(@RequestBody CommonRequest<ResourceQueryVo> request){
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,resourceService.getQueryList());
    }
}
