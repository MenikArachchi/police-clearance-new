package lk.icta.commonuser.app.dao;

import lk.icta.commonuser.framework.constant.CommonUserConstant;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserStatus;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserType;
import lk.icta.commonuser.framework.constant.CommonUserQueryConstant;
import lk.icta.commonuser.framework.constant.DBConstant;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class CommonUserDAO.
 */
public final class CommonUserDAO implements CommonUserQueryConstant {

    private static final Logger LOGGER = Logger.getLogger(CommonUserDAO.class);

    /**
     * The instance.
     */
    private static CommonUserDAO instance;


    // Singleton instance

    /**
     * Instantiates a new common user dao.
     */
    private CommonUserDAO() {

    }

    /**
     * Gets the single instance of CommonUserDAO.
     *
     * @return single instance of CommonUserDAO
     */
    public static synchronized CommonUserDAO getInstance() {
        if (instance == null) {
            instance = new CommonUserDAO();
        }
        return instance;
    }

    /**
     * Gets the departments.
     *
     * @param conn   the conn
     * @param deptId the dept id
     * @return the departments
     * @throws BusinessException the business exception
     */
    public List<DepartmentVO> getDepartments(Connection conn, int... deptId)
            throws BusinessException, SQLException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getDepartments -> deptId: " + deptId);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<DepartmentVO> deptList = new ArrayList<DepartmentVO>();
        DepartmentVO deptVo = null;

        try {
            if (deptId == null || deptId.length == 0) {
                pStmt = conn.prepareStatement(GET_ALL_DEPT_DETAILS);
            } else {
                pStmt = conn.prepareStatement(GET_USER_DEPT_DETAILS);
                pStmt.setInt(1, deptId[0]);
            }
            rs = pStmt.executeQuery();
            while (rs.next()) {
                deptVo = new DepartmentVO();
                deptVo.setId(rs.getInt(DBConstant.COL_DEPT_MASTER.ID));
                deptVo.setName(rs.getString(DBConstant.COL_DEPT_MASTER.NAME));
                deptList.add(deptVo);
            }
			rs.close();
			pStmt.close();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getDepartments -> deptList: " + deptList);
        }

        return deptList;
    }

    /**
     * Gets the services by dept id.
     *
     * @param conn   the conn
     * @param deptId the dept id
     * @return the services by dept id
     * @throws BusinessException the business exception
     */
    public List<ServiceVO> getServicesByDeptId(Connection conn, int deptId)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getServicesByDeptId -> deptId: " + deptId);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<ServiceVO> serviceList = new ArrayList<ServiceVO>();
        ServiceVO serviceVo = null;

        try {
            pStmt = conn.prepareStatement(GET_DEPT_SERVICES);
            pStmt.setInt(1, deptId);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                serviceVo = new ServiceVO();
                serviceVo.setId(rs.getInt(DBConstant.COL_SERVICE_MASTER.ID));
                serviceVo.setName(rs.getString(DBConstant.COL_SERVICE_MASTER.NAME));
                serviceVo.setDepartmentId(deptId);
                if (rs.getObject(DBConstant.COL_SERVICE_MASTER.PARENT_ID) == null) {
                    serviceVo.setParentId(null);
                } else {
                    serviceVo.setParentId(rs.getInt(DBConstant.COL_SERVICE_MASTER.PARENT_ID));
                }
                serviceList.add(serviceVo);
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getServicesByDeptId: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getServicesByDeptId -> serviceList: "
                    + serviceList);
        }

        return serviceList;
    }

    /**
     * Gets the created user list.
     *
     * @param conn             the conn
     * @param loggedOnUserType the logged on user type
     * @param deptId           the dept id
     * @return the created user list
     * @throws BusinessException the business exception
     */
    public List<UserVO> getCreatedUserList(Connection conn,
                                           int loggedOnUserType, int deptId) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getCreatedUserList -> deptId: " + deptId
                    + ", loggedOnUserType: " + loggedOnUserType);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;

        List<UserVO> userList = new ArrayList<UserVO>();

        UserVO userVo = null;

        try {
            if (loggedOnUserType == UserType.SUPER_USER.getCode()) {
                pStmt = conn.prepareStatement(GET_DEPT_ADMIN_USER_DETAILS);
                LOGGER.error("Exception in getCreatedUserList: " + GET_DEPT_ADMIN_USER_DETAILS);
            } else if (loggedOnUserType == UserType.DEPARTMENT_ADMIN.getCode()) {
                pStmt = conn.prepareStatement(GET_DEPT_USER_DETAILS);
                //LOGGER.error("Exception in getCreatedUserList: " + GET_DEPT_USER_DETAILS);
                LOGGER.error("Exception in getCreatedUserList: ");
            }
            pStmt.setInt(1, deptId);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                userVo = new UserVO();
                userVo.setId(rs.getInt(DBConstant.COL_USER_MASTER.ID));
                userVo.setFullName(rs.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME));
                userVo.setCurrentNic(rs.getString(DBConstant.COL_USER_MASTER.CURRENT_NIC));
                userList.add(userVo);

            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("Exception in getCreatedUserList: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getCreatedUserList -> userList: " + userList);
        }

        return userList;
    }

    /**
     * Validate add operation.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public void validateAddOperation(Connection conn, UserVO userDataVo)
            throws BusinessException, SQLException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY validateAddOperation -> userDataVo: " + userDataVo);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;

  //      PreparedStatement pStmt1 = null;
  //      ResultSet rs1 = null;

        int userCount = 0;
        boolean isUserNameAlreadyExist = false;
//		boolean isEmailAlreadyExist = false;

        try {
            pStmt = conn.prepareStatement(CHECK_USER_NAME_BEFORE_ADD);
            pStmt.setString(1, userDataVo.getUserName());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                userCount = rs.getInt(DBConstant.ALIAS.USERCOUNT);
            }
            if (userCount > 0) {
                isUserNameAlreadyExist = true;
            }

            // reset counter
            userCount = 0;
//			if (LOGGER.isDebugEnabled()) {
//			LOGGER.debug("Inside validateAddOperation -> emailId::"+userDataVo.getEmailId());
//			}
//			pStmt1 = conn.prepareStatement(CHECK_EMAIL_BEFORE_ADD);
//			pStmt1.setString(1, userDataVo.getEmailId());
//			rs1 = pStmt1.executeQuery();
//			if (rs1.next()) {
//				userCount = rs1.getInt(DBConstant.ALIAS.USERCOUNT);
//			}
//			if (userCount > 0) {
//				isEmailAlreadyExist = true;
//			}

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("isUserNameAlreadyExist: " + isUserNameAlreadyExist);
//				LOGGER.info("isEmailAlreadyExist: " + isEmailAlreadyExist);
            }
            LOGGER.debug("Inside validateAddOperation -> isUserNameAlreadyExist::" + isUserNameAlreadyExist);
            /*if (isUserNameAlreadyExist && isEmailAlreadyExist) {
                throw new BusinessException(ErrorCodeConstant.DUPLICATE_USERNAME_EMAIL);
			} else*/
            if (isUserNameAlreadyExist) {
                throw new BusinessException(ErrorCodeConstant.DUPLICATE_USERNAME);
            }/* else if (isEmailAlreadyExist) {
                throw new BusinessException(ErrorCodeConstant.DUPLICATE_EMAIL);
			}*/
            LOGGER.debug("Before rc close");
			rs.close();
            LOGGER.debug("after rc close");
			pStmt.close();
            LOGGER.debug("after pstmt close");
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);

        }

    }

    /**
     * Adds the user.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean addUser(Connection conn, UserVO userDataVo)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY addUser -> userDataVo: " + userDataVo);
        }

        PreparedStatement pStmt = null;
        boolean addFlag = false;
        boolean addServiceFlag = false;
        int addCount = 0;

        try {
            pStmt = conn.prepareStatement(ADD_NEW_USER);
            pStmt.setString(1, userDataVo.getFullName());
            pStmt.setString(2, userDataVo.getEmailId());
            pStmt.setInt(3, userDataVo.getUserType().getId());
            pStmt.setString(4, userDataVo.getUserName());
            pStmt.setString(5, userDataVo.getPassword());
            pStmt.setInt(6, userDataVo.getDept().getId());
            pStmt.setInt(7, userDataVo.getStatus().getCode());
            pStmt.setInt(8, userDataVo.getCreatedBy());
            pStmt.setLong(9, userDataVo.getAssignedLocation());
            pStmt.setString(10, userDataVo.getCurrentNic());
            addCount = pStmt.executeUpdate();
            if (addCount > 0) {
                if (userDataVo.getAssignedServices() != null) {
                    int newlyCreatedUid = this.getNewlyCreatedUserId(conn,
                            userDataVo.getUserName(), userDataVo.getEmailId());
                    addServiceFlag = this.addService(conn,
                            userDataVo.getAssignedServices(), newlyCreatedUid,
                            userDataVo.getCreatedBy());
                    if (addServiceFlag) {
                        addFlag = true;
                    }
                } else {
                    addFlag = true;
                }
            }
			pStmt.close();
        } catch (BusinessException bex) {
            LOGGER.error("BusinessException in addUser", bex);
            throw bex;
        } catch (Exception ex) {
            LOGGER.error("Exception in addUser", ex);
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT addUser -> addFlag: " + addFlag);
        }

        return addFlag;
    }

    /**
     * Gets the newly created user id.
     *
     * @param conn     the conn
     * @param userName the user name
     * @param emailId  the email id
     * @return the newly created user id
     * @throws BusinessException the business exception
     */
    public int getNewlyCreatedUserId(Connection conn, String userName,
                                     String emailId) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getNewlyCreatedUserId -> userName: " + userName
                    + ", emailId: " + emailId);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        int newlyCreatedUid = 0;

        try {
            pStmt = conn.prepareStatement(GET_NEWLY_CREATED_USER_ID);
            pStmt.setString(1, userName);
            pStmt.setString(2, emailId);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                newlyCreatedUid = rs.getInt(DBConstant.COL_USER_MASTER.ID);
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getNewlyCreatedUserId: ", ex);
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getNewlyCreatedUserId -> newlyCreatedUid: "
                    + newlyCreatedUid);
        }

        return newlyCreatedUid;
    }

    /**
     * Adds the service.
     *
     * @param conn               the conn
     * @param assignedServices   the assigned services
     * @param newlyCreatedUserId the newly created user id
     * @param createdBy          the created by
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean addService(Connection conn,
                              List<ServiceVO> assignedServices, int newlyCreatedUserId,
                              int createdBy) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY addService -> assignedServices: "
                    + assignedServices + ", newlyCreatedUserId: "
                    + newlyCreatedUserId + ", createdBy: " + createdBy);
        }

        PreparedStatement pStmt = null;
        boolean addServiceFlag = false;
        int addServiceCount = 0;

        try {
            pStmt = conn.prepareStatement(ADD_SERVICE_NEW_USER);
            for (ServiceVO service : assignedServices) {
                pStmt.setInt(1, newlyCreatedUserId);
                pStmt.setInt(2, service.getId());
                pStmt.setInt(3, createdBy);
                addServiceCount += pStmt.executeUpdate();
                pStmt.clearParameters();
            }
            if (addServiceCount > 0) {
                addServiceFlag = true;
            }
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in addService: ", ex);
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT addService -> addServiceFlag: " + addServiceFlag);
        }

        return addServiceFlag;
    }

    /**
     * Gets the user details.
     *
     * @param conn         the conn
     * @param selectedUser the selected user
     * @return the user details
     * @throws BusinessException the business exception
     */
    public UserVO getUserDetails(Connection conn, UserVO selectedUser)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getUserDetails -> selectedUser: "
                    + selectedUser);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        UserVO userDetail = null;

        try {
            CommonUserDAO loginDao = CommonUserDAO.getInstance();
            pStmt = conn.prepareStatement(FETCH_USER_DETAILS);
            pStmt.setInt(1, selectedUser.getId());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                userDetail = new UserVO();
                userDetail.setId(rs.getInt(DBConstant.COL_USER_MASTER.ID));
                userDetail.setFullName(rs.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME));
                userDetail.setEmailId(rs.getString(DBConstant.COL_USER_MASTER.EMAIL_ID));
                userDetail.setUserType(loginDao.getUserTypeVO(rs.getInt(DBConstant.COL_USER_MASTER.USER_TYPE), conn));
                userDetail.setUserName(rs.getString(DBConstant.COL_USER_MASTER.USER_NAME));
                int deptId = rs.getInt(DBConstant.COL_USER_MASTER.DEPT_ID);
                userDetail.setDept(loginDao.getDepartmentVO(deptId, conn));
                userDetail.setStatus(UserStatus.fromCode(rs.getInt(DBConstant.COL_USER_MASTER.STATUS)));
                userDetail.setAssignableUsers(loginDao.getAssignableUserTypes(rs.getInt(DBConstant.COL_USER_MASTER.USER_TYPE), conn, deptId));
                userDetail.setAssignedServices(this.getAssignedServiceVOs(userDetail.getId(), deptId, conn));
                userDetail.setCreatedBy(rs.getInt(DBConstant.COL_USER_MASTER.CREATED_BY));
                userDetail.setModifiedBy(rs.getInt(DBConstant.COL_USER_MASTER.MODIFIED_BY));
                userDetail.setAssignedLocation(rs.getLong(DBConstant.COL_USER_MASTER.ASSIGNED_LOCATION));
                userDetail.setCurrentNic(rs.getString(DBConstant.COL_USER_MASTER.CURRENT_NIC));
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getUserDetails: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getUserDetails -> userDetail: " + userDetail);
        }

        return userDetail;
    }

    /**
     * Gets the assigned service v os.
     *
     * @param userId the user id
     * @param deptId the dept id
     * @param conn   the conn
     * @return the assigned service v os
     * @throws BusinessException the business exception
     */
    public List<ServiceVO> getAssignedServiceVOs(int userId, int deptId,
                                                 Connection conn) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getUserDetails -> userId: " + userId
                    + ", deptId: " + deptId);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<ServiceVO> assignedServices = null;
        ServiceVO serviceVo = null;

        try {
            pStmt = conn.prepareStatement(GET_ASSIGNED_SERVICES);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, deptId);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                serviceVo = new ServiceVO();
                serviceVo.setId(rs.getInt(DBConstant.COL_USER_SERVICE_MAPPING.SERVICE_ID));
                serviceVo.setName(rs.getString(DBConstant.COL_SERVICE_MASTER.NAME));
                serviceVo.setDepartmentId(deptId);
                serviceVo.setParentId(rs.getInt(DBConstant.COL_SERVICE_MASTER.PARENT_ID));
                if (assignedServices == null) {
                    assignedServices = new ArrayList<ServiceVO>();
                }
                assignedServices.add(serviceVo);
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getUserDetails: ", ex);
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getUserDetails -> assignedServices: "
                    + assignedServices);
        }

        return assignedServices;
    }

    /**
     * Validate edit operation.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public void validateEditOperation(Connection conn, UserVO userDataVo)
            throws BusinessException, SQLException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY validateEditOperation -> userDataVo: "
                    + userDataVo);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;

 //       PreparedStatement pStmt1 = null;
 //       ResultSet rs1 = null;

        int userCount = 0;

        boolean isUserNameAlreadyExist = false;
        //boolean isEmailAlreadyExist = false;

        try {
            pStmt = conn.prepareStatement(CHECK_USER_NAME_BEFORE_EDIT);
            pStmt.setString(1, userDataVo.getUserName());
            pStmt.setInt(2, userDataVo.getId());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                userCount = rs.getInt(DBConstant.ALIAS.USERCOUNT);
            }
            if (userCount > 0) {
                isUserNameAlreadyExist = true;
            }

            // reset counter
            userCount = 0;

//			pStmt1 = conn.prepareStatement(CHECK_EMAIL_BEFORE_EDIT);
//			pStmt1.setString(1, userDataVo.getEmailId());
//			pStmt1.setInt(2, userDataVo.getId());
//			rs1 = pStmt1.executeQuery();
//			if (rs1.next()) {
//				userCount = rs1.getInt(DBConstant.ALIAS.USERCOUNT);
//			}
//			if (userCount > 0) {
//				isEmailAlreadyExist = true;
//			}

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("isUserNameAlreadyExist: " + isUserNameAlreadyExist);
                //LOGGER.info("isEmailAlreadyExist: " + isEmailAlreadyExist);
            }

			/*if (isUserNameAlreadyExist && isEmailAlreadyExist) {
                throw new BusinessException(ErrorCodeConstant.DUPLICATE_USERNAME_EMAIL);
			} else*/
            if (isUserNameAlreadyExist) {
                throw new BusinessException(ErrorCodeConstant.DUPLICATE_USERNAME);
            } /*else if (isEmailAlreadyExist) {
                throw new BusinessException(ErrorCodeConstant.DUPLICATE_EMAIL);
			}*/
			rs.close();
			pStmt.close();
			
//			rs1.close();
//			pStmt1.close();

        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);

 //           DatabaseManager.close(rs1);
 //           DatabaseManager.close(pStmt1);
        }
    }

    /**
     * Edits the user.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean editUser(Connection conn, UserVO userDataVo)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY editUser -> userDataVo: " + userDataVo);
        }

        PreparedStatement pStmt = null;
        boolean insertUserAuditFlag = false;
        boolean insertServiceAuditFlag = false;
        boolean editUserFlag = false;
        boolean editServiceFlag = false;
        boolean successEditFlag = false;
        int editCount = 0;

        try {
            insertUserAuditFlag = this.insertUserAudit(conn, userDataVo);
            if (userDataVo.getAssignedServices() != null) {
                insertServiceAuditFlag = this.insertServiceAudit(conn,
                        userDataVo);
            } else {
                insertServiceAuditFlag = true;
            }
            pStmt = conn.prepareStatement(EDIT_USER);
            pStmt.setString(1, userDataVo.getFullName());
            pStmt.setString(2, userDataVo.getEmailId());
            pStmt.setInt(3, userDataVo.getUserType().getId());
            pStmt.setString(4, userDataVo.getUserName());
            pStmt.setInt(5, userDataVo.getDept().getId());
            pStmt.setInt(6, userDataVo.getStatus().getCode());
            pStmt.setInt(7, userDataVo.getModifiedBy());
            pStmt.setString(8, userDataVo.getCurrentNic());
            pStmt.setInt(9, (int) userDataVo.getAssignedLocation());
            pStmt.setInt(10, userDataVo.getId());
            editCount = pStmt.executeUpdate();
            if (editCount > 0) {
                editUserFlag = true;
                if (userDataVo.getAssignedServices() != null) {
                    editServiceFlag = this.editUserServices(conn, userDataVo);
                } else {
                    editServiceFlag = true;
                }
            }
            if (insertUserAuditFlag && insertServiceAuditFlag && editUserFlag
                    && editServiceFlag) {
                successEditFlag = true;
            }
			pStmt.close();
        } catch (BusinessException bex) {
            LOGGER.error("BusinessException in editUser: ", bex);
            throw bex;
        } catch (Exception ex) {
            LOGGER.error("Exception in editUser: ", ex);
            throw new BusinessException(ErrorCodeConstant.EDIT_USER_FAILED);
        } finally {
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT editUser -> successEditFlag: " + successEditFlag);
        }

        return successEditFlag;
    }

    /**
     * Insert user audit.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean insertUserAudit(Connection conn, UserVO userDataVo)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY insertUserAudit -> userDataVo: " + userDataVo);
        }

        PreparedStatement pStmt = null;
        PreparedStatement pStmt2 = null;
        ResultSet rs = null;
        int insertCount = 0;
        boolean flag = false;

        try {
            pStmt = conn.prepareStatement(GET_USER_DETAILS_BY_ID_BEFORE_EDIT);
            pStmt.setInt(1, userDataVo.getId());
            rs = pStmt.executeQuery();
            if (rs.next()) {
                pStmt2 = conn.prepareStatement(INSERT_USER_AUDIT);
                pStmt2.setInt(1, rs.getInt(DBConstant.COL_USER_MASTER.ID));
                pStmt2.setString(2, rs.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME));
                pStmt2.setString(3, rs.getString(DBConstant.COL_USER_MASTER.EMAIL_ID));
                pStmt2.setInt(4, rs.getInt(DBConstant.COL_USER_MASTER.USER_TYPE));
                pStmt2.setString(5, rs.getString(DBConstant.COL_USER_MASTER.USER_NAME));
                pStmt2.setString(6, rs.getString(DBConstant.COL_USER_MASTER.PASSWORD));
                pStmt2.setInt(7, rs.getInt(DBConstant.COL_USER_MASTER.DEPT_ID));
                pStmt2.setInt(8, rs.getInt(DBConstant.COL_USER_MASTER.STATUS));
                pStmt2.setInt(9, rs.getInt(DBConstant.COL_USER_MASTER.CREATED_BY));
                pStmt2.setTimestamp(10, rs.getTimestamp(DBConstant.COL_USER_MASTER.CREATED_TIME));
                if (rs.getString(DBConstant.COL_USER_MASTER.MODIFIED_BY) == null) {
                    pStmt2.setNull(11, Types.NULL);
                } else {
                    pStmt2.setInt(11, rs.getInt(DBConstant.COL_USER_MASTER.MODIFIED_BY));
                }
                pStmt2.setTimestamp(12, rs.getTimestamp(DBConstant.COL_USER_MASTER.MODIFIED_TIME));
                insertCount = pStmt2.executeUpdate();
                if (insertCount > 0) {
                    flag = true;
                }
            }
			rs.close();
			pStmt.close();
			pStmt2.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in insertUserAudit: ", ex);
            throw new BusinessException(ErrorCodeConstant.EDIT_USER_FAILED);
        } finally {
            DatabaseManager.close(pStmt2);
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT insertUserAudit -> flag: " + flag);
        }

        return flag;
    }

    /**
     * Insert service audit.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean insertServiceAudit(Connection conn, UserVO userDataVo)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY insertServiceAudit -> userDataVo: "
                    + userDataVo);
        }

        PreparedStatement pStmt = null;
        PreparedStatement pStmt2 = null;
        ResultSet rs = null;
        int[] insertCount = null;
        boolean flag = false;

        try {
            pStmt = conn
                    .prepareStatement(GET_USER_SERVICES_DETAILS_BEFORE_EDIT);
            pStmt.setInt(1, userDataVo.getId());
            rs = pStmt.executeQuery();
            pStmt2 = conn.prepareStatement(INSERT_SERVICE_AUDIT);
            while (rs.next()) {
                pStmt2.setInt(1, rs.getInt(DBConstant.COL_USER_SERVICE_MAPPING.USER_ID));
                pStmt2.setInt(2, rs.getInt(DBConstant.COL_USER_SERVICE_MAPPING.SERVICE_ID));
                pStmt2.setInt(3, rs.getInt(DBConstant.COL_USER_SERVICE_MAPPING.CREATED_BY));
                pStmt2.setTimestamp(4, rs.getTimestamp(DBConstant.COL_USER_SERVICE_MAPPING.CREATED_TIME));
                pStmt2.addBatch();
            }
            insertCount = pStmt2.executeBatch();
            if (insertCount != null) {
                flag = true;
            }
			rs.close();
			pStmt2.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in validateEmail: ", ex);
            throw new BusinessException(ErrorCodeConstant.EDIT_USER_FAILED);
        } finally {
            DatabaseManager.close(pStmt2);
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT insertServiceAudit -> flag: " + flag);
        }

        return flag;
    }

    /**
     * Edits the user services.
     *
     * @param conn       the conn
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean editUserServices(Connection conn, UserVO userDataVo)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY editUserServices -> userDataVo: " + userDataVo);
        }

        PreparedStatement pStmt = null;
        PreparedStatement pStmt2 = null;
        int delCount = 0;
        int addServiceCount = 0;
        boolean flag = false;

        try {
            pStmt = conn.prepareStatement(DELETE_OLD_SERVICES_FOR_EDIT);
            pStmt.setInt(1, userDataVo.getId());
            delCount = pStmt.executeUpdate();
            if (delCount > 0) {
                pStmt2 = conn.prepareStatement(INSERT_NEW_SERVICES_FOR_EDIT);
                for (ServiceVO service : userDataVo.getAssignedServices()) {
                    pStmt2.setInt(1, userDataVo.getId());
                    pStmt2.setInt(2, service.getId());
                    pStmt2.setInt(3, userDataVo.getCreatedBy());
                    addServiceCount += pStmt2.executeUpdate();
                    pStmt2.clearParameters();
                }
                if (addServiceCount > 0) {
                    flag = true;
                }
            }
			pStmt2.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in validateEmail: ", ex);
            throw new BusinessException(ErrorCodeConstant.EDIT_USER_FAILED);
        } finally {
            DatabaseManager.close(pStmt2);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT editUserServices -> flag: " + flag);
        }

        return flag;
    }

    /**
     * Validate user.
     *
     * @param userName    the user name
     * @param encPassword the enc password
     * @param dbCon       the db con
     * @return the user vo
     * @throws BusinessException the business exception
     */
    public UserVO validateUser(String userName, String encPassword,
                               Connection dbCon) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY validateUser -> userName: " + userName);
        }

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
                loggedInUser.setFullName(rs.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME));
                loggedInUser.setEmailId(rs.getString(DBConstant.COL_USER_MASTER.EMAIL_ID));

                loggedInUser.setUserType(this.getUserTypeVO(rs.getInt(DBConstant.COL_USER_MASTER.USER_TYPE), dbCon));
                System.out.println("###############" + loggedInUser.getUserType().getName());
                System.out.println("###############" + loggedInUser.getUserType().getId());
                loggedInUser.setUserName(rs.getString(DBConstant.COL_USER_MASTER.USER_NAME));
                loggedInUser.setPassword(rs.getString(DBConstant.COL_USER_MASTER.PASSWORD));
                int deptId = rs.getInt(DBConstant.COL_USER_MASTER.DEPT_ID);

                loggedInUser.setDept(this.getDepartmentVO(deptId, dbCon));

                loggedInUser.setStatus(UserStatus.fromCode(rs.getInt(DBConstant.COL_USER_MASTER.STATUS)));

                loggedInUser.setAssignableUsers(this.getAssignableUserTypes(rs.getInt(DBConstant.COL_USER_MASTER.USER_TYPE), dbCon, deptId));

                loggedInUser.setCreatedBy(rs.getInt(DBConstant.COL_USER_MASTER.CREATED_BY));
                loggedInUser.setModifiedBy(rs.getInt(DBConstant.COL_USER_MASTER.MODIFIED_BY));
            }

            if (loggedInUser == null
                    || loggedInUser.getUserType().getName().getCode() == UserType.DEPARTMENT_USER
                    .getCode()) {
                throw new BusinessException(ErrorCodeConstant.ACCESS_DENIED);
            }

            if (UserStatus.ACTIVE.getCode() != loggedInUser.getStatus().getCode()) {
                throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
            }
			rs.close();
			pStmt.close();

        } catch (BusinessException bex) {
            LOGGER.error("BusinessException in validateUser: ", bex);
            throw bex;
        } catch (Exception ex) {
            LOGGER.error("Exception in validateUser: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT validateUser -> loggedInUser: " + loggedInUser);
        }

        return loggedInUser;
    }

    /**
     * Gets the user type vo.
     *
     * @param userType the user type
     * @param conn     the conn
     * @return the user type vo
     * @throws BusinessException the business exception
     */
    public UserTypeVO getUserTypeVO(int userType, Connection conn)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getUserTypeVO -> userType: " + userType);
        }

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
                userTypeDetails.setName(UserType.fromCode(rs.getInt(DBConstant.COL_USER_TYPE_MASTER.NAME)));
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getUserTypeVO: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getUserTypeVO -> userTypeDetails: "
                    + userTypeDetails);
        }

        return userTypeDetails;
    }

    /**
     * Gets the assignable user types.
     *
     * @param userType the user type
     * @param conn     the conn
     * @return the assignable user types
     * @throws BusinessException the business exception
     */
    public List<UserTypeVO> getAssignableUserTypes(int userType, Connection conn, int deptId)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getAssignableUserTypes -> userType: "
                    + userType);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<UserTypeVO> assignableUserVoList = new ArrayList<UserTypeVO>();

        try {
            pStmt = conn.prepareStatement(GET_ASSIGNABLE_USER_TYPES);
            pStmt.setInt(1, userType);
            rs = pStmt.executeQuery();
            while (rs.next()) {

                int assignableUserTypeId = rs.getInt(DBConstant.COL_ASSIGNMENT_MAPPING.ASSIGNABLE_USER_TYPE_ID);
//				System.out.println("assignableUserTypeId :" + assignableUserTypeId);
                if (!(deptId == CommonUserConstant.PHQ_DEPARTMENT_ID)) {
                    if (!(assignableUserTypeId > 3)) {
                        assignableUserVoList.add(this.getUserTypeVO(assignableUserTypeId, conn));
                    }
                } else {
                    assignableUserVoList.add(this.getUserTypeVO(assignableUserTypeId, conn));
                }
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getAssignableUserTypes: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getAssignableUserTypes -> assignableUserVoList: "
                    + assignableUserVoList);
        }

        return assignableUserVoList;
    }

    /**
     * Gets the department vo.
     *
     * @param deptId the dept id
     * @param conn   the conn
     * @return the department vo
     * @throws BusinessException the business exception
     */
    public DepartmentVO getDepartmentVO(int deptId, Connection conn)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getDepartmentVO -> deptId: " + deptId);
        }

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
                deptDetails.setName(rs.getString(DBConstant.COL_DEPT_MASTER.NAME));
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getDepartmentVO: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getDepartmentVO -> deptDetails: " + deptDetails);
        }

        return deptDetails;
    }

    /**
     * Validate user.
     *
     * @param userId      the user id
     * @param oldPassword the old password
     * @param dbCon       the db con
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean validateUser(int userId, String oldPassword, Connection dbCon)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY validateUser -> userId: " + userId);
        }

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
        } catch (Exception ex) {
            LOGGER.error("Exception in validateUser: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT validateUser -> validUserPassword: "
                    + validUserPassword);
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
     * @throws BusinessException the business exception
     */
    public boolean changePassword(int userId, String encrPassword,
                                  Connection dbCon) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY changePassword -> userId: " + userId);
        }

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
        } catch (Exception ex) {
            LOGGER.error("Exception in changePassword: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT changePassword -> passwordSet: " + passwordSet);
        }

        return passwordSet;
    }

    /**
     * Validate email.
     *
     * @param userName     the user name
     * @param emailId      the email id
     * @param userFullName  the user Full Name
     * @param dbCon        the db con
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public String getUserFullName(String userName, String emailId,
                                  Connection dbCon) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY validateEmail -> userName: " + userName
                    + ", emailId: " + emailId);
        }

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

                // In-active user check
                int status = rs.getInt(DBConstant.COL_USER_MASTER.STATUS);
                if (UserStatus.ACTIVE.getCode() != status) {
                    throw new BusinessException(ErrorCodeConstant.INACTIVE_USER);
                }

                // case sensitive user name & email validation
                String userNameFromDB = rs.getString(DBConstant.COL_USER_MASTER.USER_NAME);
                String emailFromDB = rs.getString(DBConstant.COL_USER_MASTER.EMAIL_ID);
                if (!userName.equals(userNameFromDB) || !emailId.equals(emailFromDB)) {
                    throw new BusinessException(ErrorCodeConstant.INCOMPATIBLE_USERNAME_EMAIL);
                }

                userFullName = rs.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME);
            }
			rs.close();
			pStmt.close();
        } catch (BusinessException bex) {
            LOGGER.error("BusinessException in validateEmail: ", bex);
            throw bex;
        } catch (Exception ex) {
            LOGGER.error("Exception in validateEmail: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT validateEmail -> userFullName: "
                    + userFullName);
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
     * @return the int
     * @throws BusinessException the business exception
     */
    public int resetPassword(String userName, String emailId,
                             String encrPassword, Connection dbCon) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY resetPassword -> userName: " + userName
                    + ", emailId: " + emailId);
        }

        PreparedStatement pStmt = null;
        int updateCount = 0;
        int userId = 0;

        try {
            pStmt = dbCon.prepareStatement(UPDATE_NEW_PASSWORD);
            pStmt.setString(1, encrPassword);
            pStmt.setString(2, userName);
            pStmt.setString(3, emailId);
            pStmt.setInt(4, UserStatus.ACTIVE.getCode());
            updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                userId = this.getUserIdByUserName(userName, dbCon);
            }
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in resetPassword: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT resetPassword -> userId: " + userId);
        }

        return userId;
    }

    /**
     * Gets the user id by user name.
     *
     * @param userName the user name
     * @param dbCon    the db con
     * @return the user id by user name
     * @throws BusinessException the business exception
     */
    public int getUserIdByUserName(String userName, Connection dbCon)
            throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getUserIdByUserName -> userName: " + userName);
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        int userId = 0;

        try {
            pStmt = dbCon.prepareStatement(GET_USER_ID_BY_USER_NAME);
            pStmt.setString(1, userName);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getUserIdByUserName: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT getUserIdByUserName -> userId: " + userId);
        }

        return userId;
    }

    /**
     * Sets the operation.
     *
     * @param operationType   the operation type
     * @param operationResult the operation result
     * @param createdBy       the created by
     * @param userName        the user name
     * @param dbCon           the db con
     * @return true, if successful
     */
    public boolean setOperation(int operationType, int operationResult,
                                int createdBy, String userName, Connection dbCon) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY setOperation -> operationType: "
                    + operationType + ", operationResult: " + operationResult
                    + ", createdBy: " + createdBy + ", userName: " + userName);
        }

        PreparedStatement pStmt = null;
        boolean operationSet = false;
        int insertCount = 0;

        try {
            pStmt = dbCon.prepareStatement(INSERT_USER_OPERATION);
            pStmt.setInt(1, operationType);
            pStmt.setInt(2, operationResult);
            if (createdBy == 0) {
                pStmt.setNull(3, Types.NULL);
                pStmt.setString(4, userName);
            } else {
                pStmt.setInt(3, createdBy);
                pStmt.setNull(4, Types.NULL);
            }
            insertCount = pStmt.executeUpdate();
            if (insertCount > 0) {
                operationSet = true;
            }
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in setOperation: ", ex);
        } finally {
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EXIT setOperation -> operationSet: " + operationSet);
        }

        return operationSet;
    }


    /**
     * @param conn
     * @return
     * @throws BusinessException
     */
    public List<LocationVO> getAvailableLocationsVO(Connection conn) throws BusinessException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ENTRY getAvailablePortsVO ");
        }

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<LocationVO> availableLocations = null;
        LocationVO locationsVo = null;

        try {
            pStmt = conn.prepareStatement(SELECT_LIST_OF_LOCATIONS);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                locationsVo = new LocationVO();
                locationsVo.setId(rs.getInt(DBConstant.COL_PORTS_MASTER.LOCATION_ID));
                locationsVo.setLocationName(rs.getString(DBConstant.COL_PORTS_MASTER.LOCATION_NAME));

                if (availableLocations == null) {
                    availableLocations = new ArrayList<LocationVO>();
                }

                availableLocations.add(locationsVo);
            }
			rs.close();
			pStmt.close();
        } catch (Exception ex) {
            LOGGER.error("Exception in getAvailableLocationsVO: ", ex);
            throw new BusinessException(ErrorCodeConstant.UNKNOWN_ERROR);
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pStmt);
        }

        if (LOGGER.isDebugEnabled()) {
            //LOGGER.debug("EXIT getAvailableLocationssVO -> availableLocations: " + availableLocations);
            LOGGER.debug("EXIT getAvailableLocationssVO -> availableLocations: ");
        }

        return availableLocations;
    }

    public boolean addLocationMaster(Connection dbCon, long policeAreaId, String policeArea) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            pstm = dbCon.prepareStatement(ADD_LOCATION_MASTER, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setLong(1, policeAreaId);
            pstm.setString(2, policeArea);
            int result = pstm.executeUpdate();
            if (result > 0) {
                rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    if (rs.getInt(1) == policeAreaId) {
                        flag = true;
                    }
                }
            }
			rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(pstm);
            DatabaseManager.close(rs);
        }
        return flag;
    }

    public Boolean checkUserByCurrentNICNumber(String currentNic) {
        LOGGER.info("Entered checkUserByCurrentNICNumber(" + currentNic + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer i = 0;
        Boolean isExitingUser = Boolean.FALSE;
        try {
            connection = DatabaseManager.getCOMMUSRConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_CURRENT_NIC);
            preparedStatement.setInt(++i, UserStatus.ACTIVE.getCode());
            preparedStatement.setString(++i, currentNic);

            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                Integer rowCount = 0;
                while (resultSet.next()) {
                    rowCount = resultSet.getInt("row_count");
                }

                if (rowCount == 0) {
                    isExitingUser = Boolean.FALSE;
                } else {
                    isExitingUser = Boolean.TRUE;
                }
            }
			resultSet.close();
			preparedStatement.close();
			connection.close();
        } catch (Exception e) {
            LOGGER.error("An error occurred: " + e.getMessage());
        } finally {
            DatabaseManager.close(resultSet);
            DatabaseManager.close(preparedStatement);
            DatabaseManager.close(connection);
        }

        return isExitingUser;
    }

    public List<UserVO> findAllUsersByPoliceStation(Long policeStationId) {
        LOGGER.info("Entered findAllUsersByPoliceStation(" + policeStationId + ")");

        List<UserVO> userVOList = new ArrayList<UserVO>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer i = 0;
        try {
            connection = DatabaseManager.getCOMMUSRConnection();
            preparedStatement = connection.prepareStatement(FIND_USERS_BY_POLICE_STATION_ID);
            preparedStatement.setLong(++i, policeStationId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    UserVO user = new UserVO();
                    user.setFullName(resultSet.getString(DBConstant.COL_USER_MASTER.USER_FULL_NAME));
                    user.setUserName(resultSet.getString(DBConstant.COL_USER_MASTER.USER_NAME));
                    user.setCurrentNic(resultSet.getString(DBConstant.COL_USER_MASTER.CURRENT_NIC));
                    user.setEmailId(resultSet.getString(DBConstant.COL_USER_MASTER.EMAIL_ID));
                    UserStatus status = UserStatus.fromCode(resultSet.getInt(DBConstant.COL_USER_MASTER.STATUS));
                    user.setStatus(status);

                    userVOList.add(user);
                }
            }
			resultSet.close();
			preparedStatement.close();
			connection.close();
        } catch (Exception e) {
            LOGGER.error("An error occurred: " + e.getMessage());
        } finally {
            DatabaseManager.close(resultSet);
            DatabaseManager.close(preparedStatement);
            DatabaseManager.close(connection);
        }

        return userVOList;
    }

}
