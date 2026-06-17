package lk.icta.commonuser.dept.web.action;

import com.opensymphony.xwork2.ActionSupport;
import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.app.business.UserOperationBusiness;
import lk.icta.commonuser.framework.constant.CommonUserConstant;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperation;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserOperationResult;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserStatus;
import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserType;
import lk.icta.commonuser.framework.constant.SessionConstant;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.utility.CommonUtil;
import lk.icta.commonuser.framework.vo.*;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

// TODO: Auto-generated Javadoc

/**
 * The Class CommonUserAction.
 */
public class CommonUserAction extends ActionSupport implements SessionAware,
        ServletRequestAware {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger
            .getLogger(CommonUserAction.class);

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The session.
     */
    private Map<String, Object> session;

    /**
     * The request.
     */
    private HttpServletRequest request;

    /**
     * The user vo.
     */
    private UserVO userVo = new UserVO();

    /**
     * The dept list.
     */
    private List<DepartmentVO> deptList = new ArrayList<DepartmentVO>();

    /**
     * The service list.
     */
    private List<ServiceVO> serviceList = new ArrayList<ServiceVO>();

    /**
     * The services for user.
     */
    private int[] servicesForUser;

    private Map<Integer, SubDeptServiceVO> subDeptServiceMap = new HashMap<Integer, SubDeptServiceVO>();

    /**
     * The assignable user types.
     */
    private Map<Integer, String> assignableUserTypes = new HashMap<Integer, String>();

    /**
     * The assigned user type.
     */
    private String assignedUserType;

    /**
     * The confirmed pwd.
     */
    private String confirmedPwd;

    /**
     * The user status.
     */
    private Map<Integer, String> userStatus = new HashMap<Integer, String>();

    /**
     * The present status.
     */
    private String presentStatus;

    /**
     * The operation.
     */
    private String operation;

    /**
     * The logged on user type.
     */
    private String loggedOnUserType;

    private String selectedUserId;

    /**
     * The user list.
     */

    private Map<Integer, String> userList = new LinkedHashMap<Integer, String>();

    /**
     * The disp full name.
     */
    private String dispFullName;


    /**
     * The available ports  list.
     */
    private List<LocationVO> availableLocations = new ArrayList<LocationVO>();

    /**
     * The assigned location.
     */
    private String assignedLocation;

    private Boolean exitingUser;

    private String currentNic;

    private Long policeStationId;

    private List<UserVO> assignedUsers;

    private Boolean canAccess;

    /*
     * (non-Javadoc)
     *
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() {
        String retStr = "FAILURE";

        if (session == null || session.get(SessionConstant.USER_DETAILS) == null) {
            addActionError(getText("commonuser.session.expired"));
            return "SESSION_EXPIRED";
        }

        UserVO loggedOnUser = (UserVO) session.get(SessionConstant.USER_DETAILS);

        userSearchAccess(loggedOnUser);

        try {
            if (loggedOnUser.getUserType().getName().getCode() == UserType.SUPER_USER.getCode()) {
                setLoggedOnUserType(Integer.toString(UserType.SUPER_USER.getCode()));
                DepartmentVO header = new DepartmentVO();
                header.setId(CommonUserConstant.NOT_SELECTED);
                header.setName("--Select--");
                deptList.add(header);
                deptList.addAll(getDepartments());

                // to make "--select--" as default
                userVo.setDept(header);
                System.out.println("||||||||||||||||||||||||||||||||| -HeadOfficeLocation-  ||||||||||||||||||||||||||||||||||||||||");
                availableLocations = getAvailableLocationList();
            } else if (loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                    .getCode()) {
                setLoggedOnUserType(Integer.toString(UserType.DEPARTMENT_ADMIN
                        .getCode()));
                deptList = this.getDepartments(loggedOnUser.getDept().getId());
                // serviceList = this.getServicesByDeptId(loggedOnUser.getDept()
                // .getId());

                userVo.setDept(deptList.get(0));
                // this.setSubDeptMap(serviceList);

                availableLocations = getAvailableLocationList();
                System.out.println("|||||||||||||||||||||||||||||||| -HeadOfficeLocation- |||||||||||||||||||||||||||||||||||||||||");
            }

            List<UserTypeVO> assignableUsers = loggedOnUser.getAssignableUsers();
            setAssignableUserTypes(this.getUserTypeMap(assignableUsers));
            UserStatus status = loggedOnUser.getStatus();
            setUserStatus(this.getStatusMap(status));
            setPresentStatus(Integer.toString(status.getCode()));
            setDispFullName(loggedOnUser.getFullName());

            retStr = "LOAD";
        } catch (BusinessException bex) {
            LOGGER.fatal("CommonUserAction # execute error : " + bex);

            addActionError(getText(CommonUtil.getErrorKey(bex)));
            this.setUserOperation(UserOperation.LOGIN.getCode(),
                    UserOperationResult.FAILURE.getCode(), 0,
                    loggedOnUser.getUserName());
            retStr = "FAILURE";
        }
        return retStr;
    }

    private Boolean userSearchAccess(UserVO loggedOnUser) {
        if (UserType.DEPARTMENT_ADMIN.getCode() == loggedOnUser.getUserType().getName().getCode()
                || UserType.SUPER_USER.getCode() == loggedOnUser.getUserType().getName().getCode()) {
            canAccess = Boolean.TRUE;
        } else {
            canAccess = Boolean.FALSE;
        }

        return canAccess;
    }

    /**
     * Process user mgmt.
     *
     * @return the string
     */

    public String processUserMgmt() {
        String retStr = "FAILURE";
        boolean addStatus = false;
        boolean editStatus = false;
        UserVO userDataVo = null;
        UserVO loggedOnUser = null;
        List<ServiceVO> servicesList;
        LOGGER.debug("processUserMgmt");
        try {
            LOGGER.debug(session.get(SessionConstant.USER_DETAILS));
            if (session == null
                    || session.get(SessionConstant.USER_DETAILS) == null) {
                addActionError(getText("commonuser.session.expired"));
                return "SESSION_EXPIRED";
            }
            loggedOnUser = (UserVO) session.get(SessionConstant.USER_DETAILS);
            LOGGER.debug(loggedOnUser);
            userSearchAccess(loggedOnUser);
            LOGGER.debug(userSearchAccess(loggedOnUser));

            userDataVo = this.userVo;
            userDataVo.setCreatedBy(loggedOnUser.getId());
            LOGGER.debug(this.operation);
            if (this.operation.equalsIgnoreCase("add")) {
                UserTypeVO thisUserTypeVO = new UserTypeVO();
                thisUserTypeVO.setId(Integer.parseInt(this.assignedUserType));
                userDataVo.setUserType(thisUserTypeVO);
                userDataVo.setStatus(UserStatus.fromCode(Integer
                        .parseInt(this.presentStatus)));
                LOGGER.debug(userDataVo);
                LOGGER.debug(servicesForUser);
                if (loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                        .getCode()) {
                    availableLocations = getAvailableLocationList();
                    if (servicesForUser != null && servicesForUser.length > 0) {
                        List<ServiceVO> selectedServiceList = new ArrayList<ServiceVO>();
                        for (int serviceId : servicesForUser) {
                            ServiceVO service = new ServiceVO();
                            service.setId(serviceId);
                            selectedServiceList.add(service);
                        }
                        userDataVo.setAssignedServices(selectedServiceList);
                    }
                }
                if (loggedOnUser.getUserType().getName().getCode() == UserType.SUPER_USER
                        .getCode()) {
                    //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    availableLocations = getAvailableLocationList();
                    servicesList = this.getServicesByDeptId(userDataVo
                            .getDept().getId());
                    serviceList = new ArrayList<ServiceVO>();
                    for (ServiceVO serviceVo : servicesList) {
                        if (serviceVo.getParentId() != null) {
                            serviceList.add(serviceVo);
                        }
                    }
                    userDataVo.setAssignedServices(serviceList);
                }

                CommonUserBusiness.getInstance().validateAddOperation(
                        userDataVo);
                LOGGER.debug(userDataVo);
                addStatus = this.addUser(userDataVo);
                LOGGER.debug(addStatus);
                if (!addStatus) {

                    addActionError(getText("error.commonuser.addUserFail"));
                    this.setUserOperation(UserOperation.ADD_USER.getCode(),
                            UserOperationResult.FAILURE.getCode(),
                            loggedOnUser.getId(), null);
                    setDefaultPage(userDataVo, loggedOnUser);
                } else {

                    addActionMessage(getText("commonuser.addUserSuccess"));
                    this.setUserOperation(UserOperation.ADD_USER.getCode(),
                            UserOperationResult.SUCCESS.getCode(),
                            loggedOnUser.getId(), null);
                    retStr = "SUCCESS";
                }
            } else if (this.operation.equalsIgnoreCase("edit")) {
                userDataVo.setModifiedBy(loggedOnUser.getId());
                UserTypeVO thisUserTypeVO = new UserTypeVO();

                thisUserTypeVO.setId(Integer.parseInt(this.assignedUserType));
                userDataVo.setUserType(thisUserTypeVO);
                userDataVo.setStatus(UserStatus.fromCode(Integer
                        .parseInt(this.presentStatus)));
                if (loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                        .getCode()) {
                    availableLocations = getAvailableLocationList();
                    if (servicesForUser != null && servicesForUser.length > 0) {
                        List<ServiceVO> selectedServices = new ArrayList<ServiceVO>();
                        for (int serviceId : servicesForUser) {
                            ServiceVO srvcVo = new ServiceVO();
                            srvcVo.setId(serviceId);
                            selectedServices.add(srvcVo);
                        }
                        userDataVo.setAssignedServices(selectedServices);
                    }
                }

                CommonUserBusiness.getInstance().validateEditOperation(
                        userDataVo);

                editStatus = this.editUser(userDataVo);
                if (!editStatus) {

                    addActionError(getText("error.commonuser.editUserFail"));
                    this.setUserOperation(UserOperation.EDIT_USER.getCode(),
                            UserOperationResult.FAILURE.getCode(),
                            loggedOnUser.getId(), null);
                    setDefaultPage(userDataVo, loggedOnUser);
                } else {

                    addActionMessage(getText("commonuser.editUserSuccess"));
                    this.setUserOperation(UserOperation.EDIT_USER.getCode(),
                            UserOperationResult.SUCCESS.getCode(),
                            loggedOnUser.getId(), null);
                    retStr = "SUCCESS";
                }
            }
            setDispFullName(loggedOnUser.getFullName());
        } catch (BusinessException bex) {
            addActionError(getText(CommonUtil.getErrorKey(bex)));
            if (this.operation.equalsIgnoreCase("add")) {
                LOGGER.fatal("CommonUserAction # processUserMgmt addUser error : "
                        + bex);
                this.setUserOperation(UserOperation.ADD_USER.getCode(),
                        UserOperationResult.FAILURE.getCode(),
                        loggedOnUser.getId(), null);
            } else if (this.operation.equalsIgnoreCase("edit")) {
                LOGGER.fatal("CommonUserAction # processUserMgmt editUser error : "
                        + bex);
                this.setUserOperation(UserOperation.EDIT_USER.getCode(),
                        UserOperationResult.FAILURE.getCode(),
                        loggedOnUser.getId(), null);
            }
            retStr = "FAILURE";
            setDefaultPage(userDataVo, loggedOnUser);
            setDispFullName(loggedOnUser.getFullName());
        }

        return retStr;
    }

    /**
     * Fetch user.
     *
     * @return the string
     */
    public String fetchUser() {
        String retStr = "SUCCESS";
        Map<Integer, UserVO> createdUsers = null;
        List<UserVO> usersList = null;
        if (session == null
                || session.get(SessionConstant.USER_DETAILS) == null) {

            addActionError(getText("commonuser.session.expired"));
            return "SESSION_EXPIRED";
        }
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);

        UserVO userDetails = this.userVo;

        try {
            this.userVo = this.getUserDetails(userDetails);
            selectedUserId = this.userVo.getId() + "";
            if (loggedOnUser.getUserType().getName().getCode() == UserType.SUPER_USER
                    .getCode()) {
                setLoggedOnUserType(Integer.toString(UserType.SUPER_USER
                        .getCode()));

                DepartmentVO headerDepartment = new DepartmentVO();
                headerDepartment.setId(CommonUserConstant.NOT_SELECTED);
                headerDepartment.setName("--Select--");
                deptList.add(headerDepartment);
                deptList.addAll(getDepartments());
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                availableLocations = getAvailableLocationList();
            } else if (loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                    .getCode()) {
                setLoggedOnUserType(Integer.toString(UserType.DEPARTMENT_ADMIN
                        .getCode()));
                deptList = this.getDepartments(loggedOnUser.getDept().getId());
                availableLocations = getAvailableLocationList();
            }
            usersList = this.getCreatedUserList(loggedOnUser.getUserType()
                    .getName().getCode(), this.userVo.getDept().getId());

            createdUsers = new LinkedHashMap<Integer, UserVO>();
            for (UserVO user : usersList) {
                createdUsers.put(user.getId(), user);
            }
            for (Entry<Integer, UserVO> entry : createdUsers.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue().getFullName();

                userList.put(key, value);
            }

            serviceList = this.getServicesByDeptId(this.userVo.getDept()
                    .getId());

            this.setSubDeptMap(serviceList);
            List<UserTypeVO> assignableUsers = loggedOnUser
                    .getAssignableUsers();
            setAssignableUserTypes(this.getUserTypeMap(assignableUsers));
            setAssignedUserType(Integer.toString(this.userVo.getUserType()
                    .getId()));

            UserStatus status = this.userVo.getStatus();
            setUserStatus(this.getStatusMap(status));
            setPresentStatus(Integer.toString(status.getCode()));

            // if (this.userVo.getAssignedServices() != null) {
            // List<ServiceVO> assignedServices = this.userVo
            // .getAssignedServices();
            // servicesForUser = new int[assignedServices.size()];
            // int i = 0;
            // for (ServiceVO serviceVo : assignedServices) {
            // servicesForUser[i] = serviceVo.getId();
            // i++;
            // }
            // }
            setDispFullName(loggedOnUser.getFullName());
            retStr = "SUCCESS";
        } catch (BusinessException bex) {
            LOGGER.fatal("CommonUserAction # fetchUser error : " + bex);

            addActionError(getText(CommonUtil.getErrorKey(bex)));
            this.setUserOperation(UserOperation.EDIT_USER.getCode(),
                    UserOperationResult.FAILURE.getCode(),
                    loggedOnUser.getId(), null);
            retStr = "FAILURE";
        }
        return retStr;
    }

    public String checkUserByCurrentNICNumber() {
        LOGGER.info("Entered checkUserByCurrentNICNumber()");
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);

        exitingUser = CommonUserBusiness.getInstance().checkUserByCurrentNICNumber(currentNic);

        return SUCCESS;
    }

    public String findAllUsersByPoliceStation() {
        LOGGER.info("Entered findAllUsersByPoliceStation()");
        if (policeStationId <= 0) {
            addActionError("Please provide a valid Police Station");
        }
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        assignedUsers = CommonUserBusiness.getInstance().findAllUsersByPoliceStation(policeStationId);
        return SUCCESS;
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
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        return commonUserBusiness.getDepartments(deptId);
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
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        return commonUserBusiness.getServicesByDeptId(deptId);
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
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        return commonUserBusiness.getUserTypeMap(userTypeVos);
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
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        return commonUserBusiness.getStatusMap(status);
    }

    // /**
    // * Validate add operation.
    // *
    // * @param userDataVo
    // * the user data vo
    // * @return true, if successful
    // * @throws BusinessException
    // * the business exception
    // */
    // public boolean validateAddOperation(UserVO userDataVo)
    // throws BusinessException {
    // CommonUserBusiness commonUserBusiness = CommonUserBusiness
    // .getInstance();
    // return commonUserBusiness.validateAddOperation(userDataVo);
    // }

    /**
     * Adds the user.
     *
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean addUser(UserVO userDataVo) throws BusinessException {
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);

        String currentNic = userDataVo.getCurrentNic();
        if (currentNic == null || currentNic.isEmpty()) {
            return false;
        }
        Boolean isExitingUser = CommonUserBusiness.getInstance().checkUserByCurrentNICNumber(currentNic);
        return !isExitingUser && commonUserBusiness.addUser(userDataVo);
    }

    /**
     * Gets the user details.
     *
     * @param userDataVo the user data vo
     * @return the user details
     * @throws BusinessException the business exception
     */
    public UserVO getUserDetails(UserVO userDataVo) throws BusinessException {
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        return commonUserBusiness.getUserDetails(userDataVo);
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
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        return commonUserBusiness.getCreatedUserList(loggedOnUserType, deptId);
    }

    // /**
    // * Validate edit operation.
    // *
    // * @param userDataVo
    // * the user data vo
    // * @return true, if successful
    // * @throws BusinessException
    // * the business exception
    // */
    // public boolean validateEditOperation(UserVO userDataVo)
    // throws BusinessException {
    // CommonUserBusiness commonUserBusiness = CommonUserBusiness
    // .getInstance();
    // return commonUserBusiness.validateEditOperation(userDataVo);
    // }

    /**
     * Edits the user.
     *
     * @param userDataVo the user data vo
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    public boolean editUser(UserVO userDataVo) throws BusinessException {
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        return commonUserBusiness.editUser(userDataVo);
    }

    /**
     * Sets the default page.
     *
     * @param thisUser     the this user
     * @param loggedOnUser the logged on user
     */
    public void setDefaultPage(UserVO thisUser, UserVO loggedOnUser) {
        Map<Integer, UserVO> createdUsers = null;
        List<UserVO> usersList = null;
        userSearchAccess(loggedOnUser);

        try {
            if (loggedOnUser.getUserType().getName().getCode() == UserType.SUPER_USER
                    .getCode()) {
                setLoggedOnUserType(Integer.toString(UserType.SUPER_USER
                        .getCode()));

                DepartmentVO firstElement = new DepartmentVO();
                firstElement.setId(CommonUserConstant.NOT_SELECTED);
                firstElement.setName("--Select--");

                deptList.add(firstElement);
                deptList.addAll(getDepartments());

            } else if (loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                    .getCode()) {
                setLoggedOnUserType(Integer.toString(UserType.DEPARTMENT_ADMIN
                        .getCode()));
                deptList = this.getDepartments(loggedOnUser.getDept().getId());
            }
            usersList = this.getCreatedUserList(loggedOnUser.getUserType()
                    .getName().getCode(), thisUser.getDept().getId());
            createdUsers = new HashMap<Integer, UserVO>();
            for (UserVO user : usersList) {
                createdUsers.put(user.getId(), user);
            }
            for (Entry<Integer, UserVO> entry : createdUsers.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue().getFullName();
                userList.put(key, value);
            }

            serviceList = this.getServicesByDeptId(thisUser.getDept().getId());

            this.setSubDeptMap(serviceList);
            // if (loggedOnUser.getUserType().getName().getCode() ==
            // UserType.SUPER_USER
            // .getCode()) {
            // servicesForUser = new int[serviceList.size()];
            // int i = 0;
            // for (ServiceVO serviceVo : serviceList) {
            // if (serviceVo.getParentId() != null) {
            // servicesForUser[i] = serviceVo.getId();
            // i++;
            // }
            // }
            // }
            List<UserTypeVO> assignableUsers = loggedOnUser
                    .getAssignableUsers();
            setAssignableUserTypes(this.getUserTypeMap(assignableUsers));
            setAssignedUserType(Integer
                    .toString(thisUser.getUserType().getId()));

            UserStatus status = thisUser.getStatus();
            setUserStatus(this.getStatusMap(status));
            setPresentStatus(Integer.toString(status.getCode()));

            // if (thisUser.getAssignedServices() != null) {
            // List<ServiceVO> assignedServices = thisUser
            // .getAssignedServices();
            // servicesForUser = new int[assignedServices.size()];
            // int i = 0;
            // for (ServiceVO serviceVo : assignedServices) {
            // servicesForUser[i] = serviceVo.getId();
            // i++;
            // }
            // }
        } catch (BusinessException bex) {
            LOGGER.fatal("CommonUserAction # setDefaultPage error : " + bex);
        }
    }

    /**
     * Sets the user operation.
     *
     * @param operationType   the operation type
     * @param operationResult the operation result
     * @param createdBy       the created by
     * @param userName        the user name
     */
    public void setUserOperation(int operationType, int operationResult,
                                 int createdBy, String userName) {
        UserOperationBusiness userOpBusiness = UserOperationBusiness
                .getInstance();
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        userOpBusiness.setOperation(operationType, operationResult, createdBy,
                userName);
    }

    /**
     * Gets the dept list.
     *
     * @return the deptList
     */
    public List<DepartmentVO> getDeptList() {
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        return deptList;
    }

    /**
     * Sets the dept list.
     *
     * @param deptList the deptList to set
     */
    public void setDeptList(List<DepartmentVO> deptList) {
        this.deptList = deptList;
    }

    /**
     * Gets the service list.
     *
     * @return the serviceList
     */
    public List<ServiceVO> getServiceList() {
        return serviceList;
    }

    /**
     * Sets the service list.
     *
     * @param serviceList the serviceList to set
     */
    public void setServiceList(List<ServiceVO> serviceList) {
        this.serviceList = serviceList;
    }

    /**
     * Sets the user vo.
     *
     * @param userVo the userVo to set
     */
    public void setUserVo(UserVO userVo) {
        this.userVo = userVo;
    }

    /**
     * Gets the user vo.
     *
     * @return the userVo
     */
    public UserVO getUserVo() {
        return userVo;
    }

    /**
     * Sets the operation.
     *
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Gets the operation.
     *
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the logged on user type.
     *
     * @param loggedOnUserType the loggedOnUserType to set
     */
    public void setLoggedOnUserType(String loggedOnUserType) {
        this.loggedOnUserType = loggedOnUserType;
    }

    /**
     * Gets the logged on user type.
     *
     * @return the loggedOnUserType
     */
    public String getLoggedOnUserType() {
        return loggedOnUserType;
    }

    /**
     * Gets the user list.
     *
     * @return the userList
     */
    public Map<Integer, String> getUserList() {
        return userList;
    }

    /**
     * Sets the user list.
     *
     * @param userList the userList to set
     */
    public void setUserList(Map<Integer, String> userList) {
        this.userList = userList;
    }

    /**
     * Sets the assignable user types.
     *
     * @param assignableUserTypes the assignableUserTypes to set
     */
    public void setAssignableUserTypes(Map<Integer, String> assignableUserTypes) {
        this.assignableUserTypes = assignableUserTypes;
    }

    /**
     * Gets the assignable user types.
     *
     * @return the assignableUserTypes
     */
    public Map<Integer, String> getAssignableUserTypes() {
        return assignableUserTypes;
    }

    /**
     * Sets the assigned user type.
     *
     * @param assignedUserType the assignedUserType to set
     */
    public void setAssignedUserType(String assignedUserType) {
        this.assignedUserType = assignedUserType;
    }

    /**
     * Gets the assigned user type.
     *
     * @return the assignedUserType
     */
    public String getAssignedUserType() {
        return assignedUserType;
    }

    /**
     * Sets the confirmed pwd.
     *
     * @param confirmedPwd the confirmedPwd to set
     */
    public void setConfirmedPwd(String confirmedPwd) {
        this.confirmedPwd = confirmedPwd;
    }

    /**
     * Gets the confirmed pwd.
     *
     * @return the confirmedPwd
     */
    public String getConfirmedPwd() {
        return confirmedPwd;
    }

    /**
     * Sets the user status.
     *
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(Map<Integer, String> userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Gets the user status.
     *
     * @return the userStatus
     */
    public Map<Integer, String> getUserStatus() {
        return userStatus;
    }

    /**
     * Gets the present status.
     *
     * @return the presentStatus
     */
    public String getPresentStatus() {
        return presentStatus;
    }

    /**
     * Sets the present status.
     *
     * @param presentStatus the presentStatus to set
     */
    public void setPresentStatus(String presentStatus) {
        this.presentStatus = presentStatus;
    }

    /**
     * Gets the services for user.
     *
     * @return the servicesForUser
     */
    public int[] getServicesForUser() {
        return servicesForUser;
    }

    /**
     * Sets the services for user.
     *
     * @param servicesForUser the servicesForUser to set
     */
    public void setServicesForUser(int[] servicesForUser) {
        this.servicesForUser = servicesForUser;
    }

    /**
     * Gets the checks if is dep admin.
     *
     * @return the checks if is dep admin
     */
    public Boolean getIsDepAdmin() {
        if (loggedOnUserType != null) {
            return (Integer.parseInt(loggedOnUserType) == UserType.DEPARTMENT_ADMIN
                    .getCode());
        } else {
            return null;
        }
    }

    /**
     * Gets the checks if is sup user.
     *
     * @return the checks if is sup user
     */
    public Boolean getIsSupUser() {
        if (loggedOnUserType != null) {
            return (Integer.parseInt(loggedOnUserType) == UserType.SUPER_USER
                    .getCode());
        } else {
            return null;
        }
    }

    /**
     * Sets the disp full name.
     *
     * @param dispFullName the dispFullName to set
     */
    public void setDispFullName(String dispFullName) {
        this.dispFullName = dispFullName;
    }

    /**
     * Gets the disp full name.
     *
     * @return the dispFullName
     */
    public String getDispFullName() {
        return dispFullName;
    }

    public List<LocationVO> getAvailableLocations() {
        return availableLocations;
    }

    public void setAvailableLocations(List<LocationVO> availableLocations) {
        this.availableLocations = availableLocations;
    }

    public String getAssignedLocation() {
        return assignedLocation;
    }

    public void setAssignedLocation(String assignedLocation) {
        this.assignedLocation = assignedLocation;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * @param subDeptServiceMap the subDeptServiceMap to set
     */
    public void setSubDeptServiceMap(
            Map<Integer, SubDeptServiceVO> subDeptServiceMap) {
        this.subDeptServiceMap = subDeptServiceMap;
    }

    /**
     * @return the subDeptServiceMap
     */
    public Map<Integer, SubDeptServiceVO> getSubDeptServiceMap() {
        return subDeptServiceMap;
    }

    public void setSubDeptMap(List<ServiceVO> serviceList) {
        Map<Integer, String> subDepts = new HashMap<Integer, String>();
        SubDeptServiceVO subDeptServiceVo;
        List<ServiceVO> servicePerSubDept;
        for (ServiceVO serviceVo : serviceList) {
            if (serviceVo.getParentId() == null) {
                subDepts.put(serviceVo.getId(), serviceVo.getName());
            }
        }
        for (int subdeptId : subDepts.keySet()) {
            subDeptServiceVo = new SubDeptServiceVO();
            servicePerSubDept = new ArrayList<ServiceVO>();
            subDeptServiceVo.setSubDeptId(subdeptId);
            subDeptServiceVo.setSubDeptName(subDepts.get(subdeptId));
            for (ServiceVO serviceVo : serviceList) {
                if (serviceVo.getParentId() != null
                        && serviceVo.getParentId() == subdeptId) {
                    servicePerSubDept.add(serviceVo);
                }
            }
            if (servicePerSubDept.size() > 0) {
                subDeptServiceVo.setDeptServices(servicePerSubDept);
                subDeptServiceMap.put(subdeptId, subDeptServiceVo);
            }
        }
    }

    public String populateServiceList() {
        String retStr = "FAILURE";
        if (session == null
                || session.get(SessionConstant.USER_DETAILS) == null) {
            addActionError(getText("commonuser.session.expired"));
            return "SESSION_EXPIRED";
        } else {
            UserVO loggedOnUser = (UserVO) session
                    .get(SessionConstant.USER_DETAILS);
            String selectedDept = request.getParameter("selDept");
            String operation = getOperation();
            String selectedUserIdStr = request.getParameter("selectedUserId");
            int selectedUserId = 0;
            if (!CommonUtil.checkBlank(selectedUserIdStr)) {
                selectedUserId = Integer.parseInt(selectedUserIdStr);
            }

            try {
                serviceList = this.getServicesByDeptId(Integer
                        .parseInt(selectedDept));
                List<ServiceVO> assignedServices = new ArrayList<ServiceVO>();
                if (loggedOnUser.getUserType().getName().getCode() == UserType.SUPER_USER
                        .getCode()) {
                    assignedServices = serviceList;
                    setLoggedOnUserType(Integer.toString(UserType.SUPER_USER
                            .getCode()));
                } else if (loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                        .getCode()) {
                    setLoggedOnUserType(Integer
                            .toString(UserType.DEPARTMENT_ADMIN.getCode()));

                    if (CommonUserConstant.OPERATION_EDIT.equals(operation)) {
                        assignedServices = CommonUserBusiness.getInstance()
                                .getAssignedServics(selectedUserId,
                                        loggedOnUser.getDept().getId());
                    } else {
                        assignedServices = CommonUserBusiness.getInstance()
                                .getAssignedServics(loggedOnUser.getId(),
                                        loggedOnUser.getDept().getId());
                    }

                }
                if (Integer.parseInt(selectedDept) != CommonUserConstant.NOT_SELECTED) {

                    this.setSubDeptMap(serviceList);
                    servicesForUser = new int[assignedServices.size()];
                    int i = 0;

                    if (checkIfAssignedServiceSelectionRequired(loggedOnUser,
                            operation)) {
                        for (ServiceVO serviceVo : assignedServices) {
                            if (serviceVo.getParentId() != null) {
                                servicesForUser[i] = serviceVo.getId();
                                i++;
                            }
                        }
                    }
                }

                retStr = "SUCCESS";
            } catch (BusinessException bex) {
                LOGGER.fatal("CommonUserAction # populateServiceList error : "
                        + bex);
                addActionError(getText(CommonUtil.getErrorKey(bex)));
                retStr = "FAILURE";
            }
            return retStr;
        }
    }

    private boolean checkIfAssignedServiceSelectionRequired(
            UserVO loggedOnUser, String operation) {
        boolean isReqd = false;

        if (loggedOnUser.getUserType().getName().getCode() == UserType.SUPER_USER
                .getCode()
                || ((loggedOnUser.getUserType().getName().getCode() == UserType.DEPARTMENT_ADMIN
                .getCode() && CommonUserConstant.OPERATION_EDIT
                .equals(operation)))) {
            isReqd = true;
        }

        return isReqd;
    }

    /**
     * Gets the available list of locations
     *
     * @return the available list of locations
     * @throws BusinessException the business exception
     */
    public List<LocationVO> getAvailableLocationList()
            throws BusinessException {
        CommonUserBusiness commonUserBusiness = CommonUserBusiness
                .getInstance();
        UserVO loggedOnUser = (UserVO) session
                .get(SessionConstant.USER_DETAILS);
        userSearchAccess(loggedOnUser);
        for (LocationVO locationVO : commonUserBusiness.getAvailableLocationss()) {
            //LOGGER.info("*++++++ locationVO ID   ++++++ *" + locationVO.getId());
            //LOGGER.info("*++++++ locationVO NAME ++++++ *" + locationVO.getLocationName());
        }
        return commonUserBusiness.getAvailableLocationss();
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setSelectedUserId(String selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public Boolean getExitingUser() {
        return exitingUser;
    }

    public void setExitingUser(Boolean exitingUser) {
        exitingUser = exitingUser;
    }

    public String getCurrentNic() {
        return currentNic;
    }

    public void setCurrentNic(String currentNic) {
        this.currentNic = currentNic;
    }

    public Long getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(Long policeStationId) {
        this.policeStationId = policeStationId;
    }

    public List<UserVO> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<UserVO> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public Boolean getCanAccess() {
        return canAccess;
    }

    public void setCanAccess(Boolean canAccess) {
        this.canAccess = canAccess;
    }
}
