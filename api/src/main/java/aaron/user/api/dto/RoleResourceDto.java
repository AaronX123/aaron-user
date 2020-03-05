package aaron.user.api.dto;

import java.io.Serializable;

/**
 * @author acer
 */
public class RoleResourceDto implements Serializable {
    private static final long serialVersionUID = -1857819275686602576L;
    private Long id;
    private Long roleId;
    private Long resourceId;
    private Byte type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoleResourceVO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", type=" + type +
                '}';
    }
}
