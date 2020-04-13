package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.OrganizationDto;
import aaron.user.service.biz.service.OrganizationService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.pojo.model.Organization;
import aaron.user.service.pojo.vo.OrganizationItemVo;
import aaron.user.service.pojo.vo.OrganizationListVo;
import aaron.user.service.pojo.vo.OrganizationQueryVo;
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

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.ORG)
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE)
    public CommonResponse<Boolean> save(@RequestBody @Valid CommonRequest<OrganizationItemVo> request){
        OrganizationDto organizationDto = CommonUtils.copyProperties(request.getData(),OrganizationDto.class);
        organizationService.save(organizationDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.DELETE)
    public CommonResponse<Boolean> delete(@RequestBody @Valid CommonRequest<List<OrganizationItemVo>> request){
        List<OrganizationDto> dtoList = CommonUtils.convertList(request.getData(),OrganizationDto.class);
        organizationService.delete(dtoList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.UPDATE)
    public CommonResponse<Boolean> update(@RequestBody @Valid CommonRequest<OrganizationItemVo> request){
        OrganizationDto organizationDto = CommonUtils.copyProperties(request.getData(),OrganizationDto.class);
        organizationDto.setOldVersion(organizationDto.getVersion());
        organizationService.update(organizationDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY)
    public CommonResponse<Map> query(@RequestBody CommonRequest<OrganizationQueryVo> request){
        OrganizationDto dto = CommonUtils.copyProperties(request.getData(),OrganizationDto.class);
        Page<OrganizationListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<Organization> organizationList = organizationService.list(dto);
        Map<String,Object> pageMap = PageMapUtil.getPageMap(organizationList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,pageMap);
    }
}
