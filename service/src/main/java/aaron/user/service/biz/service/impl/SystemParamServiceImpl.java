package aaron.user.service.biz.service.impl;

import aaron.baseinfo.api.dto.BaseDataDto;
import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.common.data.common.CommonState;
import aaron.common.data.exception.StarterError;
import aaron.common.data.exception.StarterException;
import aaron.common.utils.CommonUtils;
import aaron.common.utils.TokenUtils;
import aaron.user.api.dto.SystemParamDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.dao.SystemParamDao;
import aaron.user.service.biz.manage.BaseInfoApi;
import aaron.user.service.biz.service.SystemParamService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.SystemParam;
import aaron.user.service.pojo.model.TreeList;
import aaron.user.service.pojo.vo.SystemParamItemVo;
import aaron.user.service.pojo.vo.SystemParamListVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class SystemParamServiceImpl extends ServiceImpl<SystemParamDao, SystemParam> implements SystemParamService {
    @Autowired
    CommonState state;

    @Resource
    BaseInfoApi baseInfoApi;


    @FullCommonFieldU
    @Override
    public boolean save(SystemParamDto systemParamDto) {
        SystemParam systemParam = CommonUtils.copyProperties(systemParamDto,SystemParam.class);
        if (save(systemParam)){
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<SystemParamDto> systemParamDto) {
        List<SystemParam> systemParamList = CommonUtils.convertList(systemParamDto,SystemParam.class);
        if (baseMapper.deleteByIdList(systemParamList) == systemParamDto.size()){
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }


    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(SystemParamDto systemParamDto) {
        SystemParam systemParam = CommonUtils.copyProperties(systemParamDto,SystemParam.class);
        if (baseMapper.update(systemParam) == 1){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    /**
     * 根据请求条件查询符合条件的参数记录集合
     *
     * @param systemParamDTO 请求条件查询信息
     * @return
     */
    @Override
    public List<SystemParamListVo> queryByCondition(SystemParamDto systemParamDTO) {
        SystemParam systemParam = CommonUtils.copyProperties(systemParamDTO,SystemParam.class);
        List<SystemParam> systemParamList = baseMapper.query(systemParam);
        if (CommonUtils.isEmpty(systemParamList)){
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        List<SystemParamListVo> listVoList = CommonUtils.convertList(systemParamList, SystemParamListVo.class);
        // 从基础数据服务获取值
        Map<Long,String> map = new HashMap<>(systemParamList.size());
        for (SystemParam param : systemParamList) {
            map.put(param.getParamType(),null);
        }
        BaseDataDto baseDataDto = new BaseDataDto(map);
        CommonRequest<BaseDataDto> request = new CommonRequest<>(state.getVersion(), TokenUtils.getToken(),baseDataDto);
        CommonResponse<BaseDataDto> response;
        try {
            response = baseInfoApi.getBaseDataS(request);
        }catch (Exception e){
            throw new StarterException(StarterError.SYSTEM_RPC_ERROR);
        }
        map = response.getData().getBaseInfoMap();
        for (SystemParamListVo vo : listVoList) {
            vo.setParamTypeName(map.get(vo.getParamType()));
        }
        return listVoList;
    }

    /**
     * 获取参数树集合
     *
     * @return 以树（treelist）形式返回数据
     */
    @Override
    public List<TreeListDto> getQueryListData() {
        List<TreeList> treeListList = baseMapper.getQueryListData();
        return CommonUtils.convertList(treeListList,TreeListDto.class);
    }
}
