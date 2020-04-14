package aaron.user.service.biz.service;

import aaron.user.api.dto.ResourceDto;
import aaron.user.api.dto.TreeListDto;
import aaron.user.service.pojo.model.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface ResourceService extends IService<Resource> {
    boolean save(ResourceDto resourceDto);

    boolean delete(List<Resource> resources);

    boolean update(ResourceDto resourceDto);

    List<Resource> list(ResourceDto resourceDto);

    List<TreeListDto> getQueryList();
}
