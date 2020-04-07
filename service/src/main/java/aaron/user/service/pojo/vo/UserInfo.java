package aaron.user.service.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author LH
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于获取用户的基本信息
 */
public class UserInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String profilePicture;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
