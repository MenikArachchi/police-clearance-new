package lk.icta.police.action;

import java.util.Map;

import lk.icta.commonuser.framework.app.business.CommonUserClientBusiness;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The class ChangePasswordAction
 */
public class ChangePasswordAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = -8133536190168436492L;
	private static final Logger LOGGER = Logger.getLogger(ChangePasswordAction.class);
	private Integer userId;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	
	private Map<String, Object> session;

	
	public String execute() {
		
		UserVO user = (UserVO)session.get(PoliceConstant.SESSION_USER);
		
		if (user != null) {
			userId = user.getId();
		}
		CommonUserClientBusiness commonUserClientBusiness = new CommonUserClientBusiness();
		boolean isSuccessful = false;
		try {
			if(validatePassword().equals(SUCCESS) ){
				isSuccessful = commonUserClientBusiness.changePassword(userId, oldPassword, confirmNewPassword);
			}
		} catch (BusinessException e) {
			if (e.getErrorCode() == ErrorCodeConstant.INCORRECT_PASSWORD) {
				addActionError("Failed to change the password. The current password is incorrect!");
				return ERROR;			
			} else {
				addActionError("Failed to change the password. Internal server error!");
				return ERROR;
			}
		}
		
		if (!isSuccessful) {
			addActionError("Failed to change the password, please try again!");
			return ERROR;
		}
		addActionMessage("Password changed successfully");
		
		return SUCCESS;
	}
	
	
	public String validatePassword() {
		
		UserVO user = (UserVO)session.get(PoliceConstant.SESSION_USER);
		
		if (user != null) {
			userId = user.getId();
		}
		
		if (userId == null) {
			addFieldError("userId", "You have not entered all required data. Please check again");
			return ERROR;
		} 
		
		if (CommonUtil.checkBlank(oldPassword)) {
			addFieldError("oldPassword", "You have not entered all required data. Please check again");
			return ERROR;
		}
		
		if (CommonUtil.checkBlank(newPassword)) {
			addFieldError("newpassword", "You have not entered all required data. Please check again");
			return ERROR;
		}
		
		if (CommonUtil.checkBlank(confirmNewPassword)) {
			addFieldError("confirmNewPassword", "You have not entered all required data. Please check again");
			return ERROR;
		}
		
		if (oldPassword != null  && newPassword != null && newPassword.equals(oldPassword)) {
			addActionError("Current password and new password cannot be same");
			return ERROR;
		}
		
		if (newPassword != null  && confirmNewPassword != null && !newPassword.equals(confirmNewPassword)) {
			addActionError("New password and confirm password should be the same. Please enter again");
			return ERROR;
		}
		
		if (newPassword != null && !checkIfPasswordCompatible(newPassword)) {
			addFieldError("newPassword", "Incorrect password format. The minimum password length should be six characters with alpha and numeric characters");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * Return true if password is compatible
	 * @param passwordStr
	 * @return
	 */
	private boolean checkIfPasswordCompatible(String passwordStr) {
		boolean isPasswordCompatible = true;
		if (passwordStr.length() < 6) {
			isPasswordCompatible = false;
		} else {
			char[] passwordCharArray = passwordStr.toCharArray();
			boolean atLeastOneNumericPresent = false;
			boolean atLeastOneAlphabetPresent = false;
			for (char passwordChar : passwordCharArray) {
				if (Character.isDigit(passwordChar)) {
					atLeastOneNumericPresent = true;
				}
				
				if (Character.isLetter(passwordChar)) {
					atLeastOneAlphabetPresent = true;
				}
			}
			
			if (!atLeastOneNumericPresent || !atLeastOneAlphabetPresent) {
				isPasswordCompatible = false;
			}
		}
		
		return isPasswordCompatible;
	}


}
