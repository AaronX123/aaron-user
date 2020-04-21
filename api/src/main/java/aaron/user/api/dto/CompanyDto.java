package aaron.user.api.dto;


import aaron.common.data.common.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**
 * t_company
 * @author
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -2132644087551023977L;
    /**
     * 公司ID
     */
    private Long id;

    /**
     * 组织机构ID
     */
    private Long orgId;

    /**
     * 公司名
     */
    private String name;

    /**
     * 公司编号
     */
    private String code;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 法人
     */
    private String master;

    /**
     * 税号
     */
    private String tax;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 所属机构
     */
    private String orgName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String website;

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 当前请求页
     */
    private int currentPage;
    /**
     * 页面显示条数
     */
    private int pageSize;

    private Long judgeId;

}
