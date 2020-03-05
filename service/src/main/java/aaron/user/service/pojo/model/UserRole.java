package aaron.user.service.pojo.model;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/9/11
 */
@Table(name = "t_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -7021545227347983885L;
    private Long id;
    private Long userId;
    private Long roleId;

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

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
