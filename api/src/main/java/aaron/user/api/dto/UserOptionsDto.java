package aaron.user.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/9/11
 */
public class UserOptionsDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    private String roleName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long positionId;
    private String positionName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserOptionsDto{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
