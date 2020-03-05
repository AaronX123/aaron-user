package aaron.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 用户Id,名称
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    Map<Long,String> nameMap;
}
