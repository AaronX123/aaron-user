package aaron.user.service.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

public class UserForRoleListVo implements Serializable {
    private static final long serialVersionUID = -8031985969008809753L;
    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户工号
     */
    private String code;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户角色
     */
    private String roleName;
    /**
     * 用户部门
     */
    private String departmentName;
    /**
     * 用户职位
     */
    private String positionName;
    /**
     * 标记位：是否已有此角色
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserForRole{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", flag=" + flag +
                '}';
    }
}
