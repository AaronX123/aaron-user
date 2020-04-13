package aaron.user.service.pojo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * t_department
 * @author
 */
@Data
@Accessors(chain = true)
public class Department extends Model<Department> implements Serializable {
    private static final long serialVersionUID = 664872952706433500L;
    /**
     * 部门ID
     */
    private Long id;

    /**
     * 公司ID
     */
    private Long companyId;


    /**
     * 部门名称
     */
    private String name;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 部门级别
     */
    private String level;

    /**
     * 上级部门
     */
    private Long parentId;

    /**
     * 上级部门名
     */
    @Transient
    private String parentDepartment;
    /**
     * 负责人
     */
    private String master;

    /**
     * 部门描述
     */
    private String description;

    /**
     * 状态
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

    @Transient
    private Long judgeId;

    @Transient
    private Long oldVersion;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
