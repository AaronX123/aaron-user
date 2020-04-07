package aaron.user.service.pojo.vo;

import bosssoft.hr.train.core.data.common.BaseQueryVO;

import java.io.Serializable;

public class UserAlloctionQuseryVo extends BaseQueryVO implements Serializable {
    private static final long serialVersionUID = -4104146631752309115L;
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 用户工号
     */
    private String code;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 公司Id
     */
    private Long companyId;
    /**
     * 组织机构ID
     */
    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "UserAlloctionQuseryVo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", orgId=" + orgId +
                '}';
    }
}
