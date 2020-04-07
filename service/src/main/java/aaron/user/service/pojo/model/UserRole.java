package aaron.user.service.pojo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/9/11
 */
@Data
@Accessors(chain = true)
public class UserRole extends Model<UserRole> implements Serializable {
    private static final long serialVersionUID = -7021545227347983885L;
    private Long id;
    private Long userId;
    private Long roleId;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
