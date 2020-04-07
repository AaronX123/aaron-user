package aaron.user.service.common.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
public enum  UserError {
    DATA_NOT_EXIST("020001","数据不存在");

    ;
    String msg;
    String code;

    UserError(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
