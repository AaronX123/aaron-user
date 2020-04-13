package aaron.user.service.pojo.vo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class SystemParamListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -3209458874602625119L;
    /**
     * 系统参数ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orgId;

    /**
     * 参数类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paramType;

    private String paramTypeName;

    @Override
    public String toString() {
        return "SystemParamListVo{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", paramType='" + paramType + '\'' +
                ", paramTypeName='" + paramTypeName + '\'' +
                ", param='" + param + '\'' +
                ", value='" + value + '\'' +
                ", status=" + status +
                '}';
    }

    /**
     * 参数项
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

    /**
     * 状态位
     */
    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getParamType() {
        return paramType;
    }

    public void setParamType(Long paramType) {
        this.paramType = paramType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public SystemParamListVo() {
    }

    public String getParamTypeName() {
        return paramTypeName;
    }

    public void setParamTypeName(String paramTypeName) {
        this.paramTypeName = paramTypeName;
    }
}
