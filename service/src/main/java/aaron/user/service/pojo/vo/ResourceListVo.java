package aaron.user.service.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class ResourceListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 4163387454235973715L;
    /**
     * 资源ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 顺序号
     */
    private Integer orderIndex;

    /**
     * 父亲节点
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * URL
     */
    private String url;

    /**
     * 打开图标
     */
    private String openImg;

    /**
     * 关闭图标
     */
    private String closeImg;

    /**
     * 资源类型
     */
    private Byte resourceType;

    /**
     * 叶子节点
     */
    private Byte leaf;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Byte status;

    public ResourceListVo() {
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpenImg() {
        return openImg;
    }

    public void setOpenImg(String openImg) {
        this.openImg = openImg;
    }

    public String getCloseImg() {
        return closeImg;
    }

    public void setCloseImg(String closeImg) {
        this.closeImg = closeImg;
    }

    public Byte getResourceType() {
        return resourceType;
    }

    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

    public Byte getLeaf() {
        return leaf;
    }

    public void setLeaf(Byte leaf) {
        this.leaf = leaf;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResourceListVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", orderIndex=" + orderIndex +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                ", openImg='" + openImg + '\'' +
                ", closeImg='" + closeImg + '\'' +
                ", resourceType=" + resourceType +
                ", leaf=" + leaf +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}
