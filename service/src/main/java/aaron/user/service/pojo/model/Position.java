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
 * t_position
 * @author
 */
@Data
@Accessors(chain = true)
public class Position extends Model<Position> implements Serializable {
    private static final long serialVersionUID = 8834918461504763913L;
    /**
     * 职位ID
     */
    private Long id;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 职位名称
     */
    private String name;

    /**
     * 职位编号
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
     *所属公司
     */
    @Transient
    private String companyName;

    @Transient
    private Long judgeId;

    @Transient
    private Long oldVersion;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
