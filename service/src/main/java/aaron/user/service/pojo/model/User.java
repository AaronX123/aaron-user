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
 * t_user
 * @author
 */
@Data
@Accessors(chain = true)
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 141927995680505569L;
    /**
     * 用户ID
     */
    @Id
    private Long id;

    /**
     * 职位ID
     */
    private Long positionId;

    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 公司Id
     */
    private Long companyId;

    /**
     * 工号
     */
    private String code;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String profilePicture;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 其他联系
     */
    private String other;

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
     * 部门
     */
    @TableField(exist = false)
    private String departmentName;

    /**
     * 角色ID
     */
    @TableField(exist = false)
    private Long roleId;

    /**
     * 职位
     */
    @TableField(exist = false)
    private String positionName;

    /**
     * 角色
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * 公司
     */
    @TableField(exist = false)
    private String companyName;

    @TableField(exist = false)
    private Long judgeId;

    @TableField(exist = false)
    private Long oldVersion;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
