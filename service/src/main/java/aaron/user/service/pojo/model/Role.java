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
 * t_role
 * @author
 */
@Data
@Accessors(chain = true)
public class Role extends Model<Role> implements Serializable {
    private static final long serialVersionUID = -4264166198464071246L;
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 组织机构ID
     */
    private Long orgId;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色代码
     */
    private String code;

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
     * 所属机构
     */
    @TableField(exist = false)
    private String orgName;

    /**
     *所属公司
     */
    @TableField(exist = false)
    private String companyName;

    /**
     * 资源节点ID
     */
    @TableField(exist = false)
    private Long resourceId;

    /**
     * 用户ID
     */
    @TableField(exist = false)
    private Long userId;

    @TableField(exist = false)
    private Long judgeId;

    @TableField(exist = false)
    private Long oldVersion;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
