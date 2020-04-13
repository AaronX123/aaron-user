package aaron.user.service.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class PositionListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -4606367687217083037L;
    /**
     * 职位ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 公司ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 职位名称
     */
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "PositionListVo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public PositionListVo() {
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
