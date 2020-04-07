package aaron.user.service.pojo.vo;

import java.io.Serializable;

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
    public String toString() {
        return "RoleResourceVo{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", type=" + type +
                '}';
    }
}
