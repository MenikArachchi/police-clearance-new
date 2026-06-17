package lk.icta.commonuser.app.dao;

import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserStatus;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserType;
import lk.icta.commonuser.framework.constant.DBConstant;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.DepartmentVO;
import lk.icta.commonuser.framework.vo.ServiceVO;
import lk.icta.commonuser.framework.vo.UserTypeVO;
import lk.icta.commonuser.framework.vo.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class CommonUserClientDAO {

    /**
     * The instance.
     */
    private static CommonUserClientDAO instance;

    // Singleton instance

    /**
     * Instantiates a new user login dao.
     */
    private CommonUserClientDAO() {

    }

    /**
     * Gets the single instance of UserLoginDAO.
     *
     * @return single instance of UserLoginDAO
     */
    public static synchronized CommonUserClientDAO getInstance() {
        if (instance == null) {
            instance = new CommonUserClientDAO();
        }
        return instance;
    }

    /**
     * Validate user.
     *
     * @param userName      the user name
     * @param encPassword   the enc password
     * @param deptmentId    the deptment id
     * @param applicationId
     * @param dbCon         the db con
     * @return the user dto
     * @throws Exception the exception
     */
    public UserVO validateUser(String userName, String encPassword,
                               int deptmentId, int applicationId, Connection dbCon)
            throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        UserVO loggedInUser = null;

        try {
            pStmt = dbCon.prepareStatement(GET_USER_DETAILS);
            pStmt.setString(1, userName);
            pStmt.setString(2, encPassword);
            // pStmt.setInt(3, UserStatus.ACTIVE.getCode());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                loggedInUser = new UserVO();
                loggedInUser.setId(rs.getInt(DBConstant.COL_USER_MASTER.ID));
                loggedInUser.setEmailId(rs.getString(DBConstant.COL_USER_MASTER.EMAIL_ID));
                loggedInUser.setFullName(rs.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME));
                loggedInUser.setUserType(this.getUserTypeDTO(rs.getInt(DBConstant.COL_USER_MASTER.USER_TYPE), dbCon));
                loggedInUser.setUserName(rs.getString(DBConstant.COL_USER_MASTER.USER_NAME));
                loggedInUser.setAssignedLocation(rs.getLong(DBConstant.COL_USER_MASTER.ASSIGNED_LOCATION));

                int deptId = rs.getInt(DBConstant.COL_USER_MASTER.DEPT_ID);
                /*if (deptId != deptmentId) {
						throw new BusinessException(ErrorCodeConstant.AUTHONTICATION_ERROR);
				}*/


                loggedInUser.setDept(this.getDepartmentDTO(deptId, dbCon));
                loggedInUser.setAssignedServices(getAssignedServices(loggedInUser.getId(), deptId, applicationId, dbCon));

                //todo set this using database
                // TODO: 2/17/2017 check here
                if (UserType.DHA.getValue().equals(loggedInUser.getUserType().getName().getValue())) {
                    ServiceVO serviceVO = new ServiceVO();
                    serviceVO.setName("Review Application");
                    serviceVO.setId(4);
                    loggedInUser.getAssignedServices().add(serviceVO);
                }

                int status = rs.getInt(DBConstant.COL_USER_MASTER.STATUS);
                if (UserStatus.ACTIVE.getCode() != status) {
                    throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
                }

                // check case-sensitive user name
                if (!userName.equals(loggedInUser.getUserName())) {
                    throw new BusinessException(ErrorCodeConstant.LOGIN_FAILED);
                }

            } else {
                throw new BusinessException(ErrorCodeConstant.LOGIN_FAILED);

            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }
        return loggedInUser;
    }

    /**
     * Gets the user type dto.
     *
     * @param userType the user type
     * @param conn     the conn
     * @return the user type dto
     * @throws SQLException the sQL exception
     */
    public UserTypeVO getUserTypeDTO(int userType, Connection conn)
            throws SQLException {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        UserTypeVO userTypeDetails = null;

        try {
            pStmt = conn.prepareStatement(GET_USER_TYPE_CODEVALUE);
            pStmt.setInt(1, userType);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                userTypeDetails = new UserTypeVO();
                userTypeDetails.setId(userType);
                userTypeDetails.setName(UserType.fromCode(rs
                        .getInt(DBConstant.COL_USER_TYPE_MASTER.NAME)));
            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        return userTypeDetails;
    }

    /**
     * Gets the assignable user types.
     *
     * @param userType the user type
     * @param conn     the conn
     * @return the assignable user types
     * @throws SQLException the sQL exception
     */
    public UserTypeVO[] getAssignableUserTypes(int userType, Connection conn)
            throws SQLException {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<UserTypeVO> assignableUserVoList = new ArrayList<UserTypeVO>();

        try {
            pStmt = conn.prepareStatement(GET_ASSIGNABLE_USER_TYPES);
            pStmt.setInt(1, userType);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                assignableUserVoList
                        .add(this.getUserTypeDTO(
                                rs.getInt(DBConstant.COL_ASSIGNMENT_MAPPING.ASSIGNABLE_USER_TYPE_ID),
                                conn));
            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        return assignableUserVoList
                .toArray(new UserTypeVO[assignableUserVoList.size()]);
    }

    /**
     * Gets the department dto.
     *
     * @param deptId the dept id
     * @param conn   the conn
     * @return the department dto
     * @throws SQLException the sQL exception
     */
    public DepartmentVO getDepartmentDTO(int deptId, Connection conn)
            throws SQLException {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        DepartmentVO deptDetails = null;

        try {
            pStmt = conn.prepareStatement(GET_USER_DEPT_DETAILS);
            pStmt.setInt(1, deptId);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                deptDetails = new DepartmentVO();
                deptDetails.setId(rs.getInt(DBConstant.COL_DEPT_MASTER.ID));
                deptDetails.setName(rs
                        .getString(DBConstant.COL_DEPT_MASTER.NAME));
            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }
        return deptDetails;
    }

    /**
     * Gets the assigned services.
     *
     * @param userId        the user id
     * @param deptId        the dept id
     * @param applicationId
     * @param conn          the conn
     * @return the assigned services
     * @throws Exception the exception
     */
    public List<ServiceVO> getAssignedServices(int userId, int deptId,
                                               int applicationId, Connection conn) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<ServiceVO> assignedServices = new ArrayList<ServiceVO>();
        ServiceVO serviceVo = null;
        System.out.println(">>>>>>>>>>>>>>>>> USER_ID >>>>>>>>>>>>>>>>>>>>>>  " + userId);
        try {
            pStmt = conn.prepareStatement(GET_ASSIGNED_SERVICES);
            pStmt.setInt(1, userId);
            //pStmt.setInt(2, deptId);
            //pStmt.setInt(3, applicationId);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                serviceVo = new ServiceVO();
                serviceVo.setId(rs.getInt(DBConstant.COL_USER_SERVICE_MAPPING.SERVICE_ID));
                serviceVo.setName(rs.getString(DBConstant.COL_SERVICE_MASTER.NAME));
                serviceVo.setDepartmentId(deptId);
                assignedServices.add(serviceVo);
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            //throw new Exception("error.commonuser.addUserFail");
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        return assignedServices;
    }

    /**
     * Validate email.
     *
     * @param userName the user name
     * @param emailId  the email id
     * @param dbCon    the db con
     * @return true, if successful
     * @throws SQLException the sQL exception
     */
    public String getUserFullName(String userName, String emailId,
                                  Connection dbCon) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String userFullName = "";

        try {
            pStmt = dbCon.prepareStatement(CHECK_USER_EMAIL_COMBINATION);
            pStmt.setString(1, userName);
            pStmt.setString(2, emailId);
            // pStmt.setInt(3, UserStatus.ACTIVE.getCode());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                int status = rs.getInt(DBConstant.COL_USER_MASTER.STATUS);
                if (UserStatus.ACTIVE.getCode() != status) {

                    //throw new Exception("error.commonuser.inactiveUser");
                    throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
                }

                // case sensitive user name & email validation
                String userNameFromDB = rs
                        .getString(DBConstant.COL_USER_MASTER.USER_NAME);
                String emailFromDB = rs
                        .getString(DBConstant.COL_USER_MASTER.EMAIL_ID);
                if (!userName.equals(userNameFromDB)
                        || !emailId.equals(emailFromDB)) {

                    throw new BusinessException(
                            ErrorCodeConstant.INCOMPITABLE_USERNAME_EMAIL);
                }

                userFullName = rs
                        .getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME);
            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }
        return userFullName;
    }

    /**
     * Reset password.
     *
     * @param userName     the user name
     * @param emailId      the email id
     * @param encrPassword the encr password
     * @param dbCon        the db con
     * @return true, if successful
     * @throws SQLException the sQL exception
     */
    public boolean resetPassword(String userName, String emailId,
                                 String encrPassword, Connection dbCon) throws SQLException {
        PreparedStatement pStmt = null;
        boolean passwordSet = false;
        int updateCount = 0;

        try {
            pStmt = dbCon.prepareStatement(UPDATE_NEW_PASSWORD);
            pStmt.setString(1, encrPassword);
            pStmt.setString(2, userName);
            pStmt.setString(3, emailId);
            pStmt.setInt(4, UserStatus.ACTIVE.getCode());
            updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                passwordSet = true;
            }
			pStmt.close();
        } finally {
            DatabaseManager.close(pStmt);
        }
        return passwordSet;
    }

    /**
     * Validate user.
     *
     * @param userId      the user id
     * @param oldPassword the old password
     * @param dbCon       the db con
     * @return true, if successful
     * @throws SQLException the sQL exception
     */
    public boolean validateUser(int userId, String oldPassword, Connection dbCon)
            throws SQLException {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        boolean validUserPassword = false;
        int userMatchedCount = 0;

        try {
            pStmt = dbCon.prepareStatement(CHECK_USERID_PASSWORD_COMBINATION);
            pStmt.setInt(1, userId);
            pStmt.setString(2, oldPassword);
            pStmt.setInt(3, UserStatus.ACTIVE.getCode());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                userMatchedCount = rs.getInt(1);
                if (userMatchedCount > 0) {
                    validUserPassword = true;
                }
            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }
        return validUserPassword;
    }

    /**
     * Change password.
     *
     * @param userId       the user id
     * @param encrPassword the encr password
     * @param dbCon        the db con
     * @return true, if successful
     * @throws SQLException the sQL exception
     */
    public boolean changePassword(int userId, String encrPassword,
                                  Connection dbCon) throws SQLException {
        PreparedStatement pStmt = null;
        boolean passwordSet = false;
        int updateCount = 0;

        try {
            pStmt = dbCon.prepareStatement(CHANGE_PASSWORD);
            pStmt.setString(1, encrPassword);
            pStmt.setInt(2, userId);
            pStmt.setInt(3, UserStatus.ACTIVE.getCode());
            updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                passwordSet = true;
            }
			pStmt.close();
        } finally {
            DatabaseManager.close(pStmt);
        }
        return passwordSet;
    }

    /**
     * The Constant GET_USER_DETAILS.
     */
    public static final String GET_USER_DETAILS = "select id,user_full_name,email_id,user_type,user_name,password,dept_id,status,ifnull(created_by,0) as created_by,ifnull(modified_by,0) as modified_by,assigned_location"
            + " from t_user_registration where user_name = ? and password = ?";

    /**
     * The Constant GET_USER_TYPE_CODEVALUE.
     */
    public static final String GET_USER_TYPE_CODEVALUE = "select name from t_user_type_master where id = ?";

    /**
     * The Constant GET_ASSIGNABLE_USER_TYPES.
     */
    public static final String GET_ASSIGNABLE_USER_TYPES = "select assignable_user_type_id from t_assignment_mapping_master where user_type_id = ?";

    /**
     * The Constant GET_USER_DEPT_DETAILS.
     */
    public static final String GET_USER_DEPT_DETAILS = "select id, name from t_department_master where id = ?";

    /**
     * The Constant GET_ASSIGNED_SERVICES.
     */
//	public static final String GET_ASSIGNED_SERVICES = "select service_id, name, department_id from t_user_service_mapping, t_service_master where t_user_service_mapping.service_id = t_service_master.id and t_user_service_mapping.user_id = ? and t_service_master.department_id = ? and t_service_master.parent_id = ?";
    public static final String GET_ASSIGNED_SERVICES = "select service_id, name, department_id from t_user_service_mapping, t_service_master where t_user_service_mapping.service_id = t_service_master.id and t_user_service_mapping.user_id = ?";

    /**
     * The Constant CHECK_USER_EMAIL_COMBINATION.
     */
    public static final String CHECK_USER_EMAIL_COMBINATION = "select user_full_name, status, user_name, email_id from t_user_registration where user_name = ? and email_id = ?";

    /**
     * The Constant UPDATE_NEW_PASSWORD.
     */
    public static final String UPDATE_NEW_PASSWORD = "update t_user_registration set password = ? where user_name = ? and email_id = ? and status = ?";

    /**
     * The Constant CHECK_USERID_PASSWORD_COMBINATION.
     */
    public static final String CHECK_USERID_PASSWORD_COMBINATION = "select count(1) from t_user_registration where id = ? and password = ? and status = ?";

    /**
     * The Constant CHANGE_PASSWORD.
     */
    public static final String CHANGE_PASSWORD = "update t_user_registration set password = ? where id = ? and status = ?";

}
