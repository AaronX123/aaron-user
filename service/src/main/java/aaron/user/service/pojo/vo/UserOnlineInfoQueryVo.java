package aaron.user.service.pojo.vo;

import aaron.common.data.common.BaseQueryVo;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */

public class UserOnlineInfoQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -1078197618140529119L;
    /**
     * 工号
     */
    private String code;
    /**
     * 用户名
     */
    private String name;
    /**
     * 上线时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlineTime;
    /**
     * 下线时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlineTime;

    public UserOnlineInfoQueryVo() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    @Override
    public String toString() {
        return "UserOnlineInfoQueryVo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", onlineTime=" + onlineTime +
                ", offlineTime=" + offlineTime +
                '}';
    }

}
