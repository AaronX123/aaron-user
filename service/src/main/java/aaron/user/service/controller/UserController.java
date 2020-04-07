package aaron.user.service.controller;

import aaron.user.service.common.constants.ControllerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Slf4j
@RestController
@RequestMapping(ControllerConstants.USER)
public class UserController {
    @Autowired
    CacheManager cacheManager;

    public void test(){
        Cache cache = cacheManager.getCache("缓存的名称");
        Cache.ValueWrapper wrapper = cache.get("缓存的KEY");
        Object value = wrapper.get();
    }
}
