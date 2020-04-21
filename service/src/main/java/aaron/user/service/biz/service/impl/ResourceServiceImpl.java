package aaron.user.service.biz.service.impl;

import aaron.common.aop.annotation.FullCommonFieldU;
import aaron.common.aop.enums.EnumOperation;
import aaron.common.utils.CommonUtils;
import aaron.user.api.dto.ResourceDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.biz.dao.ResourceDao;
import aaron.user.service.biz.service.ResourceService;
import aaron.user.service.common.exception.UserError;
import aaron.user.service.common.exception.UserException;
import aaron.user.service.pojo.model.Resource;
import aaron.user.service.pojo.model.TreeList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {
    @FullCommonFieldU
    @Override
    public boolean save(ResourceDto resourceDto) {
        Resource resource = CommonUtils.copyProperties(resourceDto,Resource.class);
        if (save(resource)){
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<Resource> resources) {
        if (baseMapper.getLeafCount(resources) > 0){
            throw new UserException(UserError.RESOURCE_IS_IN_USE);
        }
        if (baseMapper.deleteByIdList(resources) == resources.size()){
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(ResourceDto resourceDto) {
        Resource resource = CommonUtils.copyProperties(resourceDto,Resource.class);
        if (baseMapper.updateResource(resource) == 1){
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Override
    public List<Resource> list(ResourceDto resourceDto) {
        Resource resource = CommonUtils.copyProperties(resourceDto,Resource.class);
        return baseMapper.query(resource);
    }

    @Override
    public List<TreeListDto> getQueryList() {
        List<Resource> resourceList = list();
        return CommonUtils.convertList(resourceList,TreeListDto.class);
    }

    @Override
    public List<Resource> listByIdList(List<Long> idList) {
        return baseMapper.listByIdList(idList);
    }
}

