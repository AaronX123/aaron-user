package aaron.user.service.pojo.vo;

import java.io.Serializable;

/**
 * @author LuoHui
 */
public class TreeQueryVo implements Serializable {
    private static final long serialVersionUID = -4160739494491823702L;
    private Long id;
    private String name;
    private Long parentId;
    private Long version;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TreeQueryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", version=" + version +
                '}';
    }

}
