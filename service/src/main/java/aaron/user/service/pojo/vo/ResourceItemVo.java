package aaron.user.service.pojo.vo;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class ResourceItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -8678302067843260897L;
    /**
     * 资源ID
     */
    private Long id;

    /**
     * 节点名称
     */
    @NotNull(message = "节点名称不能为空")
    private String name;

    /**
     * 编号
     */
    @NotNull(message = "节点编号不能为空")
    private String code;

    /**
     * 顺序号
     */
    @NotNull(message = "顺序号不能为空")
    private Integer orderIndex;

    /**
     * 父亲节点
     */
    private Long parentId;

    /**
     * URL
     */
    @NotNull(message = "url不能为空")
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
    @NotNull(message = "资源类型不能为空")
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

    public ResourceItemVo() {
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
    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
    @Override
    public String toString() {
        return "ResourceItemVo{" +
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
}
