package aaron.user.service.common.constants;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-07
 */
public class ControllerConstants {
    public static final String COMPANY = "/company";
    public static final String DEPARTMENT = "/department";
    public static final String LOGIN = "/login";
    public static final String ORG = "/org";
    public static final String POSITION = "/position";
    public static final String RESOURCE = "/resource";
    public static final String ROLE = "/role";
    public static final String SYSTEM_PARAM = "/systemparam";
    public static final String USER = "/usermanagement";
    public static final String USER_ONLINE = "/useronline";


    public static final String GET_COMPANY_LIST = "/getCompanyList";

    public static final String GET_DEP_LEVEL = "/queryDepartmentLevel";
    public static final String GET_DEP_PARENT = "/queryDepartmentParent";
    public static final String GET_DEP_TREE_DATA = "/getDepartmentList";
    public static final String GET_DEP_OP = "/queryDepartmentOp";

    public static final String USER_INFO = "/user/info";
    public static final String USER_MENU = "/user/menu";
    public static final String LOGOUT = "/logout";

    /**
     * company
     */
    public static final String SAVE_C = "/saveCompany";
    public static final String UPDATE_C = "/updateCompany";
    public static final String DELETE_C = "/deleteCompany";
    public static final String GET_UPDATE_FORM_C = "/getCompanyUpdateForm";
    public static final String QUERY_C = "/queryCompany";

    /**
     * department
     */
    public static final String SAVE_D = "/saveDepartment";
    public static final String UPDATE_D = "/updateDepartment";
    public static final String DEL_D = "/deleteDepartment";
    public static final String GET_UF_D = "/getDepartmentUpdateForm";
    public static final String QUERY_D = "/queryDepartment";

    /**
     * org
     */
    public static final String SAVE_O = "/saveOrg";
    public static final String UPDATE_O = "/updateOrg";
    public static final String GET_UF = "/getOrgUpdateForm";
    public static final String DEL_O = "/deleteOrg";
    public static final String QUERY_O = "/queryOrg";

    /**
     * position
     */
    public static final String SAVE_P = "/savePosition";
    public static final String UPDATE_P = "/updatePosition";
    public static final String GET_UF_P = "/getPositionUpdateForm";
    public static final String DEL_P = "/deletePosition";
    public static final String QUERY_P = "/queryPosition";
    public static final String QUERY_OPTIONS_P = "/queryPositionOptions";

    /**
     * resource
     */
    public static final String SAVE_R = "/saveResource";
    public static final String GET_UF_R = "/getResourceUpdateForm";
    public static final String UPDATE_R = "/updateResource";
    public static final String DEL_R = "/deleteResource";
    public static final String QUERY_R = "/queryResource";
    public static final String QUERY_LIST_R = "/getResourceList";

    /**
     * role
     */
    public static final String SAVE_ROLE = "/saveRole";
    public static final String UPDATE_ROLE = "/updateRole";
    public static final String GET_UF_ROLE = "/getRoleUpdateForm";
    public static final String DEL_ROLE = "/deleteRole";
    public static final String QUERY_ROLE = "/queryRole";
    public static final String ALLOC_USER_FOR_ROLE = "/allocationUserForRole";
    public static final String UPDATE_RESOURCE_FOR_ROLE = "/updateResourceForRole";
    public static final String GET_USER_FOR_ROLE_FORM = "/getUserForRoleForm";
    public static final String GET_RESOURCE_FOR_ROLE_FORM = "/getResourceForRoleForm";
    public static final String GET_LIST_ROLE = "/getRoleList";

    /**
     * systemparam
     */
    public static final String SAVE_SP = "/saveSystemParam";
    public static final String UPDATE_SP = "/updateSystemParam";
    public static final String GET_UF_SP = "/getSystemParamUpdateForm";
    public static final String DEL_SP = "/deleteSystemParam";
    public static final String QUERY_SP = "/querySystemParam";
    public static final String GET_LIST_SP = "/getSystemParamList";

    /**
     * user
     */
    public static final String SAVE_U = "/saveUserManagement";
    public static final String UPDATE_U = "/updateUserManagement";
    public static final String GET_UF_U = "/getUserManagementUpdateForm";
    public static final String GET_OPTIONS_U = "/queryUserManagementOptions";
    public static final String DEL_U = "/deleteUserManagement";
    public static final String QUERY_U = "/queryUserManagement";
    public static final String GET_LIST_U = "/getUserManagementList";
    public static final String ALLOC_USER = "/allocationUserManagement";

    /**
     * useronline
     */
    public static final String QUERY_UO = "/queryUserOnline";
    public static final String QUERY_ALL = "/queryAllUserOnline";
    public static final String OFFLINE = "/mandatoryOffline";

}
