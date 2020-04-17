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
@RequestMapping(ControllerConstants.ORG)
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_O)
    public CommonResponse<Boolean> save(@RequestBody @Valid CommonRequest<OrganizationItemVo> request){
        OrganizationDto organizationDto = CommonUtils.copyProperties(request.getData(),OrganizationDto.class);
        organizationService.save(organizationDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.DEL_O)
    public CommonResponse<Boolean> delete(@RequestBody @Valid CommonRequest<List<OrganizationItemVo>> request){
        List<OrganizationDto> dtoList = CommonUtils.convertList(request.getData(),OrganizationDto.class);
        organizationService.delete(dtoList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.UPDATE_O)
    public CommonResponse<Boolean> update(@RequestBody @Valid CommonRequest<OrganizationItemVo> request){
        OrganizationDto organizationDto = CommonUtils.copyProperties(request.getData(),OrganizationDto.class);
        organizationDto.setOldVersion(organizationDto.getVersion());
        organizationService.update(organizationDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_O)
    public CommonResponse<Map> query(@RequestBody CommonRequest<OrganizationQueryVo> request){
        OrganizationDto dto = CommonUtils.copyProperties(request.getData(),OrganizationDto.class);
        Page<OrganizationListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<Organization> organizationList = organizationService.list(dto);
        Map<String,Object> pageMap = PageMapUtil.getPageMap(organizationList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,pageMap);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF)
    public CommonResponse<OrganizationListVo> getUpdateForm(@RequestBody CommonRequest<Long> request){
        Organization organization = organizationService.getById(request.getData());
        OrganizationListVo organizationListVo = CommonUtils.copyProperties(organization,OrganizationListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,organizationListVo);
    }
}
