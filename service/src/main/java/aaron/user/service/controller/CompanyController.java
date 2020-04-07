package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.CompanyDto;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.vo.CompanyItemVo;
import aaron.user.service.pojo.vo.CompanyListVo;
import aaron.user.service.pojo.vo.CompanyQueryVo;
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
@RequestMapping(ControllerConstants.COMPANY)
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE)
    public CommonResponse<Boolean> save(@RequestBody @Valid CommonRequest<CompanyItemVo> request){
        CompanyDto dto = CommonUtils.copyProperties(request.getData(),CompanyDto.class);
        companyService.save(dto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.DELETE)
    public CommonResponse<Boolean> delete(@RequestBody @Valid CommonRequest<List<CompanyItemVo>> request){
        List<CompanyItemVo> itemVoList = request.getData();
        List<Long> idList = itemVoList.stream().map(CompanyItemVo::getId).collect(Collectors.toList());
        companyService.removeByIds(idList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.UPDATE)
    public CommonResponse<Boolean> update(@RequestBody @Valid CommonRequest<CompanyItemVo> request){
        CompanyDto dto = CommonUtils.copyProperties(request.getData(),CompanyDto.class);
        dto.setOldVersion(request.getData().getVersion());
        companyService.update(dto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_UPDATE_FORM)
    public CommonResponse<CompanyListVo> getUpdateForm(@RequestBody @Valid CommonRequest<Long> request){
        Company company = companyService.getById(request.getData());
        if (company == null){
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        CompanyListVo listVo = CommonUtils.copyProperties(company,CompanyListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,listVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY)
    public CommonResponse<Map> query(@RequestBody @Valid CommonRequest<CompanyQueryVo> request){
        CompanyDto dto = CommonUtils.copyProperties(request.getData(),CompanyDto.class);
        dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<CompanyListVo> page = PageHelper.startPage(dto.getCurrentPage(),dto.getPageSize());
        List<Company> companyList = companyService.queryCompany(dto);
        List<CompanyListVo> listVoList = CommonUtils.convertList(companyList,CompanyListVo.class);
        Map map = PageMapUtil.getPageMap(listVoList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }
}
