package lk.icta.commonuser.framework.constant;

import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserType;


// TODO: Auto-generated Javadoc

/**
 * The Interface CommonUserQueryConstant.
 */
public interface CommonUserQueryConstant {

    // User Login related queries
    /**
     * GET USER DETAILS QUERY
     */
    String GET_USER_DETAILS = "select id,user_full_name,email_id,user_type,user_name,password,dept_id,status,ifnull(created_by,0) as created_by,ifnull(modified_by,0) as modified_by "
            + "from t_user_registration where user_name = ? and password = ?";

    /**
     * The GET_USER_TYPE_CODEVALUE Query
     */
    String GET_USER_TYPE_CODEVALUE = "select name from t_user_type_master where id = ?";

    /**
     * The GET_USER_DEPT_DETAILS
     */
    String GET_USER_DEPT_DETAILS = "select id, name from t_department_master where id = ?";

    /**
     * The GET_ASSIGNABLE_USER_TYPES
     */
    String GET_ASSIGNABLE_USER_TYPES = "select assignable_user_type_id from t_assignment_mapping_master where user_type_id = ?";

    // Forgot Password related queries
    /**
     * The CHECK_USER_EMAIL_COMBINATION
     */
    String CHECK_USER_EMAIL_COMBINATION = "select user_full_name, status, user_name, email_id from t_user_registration where user_name = ? and email_id = ?";

    /**
     * The UPDATE_NEW_PASSWORD
     */
    String UPDATE_NEW_PASSWORD = "update t_user_registration set password = ? where user_name = ? and email_id = ? and status = ?";

    /**
     * GET_USER_ID_BY_USER_NAME
     */
    String GET_USER_ID_BY_USER_NAME = "select id from t_user_registration where user_name = ?";

    // User Management related queries
    /**
     * The GET_ALL_DEPT_DETAILS
     */
    String GET_ALL_DEPT_DETAILS = "select id, name from t_department_master";

    /**
     * The GET_DEPT_SERVICES
     */
    String GET_DEPT_SERVICES = "select id,name,parent_id from t_service_master where department_id = ?";

    /**
     * The GET_DEPT_ADMIN_USER_DETAILS
     */
    String GET_DEPT_ADMIN_USER_DETAILS = "select id, user_full_name, current_nic from t_user_registration where dept_id = ? and user_type = (select id from t_user_type_master where name = "
            + UserType.DEPARTMENT_ADMIN.getCode() + ") order by user_full_name";

    /**
     * The GET_DEPT_USER_DETAILS
     */
//	String GET_DEPT_USER_DETAILS = "select id,user_full_name from t_user_registration where dept_id = ? and user_type = (select id from t_user_type_master where name = "
//			+ UserType.DEPARTMENT_USER.getCode() + ")order by user_full_name";

    String GET_DEPT_USER_DETAILS = "select id,user_full_name, current_nic"
            + " from t_user_registration"
            + " where dept_id = ?"
            + " and user_type IN (select amm.assignable_user_type_id"
            + " from t_user_type_master utm, t_assignment_mapping_master amm"
            + " where utm.id=amm.user_type_id"
            + " AND amm.user_type_id=" + UserType.DEPARTMENT_ADMIN.getCode() + ")"
            + " order by user_full_name";

    /**
     * The CHECK_USER_NAME_EMAIL_BEFORE_ADD
     */
    String CHECK_USER_NAME_BEFORE_ADD = "select count(1) userCount from t_user_registration where user_name = ?";
    String CHECK_EMAIL_BEFORE_ADD = "select count(1) userCount from t_user_registration where email_id = ?";

    /**
     * The ADD_NEW_USER
     */
    String ADD_NEW_USER = "insert into t_user_registration (user_full_name,email_id,user_type,user_name,password,dept_id,status,created_by,created_time,modified_by,modified_time,assigned_location, current_nic) values (?,?,?,?,?,?,?,?,current_timestamp(),null,null,?, ?)";

    /**
     * The GET_NEWLY_CREATED_USER_ID
     */
    String GET_NEWLY_CREATED_USER_ID = "select id from t_user_registration where user_name = ? and email_id = ?";

    /**
     * The ADD_SERVICE_NEW_USER
     */
    String ADD_SERVICE_NEW_USER = "insert into t_user_service_mapping (user_id,service_id,created_by,created_time) values (?,?,?,current_timestamp())";

    /**
     * The FETCH_USER_DETAILS
     */
    String FETCH_USER_DETAILS = "select id,user_full_name,email_id,user_type,user_name,dept_id,status,ifnull(created_by,0) as created_by,ifnull(modified_by,0) as modified_by,assigned_location "
            + ", current_nic from t_user_registration where id = ?";

    /**
     * The GET_ASSIGNED_SERVICES
     */
    String GET_ASSIGNED_SERVICES = "select service_id, name, department_id, parent_id from t_user_service_mapping, t_service_master where t_user_service_mapping.service_id = t_service_master.id and t_user_service_mapping.user_id = ? and t_service_master.department_id = ?";

    /**
     * The GET_USER_DETAILS_BY_ID_BEFORE_EDIT
     */
    String GET_USER_DETAILS_BY_ID_BEFORE_EDIT = "select id,user_full_name,email_id,user_type,user_name,password,dept_id,status,ifnull(created_by,0) as created_by,ifnull(created_time,curdate()) as created_time, modified_by, modified_time "
            + "from t_user_registration where id = ?";

    /**
     * The GET_USER_SERVICES_DETAILS_BEFORE_EDIT
     */
    String GET_USER_SERVICES_DETAILS_BEFORE_EDIT = "select user_id, service_id, created_by, created_time from t_user_service_mapping where user_id = ?";

    /**
     * The INSERT_USER_AUDIT
     */
    String INSERT_USER_AUDIT = "insert into t_user_registration_audit (user_id,user_full_name,email_id,user_type,user_name,password,dept_id,status,created_by,created_time,modified_by,modified_time,insertion_time) values (?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp())";

    /**
     * The INSERT_SERVICE_AUDIT
     */
    String INSERT_SERVICE_AUDIT = "insert into t_user_service_mapping_audit (user_id,service_id,created_by,created_time,insertion_time) values (?,?,?,?,current_timestamp())";

    /**
     * The CHECK_USER_NAME_EMAIL_BEFORE_EDIT
     */
    String CHECK_USER_NAME_BEFORE_EDIT = "select count(1) userCount from t_user_registration where user_name = ? and id <> ?";
    String CHECK_EMAIL_BEFORE_EDIT = "select count(1) userCount from t_user_registration where email_id = ? and id <> ?";

    /**
     * The EDIT_USER
     */
    String EDIT_USER = "update t_user_registration set user_full_name = ?, email_id = ?, user_type = ?, user_name = ?, dept_id = ?, status = ?, modified_by = ?, modified_time = current_timestamp(), current_nic = ?, assigned_location = ? where id = ?";

    /**
     * The DELETE_OLD_SERVICES_FOR_EDIT
     */
    String DELETE_OLD_SERVICES_FOR_EDIT = "delete from t_user_service_mapping where user_id = ?";

    /**
     * The INSERT_NEW_SERVICES_FOR_EDIT
     */
    String INSERT_NEW_SERVICES_FOR_EDIT = "insert into t_user_service_mapping (user_id,service_id,created_by,created_time) values (?,?,?,current_timestamp())";

    // Change Password related queries
    /**
     * The CHECK_USERID_PASSWORD_COMBINATION
     */
    String CHECK_USERID_PASSWORD_COMBINATION = "select count(1) from t_user_registration where id = ? and password = ? and status = ?";

    /**
     * The CHANGE_PASSWORD
     */
    String CHANGE_PASSWORD = "update t_user_registration set password = ? where id = ? and status = ?";

    // User Operation related queries
    /**
     * The INSERT_USER_OPERATION
     */
    String INSERT_USER_OPERATION = "insert into t_user_operations (operation_type,operation_result,created_by,created_time,user_name) values (?,?,?,current_timestamp(),?)";

    /**
     * GET AVAILABLE LIST OF LOCATION
     */
    String SELECT_LIST_OF_LOCATIONS = "select location_id,location_name from t_location_master Order by location_id";

    String ADD_LOCATION_MASTER = "INSERT INTO common_user_police.t_location_master (location_id,location_name) VALUES (?,?)";

    String FIND_USER_BY_CURRENT_NIC = "SELECT COUNT(*) as row_count FROM t_user_registration WHERE status = ? AND current_nic = ?";

    String FIND_USERS_BY_POLICE_STATION_ID = "SELECT * FROM t_user_registration WHERE assigned_location=?";

}
