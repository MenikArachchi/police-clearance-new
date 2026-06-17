package lk.icta.commonuser.framework.app.business;

import lk.icta.commonuser.app.dao.CommonUserDAO;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserStatus;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserType;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.database.DatabaseManager;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.PasswordService;
import lk.icta.commonuser.framework.vo.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class CommonUserBusiness.
 */
public final class CommonUserBusiness {

    private static final Logger LOGGER = Logger.getLogger(CommonUserBusiness.class);

    /**
     * The instance.
     */
    private static CommonUserBusiness instance;

    // Singleton instance

    /**
     * Instantiates a new common user business.
     */
    private CommonUserBusiness() {

    }

    /**
     * Gets the single instance of CommonUserBusiness.
     *
     * @return single instance of CommonUserBusiness
     */
    public static synchronized CommonUserBusiness getInstance() {
        if (instance == null) {
            instance = new CommonUserBusiness();
        }
        return instance;
    }

    /**
     * Gets the departments.
     *
     * @param deptId the dept id
     * @return the departments
     * @throws BusinessException the business exception
     */
    public List<DepartmentVO> getDepartments(int... deptId)
            throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        List<DepartmentVO> deptList = null;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            if (deptId == null || deptId.length == 0) {
                deptList = commonUserDAO.getDepartments(dbCon);

            } else {
                deptList = commonUserDAO.getDepartments(dbCon, deptId);
            }
        } catch (BusinessException bex) {
            throw bex;
        } catch (Exception ex) {
            LOGGER.error("Exception occured in getDepartments", ex);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return deptList;
    }

    /**
     * Gets the services by dept id.
     *
     * @param deptId the dept id
     * @return the services by dept id
     * @throws BusinessException the business exception
     */
    public List<ServiceVO> getServicesByDeptId(int deptId)
            throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        List<ServiceVO> serviceList = null;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            serviceList = commonUserDAO.getServicesByDeptId(dbCon, deptId);
        } catch (BusinessException bex) {
            throw bex;
        } catch (Exception ex) {
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return serviceList;
    }

    /**
     * Gets the created user list.
     *
     * @param loggedOnUserType the logged on user type
     * @param deptId           the dept id
     * @return the created user list
     * @throws BusinessException the business exception
     */
    public List<UserVO> getCreatedUserList(int loggedOnUserType, int deptId)
            throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        List<UserVO> userList = null;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            userList = commonUserDAO.getCreatedUserList(dbCon,
                    loggedOnUserType, deptId);
        } catch (BusinessException bex) {
            throw bex;
        } catch (Exception ex) {
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return userList;
    }

    /**
     * Gets the user type map.
     *
     * @param userTypeVos the user type vos
     * @return the user type map
     * @throws BusinessException the business exception
     */
    public Map<Integer, String> getUserTypeMap(List<UserTypeVO> userTypeVos)
            throws BusinessException {

        Map<Integer, String> userTypeMap = new HashMap<Integer, String>();

        try {

            for (UserTypeVO userType : userTypeVos) {
                userTypeMap.put(userType.getId(),
                        UserType.fromCode(userType.getId()).getValue());
            }
        } catch (Exception ex) {
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        }

        return userTypeMap;
    }

    /**
     * Gets the status map.
     *
     * @param status the status
     * @return the status map
     * @throws BusinessException the business exception
     */
    public Map<Integer, String> getStatusMap(UserStatus status)
            throws BusinessException {

        Map<Integer, String> userStatusMap = new HashMap<Integer, String>();

        try {

            userStatusMap.put(UserStatus.ACTIVE.getCode(),
                    UserStatus.ACTIVE.getValue());
            userStatusMap.put(UserStatus.INACTIVE.getCode(),
                    UserStatus.INACTIVE.getValue());
        } catch (Exception ex) {
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        }

        return userStatusMap;
    }

    /**
     * Validate add operation.
     *
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public void validateAddOperation(UserVO userDataVo)
            throws BusinessException {
        Connection dbCon = null;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            CommonUserDAO.getInstance().validateAddOperation(dbCon, userDataVo);
        } catch (BusinessException ex) {
            LOGGER.error("BusinessException in validateAddOperation", ex);
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("Exception in validateAddOperation", ex);
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(dbCon);
        }
    }

    /**
     * Adds the user.
     *
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean addUser(UserVO userDataVo) throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        boolean successFlag = false;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            dbCon.setAutoCommit(false);
            userDataVo.setPassword(PasswordService.getInstance().encrypt(
                    userDataVo.getPassword()));
            successFlag = commonUserDAO.addUser(dbCon, userDataVo);
            if (successFlag) {
                dbCon.commit();
            }
        } catch (BusinessException bex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw bex;
        } catch (Exception ex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw new BusinessException(ErrorCodeConstant.ADD_USER_FAILED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return successFlag;
    }

    /**
     * Gets the user details.
     *
     * @param selectedUser the selected user
     * @return the user details
     * @throws BusinessException the business exception
     */
    public UserVO getUserDetails(UserVO selectedUser) throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        UserVO userDetail = null;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            userDetail = commonUserDAO.getUserDetails(dbCon, selectedUser);
        } catch (BusinessException bex) {
            throw bex;
        } catch (Exception ex) {
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return userDetail;
    }

    /**
     * Validate edit operation.
     *
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public void validateEditOperation(UserVO userDataVo)
            throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            commonUserDAO.validateEditOperation(dbCon,
                    userDataVo);
        } catch (BusinessException bex) {
            LOGGER.error("BusinessException in validateEditOperation: ", bex);
            throw bex;
        } catch (Exception ex) {
            LOGGER.error("Exception in validateEditOperation: ", ex);
            throw new BusinessException(ErrorCodeConstant.EDIT_USER_FAILED);
        } finally {
            DatabaseManager.close(dbCon);
        }
    }

    /**
     * Edits the user.
     *
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean editUser(UserVO userDataVo) throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        boolean successFlag = false;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            dbCon.setAutoCommit(false);
            successFlag = commonUserDAO.editUser(dbCon, userDataVo);
            if (successFlag) {
                dbCon.commit();
            }
        } catch (BusinessException bex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw bex;
        } catch (Exception ex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw new BusinessException(ErrorCodeConstant.EDIT_USER_FAILED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return successFlag;
    }


    /**
     * to get Assigned Services
     *
     * @param userId
     * @param deptId
     * @return
     * @throws BusinessException
     */
    public List<ServiceVO> getAssignedServics(int userId, int deptId) throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        List<ServiceVO> assignedServices = new ArrayList<ServiceVO>();

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            assignedServices = commonUserDAO.getAssignedServiceVOs(userId, deptId, dbCon);
        } catch (BusinessException bex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw bex;
        } catch (Exception ex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return assignedServices;
    }


    /**
     * to get Available Locations
     *
     * @return
     * @throws BusinessException
     */
    public List<LocationVO> getAvailableLocationss() throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        List<LocationVO> availableLocations = new ArrayList<LocationVO>();

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            availableLocations = commonUserDAO.getAvailableLocationsVO(dbCon);
        } catch (BusinessException bex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw bex;
        } catch (Exception ex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw new BusinessException(ErrorCodeConstant.UNIDENTIFIED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return availableLocations;
    }

    public boolean addLocationMaster(long policeAreaId, String policeArea) throws BusinessException {
        Connection dbCon = null;
        CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();
        boolean successFlag = false;

        try {
            dbCon = DatabaseManager.getCOMMUSRConnection();
            dbCon.setAutoCommit(false);
            successFlag = commonUserDAO.addLocationMaster(dbCon, policeAreaId, policeArea);
            if (successFlag) {
                dbCon.commit();
            }
        } catch (BusinessException bex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw bex;
        } catch (Exception ex) {
            DatabaseManager.rollbackConnection(dbCon);
            throw new BusinessException(ErrorCodeConstant.ADD_LOCATION_MASTER_FAILED);
        } finally {
            DatabaseManager.close(dbCon);
        }

        return successFlag;
    }

    public Boolean checkUserByCurrentNICNumber(String currentNic) {
        LOGGER.info("Entered checkUserByCurrentNICNumber(" + currentNic + ")");
        return CommonUserDAO.getInstance().checkUserByCurrentNICNumber(currentNic);
    }

    public List<UserVO> findAllUsersByPoliceStation(Long policeStationId) {
        LOGGER.info("Entered findAllUsersByPoliceStation(" + policeStationId + ")");
        return CommonUserDAO.getInstance().findAllUsersByPoliceStation(policeStationId);
    }
}
