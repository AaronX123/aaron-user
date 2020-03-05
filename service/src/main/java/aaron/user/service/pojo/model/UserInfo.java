package aaron.user.service.pojo.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = -5441728009439347597L;
    private String name;
    private String profilePicture;
    private Long roleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
