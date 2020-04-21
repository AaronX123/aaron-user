package aaron.user.service.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * t_resource
 * @author
 */
@Data
@Accessors(chain = true)
public class Resource extends Model<Resource> implements Serializable {
    private static final long serialVersionUID = -4681421911850865447L;
    /**
     * 资源ID
     */
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
     * 父亲节点
     */
    private Long parentId;

    /**
     * URL
     */
    private String url;

    /**
     * 顺序号
     */
    private Integer orderIndex;

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

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private Long updatedBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 版本
     */
    private Long version;

    /**
     * 乐观锁机制
     */
    @TableField(exist = false)
    private Long oldVersion;
    @Override
    protected Serializable pkVal() {
        return id;
    }
}
