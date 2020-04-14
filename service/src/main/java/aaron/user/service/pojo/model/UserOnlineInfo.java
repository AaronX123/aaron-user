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
 * t_user_online_info
 * @author
 */
@Data
@Accessors(chain = true)
public class UserOnlineInfo extends Model<UserOnlineInfo> implements Serializable {
    private static final long serialVersionUID = -1214689941310859787L;
    /**
     * 在线ID
     */
    @Id
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 工号
     */
    private String code;

    /**
     * 用户名
     */
    private String name;

    /**
     * IP
     */
    private String ip;

    /**
     * 上线时间
     */
    private Date onlineTime;

    /**
     * 下线时间
     */
    private Date offlineTime;

    /**
     * 在线时长
     */
    private Integer stopTime;

    /**
     * 状态位
     */
    private Byte status;

    @Transient
    private Long judgeId;

    @Transient
    private Long oldVersion;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
