package aaron.user.service.pojo.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class PositionItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -1472445396328905944L;
    /**
     * 职位ID
     */
    private Long id;

    /**
     * 公司ID
     */
    @NotNull(message = "公司不能为空")
    private Long companyId;

    /**
     * 职位名称
     */
    @NotNull(message = "职位不能为空")
    private String name;

    /**
     * 职位编号
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Byte status;

    @Override
    public String toString() {
        return "PositionItemVO{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public PositionItemVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}
