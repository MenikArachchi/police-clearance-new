package lk.icta.police.external.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lk.icta.commonuser.framework.app.business.CommonUserClientBusiness;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.police.external.servlet.CaptchaGeneratorServlet;
import lk.icta.police.framework.utility.CommonUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 *  The class ResetPasswordAction
 */
public class ResetPasswordAction extends ActionSupport {
	
	private static final long serialVersionUID = -3416270379664927873L;
	
	// For reset password
	private String userName;
	private String email;
	private String uword;
	
	public String execute() {
		CommonUserClientBusiness commonUserClientBusiness = new CommonUserClientBusiness();
		boolean isSuccessful = false;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
        	HttpSession session = request.getSession(false);
			String captchaText= (String)session.getAttribute(CaptchaGeneratorServlet.CAPTCHA_KEY) ;
			if(!getUword().equals(captchaText) ){
				addActionError("Please enther the text varification correctly.");
				uword="";
			}
			else{
				if (!checkMail(email)) { // check the email pattern
					addActionError("User Name or the E-mail address is incorrect");
					email="";
				}				
				isSuccessful = commonUserClientBusiness.resetPassword(userName, email);
			}
			
		} catch (BusinessException e) {
			if (e.getErrorCode() == ErrorCodeConstant.INCOMPITABLE_USERNAME_EMAIL) {
				addActionError("User Name or the E-mail address is incorrect");
				uword="";
				return ERROR;			
			} else {
				addActionError("System error occurred, Please try again later");
				uword="";
				return ERROR;
			}
		}
		if (!isSuccessful) {
			addActionError("An error occurred. Please try again later");
			return ERROR;
		}
		addActionMessage("System generated password has been sent to the registered email address.");
		userName="";
		email="";
		uword="";
		return SUCCESS;
	}
	
	
	public void validate() {
		if (CommonUtil.checkBlank(userName)) {
			addFieldError("userName", "You have not entered all required data. Please check again");
		} 
		
		else if (CommonUtil.checkBlank(email)) {
			addFieldError("email", "You have not entered all required data. Please check again");
		}
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getUword() {
		return uword;
	}

	public void setUword(String uword) {
		this.uword = uword;
	}
	
	/**
	 * this method is used to validate the email address
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkMail(String email) {
		String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
