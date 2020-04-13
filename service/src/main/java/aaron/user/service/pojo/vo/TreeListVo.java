package aaron.user.service.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author xym
 */
public class TreeListVo implements Serializable {
    private static final long serialVersionUID = -1830434213241855994L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long rootId;
    @JsonSerialize(using = ToStringSerializer.class)
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

    public Long getRootId() {
        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TreeListVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", rootId=" + rootId +
                ", version=" + version +
                '}';
    }
}
