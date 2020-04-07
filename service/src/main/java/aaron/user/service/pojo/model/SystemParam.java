package aaron.user.service.pojo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_system_param
 * @author
 */
@Data
@Accessors(chain = true)
public class SystemParam extends Model<SystemParam> implements Serializable {
    private static final long serialVersionUID = 2029025586921403663L;
    /**
     * 系统参数ID
     */
    @Id
    private Long id;

    /**
     * 组织机构ID
     */
    private Long orgId;

    /**
     * 参数类型
     */
    private Long paramType;

    /**
     * 参数项
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

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

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
