package aaron.user.service.pojo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author LuoHui
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeList implements Serializable {
    private static final long serialVersionUID = -2740873114126068062L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long rootId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;

    @Override
    public String toString() {
        return "TreeList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", rootId=" + rootId +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeList treeList = (TreeList) o;
        return Objects.equals(id, treeList.id) &&
                Objects.equals(name, treeList.name) &&
                Objects.equals(parentId, treeList.parentId) &&
                Objects.equals(rootId, treeList.rootId) &&
                Objects.equals(version, treeList.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, rootId, version);
    }
}
