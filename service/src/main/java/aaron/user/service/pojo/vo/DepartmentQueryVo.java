package aaron.user.service.pojo.vo;

import aaron.common.data.common.BaseQueryVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CJF
 * @version V1.0.0
 * @date 2019/8/28
 */
public class DepartmentQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -7089081877518720169L;
    /**
     * 部门id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 部门名
     */
    @NotNull(message = "部门名不能为空")
    private String name;
    /**
     * 部门等级
     */
    @NotNull(message = "部门等级不能为空")
    private String level;

    public DepartmentQueryVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DepartmentQueryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
