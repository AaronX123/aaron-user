package aaron.user.service.controller;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.logging.annotation.MethodEnhancer;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.PageMapUtil;
import aaron.user.api.dto.DepartmentDto;
import aaron.user.service.biz.service.DepartmentService;
import aaron.user.service.common.constants.ControllerConstants;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Department;
import aaron.user.service.pojo.model.TreeList;
import aaron.user.service.pojo.vo.DepartmentItemVo;
import aaron.user.service.pojo.vo.DepartmentListVo;
import aaron.user.service.pojo.vo.DepartmentQueryVo;
import aaron.user.service.pojo.vo.TreeListVo;
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
@RequestMapping(ControllerConstants.DEPARTMENT)
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true",methods = {})
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_D)
    public CommonResponse<Boolean> saveDepartment(@RequestBody @Valid CommonRequest<DepartmentItemVo> request){
        DepartmentDto departmentDto = CommonUtils.copyProperties(request.getData(),DepartmentDto.class);
        departmentService.save(departmentDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }


    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_D)
    public CommonResponse<Boolean> updateDepartment(@RequestBody @Valid CommonRequest<DepartmentItemVo> request){
        DepartmentDto departmentDto = CommonUtils.copyProperties(request.getData(),DepartmentDto.class);
        departmentDto.setOldVersion(departmentDto.getVersion());
        departmentService.update(departmentDto);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_D)
    public CommonResponse<Boolean> deleteDepartment(@RequestBody @Valid CommonRequest<List<DepartmentItemVo>> request){
        List<DepartmentItemVo> itemVos = request.getData();
        List<DepartmentDto> departmentDtoList = CommonUtils.convertList(itemVos,DepartmentDto.class);
        departmentService.delete(departmentDtoList);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_D)
    public CommonResponse<DepartmentListVo> getUpdateFormDepartment(@RequestBody CommonRequest<Long> request){
        Department department = departmentService.getById(request.getData());
        if (department == null){
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        DepartmentListVo departmentListVo = CommonUtils.copyProperties(department,DepartmentListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,departmentListVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_D)
    public CommonResponse<Map> queryDepartment(@RequestBody @Valid CommonRequest<DepartmentQueryVo> request){
        Department department = CommonUtils.copyProperties(request.getData(),Department.class);
        department.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<DepartmentListVo> page = PageHelper.startPage(request.getData().getCurrentPage(),request.getData().getTotalPages());
        List<Department> list = departmentService.query(department);
        List<DepartmentListVo> voList = CommonUtils.convertList(list,DepartmentListVo.class);
        Map<String,Object> pageMap = PageMapUtil.getPageMap(voList,page);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,pageMap);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_DEP_LEVEL)
    public CommonResponse<List> queryLevelDepartment(){
        List<Department> departmentList = departmentService.queryLevel();
        List<DepartmentQueryVo> res = CommonUtils.convertList(departmentList,DepartmentQueryVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,res);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_DEP_PARENT)
    public CommonResponse<List> queryParentDepartment(){
        List<Department> departmentList = departmentService.queryParent();
        List<DepartmentQueryVo> res = CommonUtils.convertList(departmentList,DepartmentQueryVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,res);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_DEP_TREE_DATA)
    public CommonResponse<List> queryTreeDataDepartment(){
        List<TreeList> departmentList = departmentService.queryTreeData();
        List<TreeListVo> res = CommonUtils.convertList(departmentList,TreeListVo.class);
        return new CommonResponse<>(state.SUCCESS,state.SUCCESS_MSG,res);
    }

}
