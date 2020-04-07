package aaron.user.service.pojo.vo;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class BaseVo {
    /**
     * 版本号
     */
    private Long version;

    public BaseVo() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                ", version=" + version +
                '}';
    }
}
