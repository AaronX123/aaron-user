package aaron.user.service.pojo.vo;

import aaron.common.data.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class PositionQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -5311406730359216485L;
    /**
     * 职位ID
     */
    private Long id;
    /**
     * 职位名
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionQueryVo() {
    }

    @Override
    public String toString() {
        return "PositionQueryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
