package aaron.user.service.pojo.model;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author CJF
 */
@Table(name = "t_role_resource")
public class RoleResource implements Serializable {
    private static final long serialVersionUID = -5559929207907784262L;
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
