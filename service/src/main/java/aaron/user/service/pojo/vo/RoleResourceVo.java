package aaron.user.service.pojo.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author LH
 * @version V1.0.0
 * @date 2019/9/11
 * @describe 用于资源分配
 */
public class RoleResourceVo implements Serializable {
    private static final long serialVersionUID = -5392841873474130071L;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleResourceVo that = (RoleResourceVo) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, resourceId);
    }

    @Override
    public String toString() {
        return "RoleResourceVo{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", type=" + type +
                '}';
    }
}
