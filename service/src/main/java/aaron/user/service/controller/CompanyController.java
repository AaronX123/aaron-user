package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.CompanyDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.service.CompanyService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Company;
import aaron.user.service.pojo.vo.CompanyItemVo;
import aaron.user.service.pojo.vo.CompanyListVo;
import aaron.user.service.pojo.vo.CompanyQueryVo;
import aaron.user.service.pojo.vo.TreeListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_C)
    public CommonResponse<Boolean> saveCompany(@RequestBody @Valid CommonRequest<CompanyItemVo> request){
        CompanyDto dto = CommonUtils.copyProperties(request.getData(),CompanyDto.class);
        companyService.save(dto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DELETE_C)
    public CommonResponse<Boolean> deleteCompany(@RequestBody @Valid CommonRequest<List<CompanyItemVo>> request){
        List<CompanyItemVo> itemVoList = request.getData();
        List<Long> idList = itemVoList.stream().map(CompanyItemVo::getId).collect(Collectors.toList());
        companyService.removeByIds(idList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_C)
    public CommonResponse<Boolean> updateCompany(@RequestBody @Valid CommonRequest<CompanyItemVo> request){
        CompanyDto dto = CommonUtils.copyProperties(request.getData(),CompanyDto.class);
        dto.setOldVersion(request.getData().getVersion());
        companyService.update(dto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UPDATE_FORM_C)
    public CommonResponse<CompanyListVo> getUpdateFormCompany(@RequestBody @Valid CommonRequest<Long> request){
        Company company = companyService.getById(request.getData());
        if (company == null){
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        CompanyListVo listVo = CommonUtils.copyProperties(company,CompanyListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,listVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_C)
    public CommonResponse<Map> queryCompany(@RequestBody @Valid CommonRequest<CompanyQueryVo> request){
        CompanyDto dto = CommonUtils.copyProperties(request.getData(),CompanyDto.class);
        dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<CompanyListVo> page = PageHelper.startPage(dto.getCurrentPage(),dto.getPageSize());
        List<Company> companyList = companyService.queryCompany(dto);
        List<CompanyListVo> listVoList = CommonUtils.convertList(companyList,CompanyListVo.class);
        Map map = PageMapUtil.getPageMap(listVoList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,map);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_COMPANY_LIST)
    public CommonResponse<List> getCompanyList(){
        Long judgeId = CommonUtils.judgeCompanyAndOrg();
        List<TreeListDto> dtoList = companyService.getCompanyTree(judgeId);
        List<TreeListVo> voList = CommonUtils.convertList(dtoList,TreeListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,voList);
    }

}
