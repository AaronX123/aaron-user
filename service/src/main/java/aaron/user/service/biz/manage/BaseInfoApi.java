package aaron.user.service.biz.manage;

import aaron.baseinfo.api.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-14
 */
@FeignClient(ApiConstant.SERVICE_NAME)
public interface BaseInfoApi extends aaron.baseinfo.api.api.BaseInfoApi {
}
