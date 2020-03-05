package aaron.user.api.dto;


import aaron.common.data.common.BaseDto;

import java.io.Serializable;

/**
 * t_system_param
 * @author
 */
public class SystemParamDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1271364634275015815L;
    /**
     * 系统参数ID
     */
    private Long id;

    /**
     * 组织机构ID
     */
    private Long orgId;

    /**
     * 参数类型
     */
    private Long paramType;

    private String paramTypeName;

    /**
     * 参数项
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

    @Override
    public String toString() {
        return "SystemParamDto{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", paramType='" + paramType + '\'' +
                ", paramTypeName='" + paramTypeName + '\'' +
                ", param='" + param + '\'' +
                ", value='" + value + '\'' +
                ", status=" + status +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", judgeId=" + judgeId +
                '}';
    }

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 当前请求页
     */
    private int currentPage;

    /**
     * 页面显示条数
     */
    private int pageSize;

    private Long judgeId;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Long judgeId) {
        this.judgeId = judgeId;
    }

    public Long getParamType() {
        return paramType;
    }

    public void setParamType(Long paramType) {
        this.paramType = paramType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getParamTypeName() {
        return paramTypeName;
    }

    public void setParamTypeName(String paramTypeName) {
        this.paramTypeName = paramTypeName;
    }
}
