package aaron.user.service.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class OrganizationListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -5966561480948201344L;
    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 机构名
     */
    private String name;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 负责人
     */
    private String master;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态位
     */
    private Byte status;


    public OrganizationListVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrganizationListVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", master='" + master + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
