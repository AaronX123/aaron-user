package aaron.user.service.biz.service;

import aaron.user.api.dto.SystemParamDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.pojo.model.SystemParam;
import aaron.user.service.pojo.vo.SystemParamListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface SystemParamService extends IService<SystemParam> {
    boolean save(SystemParamDto systemParamDto);

    boolean delete(List<SystemParamDto> systemParamDto);

    boolean update(SystemParamDto systemParamDto);

    /**
     * 根据请求条件查询符合条件的参数记录集合
     * @param systemParamDTO 请求条件查询信息
     * @return
     */
    List<SystemParamListVo> queryByCondition(SystemParamDto systemParamDTO);


    /**
     * 获取参数树集合
     * @return 以树（treelist）形式返回数据
     */
    List<TreeListDto> getQueryListData();

}
