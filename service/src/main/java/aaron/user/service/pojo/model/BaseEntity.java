package aaron.user.service.pojo.model;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/9/19
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -469377387563716037L;
    @Transient
    private Long judgeId;

    @Transient
    private Long oldVersion;

    public Long getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Long judgeId) {
        this.judgeId = judgeId;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "judgeId=" + judgeId +
                ", oldVersion=" + oldVersion +
                '}';
    }

    public Long getOldVersion() {
        return oldVersion;
    }

    public void setOldVersion(Long oldVersion) {
        this.oldVersion = oldVersion;
    }
}
