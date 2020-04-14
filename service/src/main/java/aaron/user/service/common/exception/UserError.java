package aaron.user.service.common.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
public enum  UserError {
    DATA_NOT_EXIST("020001","数据不存在"),
    SAVE_FAIL("020002","保存失败"),
    UPDATE_FAIL("020003","更新失败"),
    DELETE_FAIL("020004","删除失败"),

    EXIST_SUB_DEP("020005","所选部门中存在下级部门，无法删除"),
    USER_OR_PASSWORD_ERROR("020006","用户名或密码错误"),
    RECORD_IS_IN_USE("020007","所选职位中存在被使用职位，无法删除"),
    RESOURCE_IS_IN_USE("020008","所选资源存在子资源，无法删除"),
    ROLE_IS_IN_USE("020009","所选角色正在被使用，无法删除"),
    ALLOC_FAIL("020010","分配失败"),
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
