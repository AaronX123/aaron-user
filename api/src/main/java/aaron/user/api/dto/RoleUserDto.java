package aaron.user.api.dto;

import java.io.Serializable;

/**
 * @author LH
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于角色页面分配用户
 */
public class RoleUserDto implements Serializable {
    private static final long serialVersionUID = -4555168027630926134L;
    private Long id;
    private Long userId;
    private Long roleId;
    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "RoleUserDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", companyId=" + companyId +
                '}';
    }
}
