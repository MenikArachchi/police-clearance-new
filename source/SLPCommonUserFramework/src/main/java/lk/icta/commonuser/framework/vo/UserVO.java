package lk.icta.commonuser.framework.vo;

import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserStatus;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class UserVO.
 */
public class UserVO {

    /**
     * The id.
     */
    private int id;

    /**
     * The full name.
     */
    private String fullName;

    /**
     * The email id.
     */
    private String emailId;

    /**
     * The user type.
     */
    private UserTypeVO userType;

    /**
     * The user name.
     */
    private String userName;

    /**
     * The password.
     */
    private String password;

    /**
     * The dept.
     */
    private DepartmentVO dept;

    /**
     * The assigned services.
     */
    private List<ServiceVO> assignedServices;

    /**
     * The assignable users.
     */
    private List<UserTypeVO> assignableUsers;

    /**
     * The status.
     */
    private UserStatus status;

    /**
     * The created by.
     */
    private int createdBy;

    /**
     * The modified by.
     */
    private int modifiedBy;

    /**
     * The assigned location
     */
    private long assignedLocation;

    private String currentNic;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the full name.
     *
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name.
     *
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the email id.
     *
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the email id.
     *
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * Gets the user type.
     *
     * @return the userType
     */
    public UserTypeVO getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     *
     * @param userType the userType to set
     */
    public void setUserType(UserTypeVO userType) {
        this.userType = userType;
    }

    /**
     * Gets the user name.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the dept.
     *
     * @return the dept
     */
    public DepartmentVO getDept() {
        return dept;
    }

    /**
     * Sets the dept.
     *
     * @param dept the dept to set
     */
    public void setDept(DepartmentVO dept) {
        this.dept = dept;
    }

    /**
     * Gets the assigned services.
     *
     * @return the assignedServices
     */
    public List<ServiceVO> getAssignedServices() {
        return assignedServices;
    }

    /**
     * Sets the assigned services.
     *
     * @param assignedServices the assignedServices to set
     */
    public void setAssignedServices(List<ServiceVO> assignedServices) {
        this.assignedServices = assignedServices;
    }

    /**
     * Gets the assignable users.
     *
     * @return the assignableUsers
     */
    public List<UserTypeVO> getAssignableUsers() {
        return assignableUsers;
    }

    /**
     * Sets the assignable users.
     *
     * @param assignableUsers the assignableUsers to set
     */
    public void setAssignableUsers(List<UserTypeVO> assignableUsers) {
        this.assignableUsers = assignableUsers;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Gets the created by.
     *
     * @return the createdBy
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the modified by.
     *
     * @return the modifiedBy
     */
    public int getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the assigned Location.
     *
     * @return the assignedLocation
     */
    public long getAssignedLocation() {
        return assignedLocation;
    }

    /**
     * Sets the assigned Location.
     *
     * @param assignedLocation the assignedLocation to set
     */
    public void setAssignedLocation(long assignedLocation) {
        this.assignedLocation = assignedLocation;
    }

    @Override
    public String toString() {
        return new StringBuilder("UserVO: [").append("id: ").append(getId())
                .append(", fullName: ").append(getFullName())
                .append(", emailId: ").append(getEmailId())
                .append(", fullName: ").append(getFullName())
                .append(", userType: ").append(getUserType())
                .append(", userName: ").append(getUserName())
                .append(", dept: ").append(getDept())
                .append(", status: ").append(getStatus())
                .append(", currentNic: ").append(getCurrentNic())
                .append("]").toString();
    }

    public String getCurrentNic() {
        return currentNic;
    }

    public void setCurrentNic(String currentNic) {
        this.currentNic = currentNic;
    }
}
