package aaron.user.service.pojo.model;

import java.io.Serializable;


public class RoleUser implements Serializable {
    private static final long serialVersionUID = 2695283492843191474L;
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
        return "RoleUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", companyId=" + companyId +
                '}';
    }
}
