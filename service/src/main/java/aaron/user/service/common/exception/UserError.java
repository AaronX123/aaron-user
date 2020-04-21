package aaron.user.service.common.exception;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
public enum  UserError {
    DATA_NOT_EXIST("030001","数据不存在"),
    SAVE_FAIL("030002","保存失败"),
    UPDATE_FAIL("030003","更新失败"),
    DELETE_FAIL("030004","删除失败"),

    EXIST_WORKER("030005","所选部门中存在员工，无法删除"),
    USER_OR_PASSWORD_ERROR("030006","用户名或密码错误"),
    RECORD_IS_IN_USE("030007","所选职位中存在被使用职位，无法删除"),
    RESOURCE_IS_IN_USE("030008","所选资源存在子资源，无法删除"),
    ROLE_IS_IN_USE("030009","所选角色正在被使用，无法删除"),
    ROLE_POSSIBLY_IN_USE("030010","要删除的角色可能被使用，请检查"),
    ALLOC_FAIL("030010","分配失败"),
    ORG_IS_IN_USE("030011","所选机构有子公司，无法直接删除"),

    DEL_ROLE_FAIL("030011","删除角色: {}失败"),
    DEL_USER_FAIL("030012","删除用户: {}失败"),
    RELEASE_RESOURCE_FAIL("030013","释放资源失败"),
    SAVE_ROLE_RESOURCE("030013","分配资源失败"),
    LOGIN_FAIL("030014","用户名或密码错误"),
    ;
    String msg;
    String code;

    UserError(String code, String msg) {
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
