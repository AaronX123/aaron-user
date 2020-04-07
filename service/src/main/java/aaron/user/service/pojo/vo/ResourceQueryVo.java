package aaron.user.service.pojo.vo;

import aaron.common.data.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class ResourceQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -2632967430805439069L;
    /**
     * 节点编号
     */
    private Long id;

    /**
     * 父亲节点
     */
    private Long parentId;
    public ResourceQueryVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ResourceQueryVo{" +
                "id='" + id + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
