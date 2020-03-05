package aaron.user.api.api;

import aaron.common.data.common.CommonRequest;
import aaron.common.data.common.CommonResponse;
import aaron.user.api.constant.ApiConstant;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
public interface CompanyApi {
    /**
     * 通过公司Id获取公司名称
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_COMPANY_NAME)
    CommonResponse<String> getCompanyById(CommonRequest<Long> request);
}
