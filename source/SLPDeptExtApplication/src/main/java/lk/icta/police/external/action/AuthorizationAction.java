package lk.icta.police.external.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lk.icta.commonuser.framework.app.business.CommonUserClientBusiness;
import lk.icta.commonuser.framework.constant.ErrorCodeConstant;
import lk.icta.commonuser.framework.vo.ServiceVO;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 
 * @author 
 *
 */
public class AuthorizationAction extends ActionSupport implements SessionAware,ServletRequestAware{
	private static final long serialVersionUID = -8517227229292215672L;
	private static final Logger LOGGER = Logger.getLogger(AuthorizationAction.class);
	private Map<String, Object> session;
	private String userName;
	private String password;
	private String department="1"; // we have only one department
	private HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
	
	/** The request. */
	private HttpServletRequest request; 
	
	/**
	 * execute
	 */
	public String execute() {
		UserVO user =null;
		try {
			LOGGER.info("DEPARTMENT : "+department);
			LOGGER.info("USER NAME  : "+userName);
			
			if(userName.trim().equals("") || password.trim().equals("")){
				LOGGER.warn("AuthorizationAction :: USRE NAME AND PASSWORD IS EMPTY.");
				addActionError("Username and Password is Required");	
				return ERROR;	
			}
			
			CommonUserClientBusiness commonUserClientBusiness = new CommonUserClientBusiness();
			user = commonUserClientBusiness.validateUser(userName, password, Integer.parseInt(department), 1);
		
			LOGGER.info("<<=============== GET USER ==================>>");
			LOGGER.info("USER OBJECT -> "+user);
			LOGGER.info("USER NAME : "+user.getUserName());
			LOGGER.info("USER ID   : "+user.getId());
			LOGGER.info("USER TYPE : "+user.getUserType());	
			LOGGER.info("DEPT_ID : "+user.getDept().getId());
			LOGGER.info("DEPT_NAME : "+user.getDept().getName());
			
			if (user == null || CommonUtil.checkBlank(user.getUserName())) {
				LOGGER.warn("||===============BLANK USER NAME ==================||");
				addActionError("Invalid User Name or Password. Please try again");
				return ERROR; 
			}  else if (user.getUserType().getId() == 1){
				addActionError("You are not authorized to access this application");
				return ERROR; 
			}
			
			session.put(PoliceConstant.SESSION_USER, user);
			session.put(PoliceConstant.SESSION_USER_USER_NAME, getUserName());
			session.put(PoliceConstant.SESSION_USER_FULL_NAME, user.getFullName());
			session.put(PoliceConstant.SESSION_USER_ID, user.getId());
			if(!(user.getDept()==null)){
				session.put(PoliceConstant.SESSION_USER_ROLE, user.getDept().getId());
			}	
			session.put(PoliceConstant.SESSION_USER_LOCATION, user.getAssignedLocation());
			session.put(PoliceConstant.SESSION_USER_EMAIL, user.getEmailId());
		
		} catch (lk.icta.commonuser.framework.exception.BusinessException e) {
			LOGGER.error("AuthorizationAction :: execute() :: Exception - "+e.getErrorCode());		
			if (e.getErrorCode() == ErrorCodeConstant.AUTHONTICATION_ERROR) { 
				addActionError("You are not authorized to access this application");	
				return ERROR;
			} else if(e.getErrorCode() == ErrorCodeConstant.ACCESS_DENIED){
				addActionError("You are not authorized to access this application");
				return ERROR;
			} else {
				addActionError("Invalid User Name or Password. Please try again");			
				return ERROR;
			}
		}
		List<ServiceVO> serviceVOList = user.getAssignedServices();
		LOGGER.warn("AuthorizationAction :: SERVICE SIZE -> "+serviceVOList.size());
		List<Integer> assignedServiceIdList = new ArrayList<Integer>();

		int count = 1;		
		for (ServiceVO serviceVO : serviceVOList) { 
			hashMap.put(count, serviceVO.getName());
			assignedServiceIdList.add(serviceVO.getId());
			LOGGER.warn("AuthorizationAction SERVICE_ID - "+serviceVO.getId()+" SERVICE_NAME - " + serviceVO.getName());
			count++;
		}

		session.put(PoliceConstant.ASSIGNED_SERVICE_ID_LIST, assignedServiceIdList);
		session.put(PoliceConstant.ASSIGNED_SERVICE_ID_MAP, hashMap);
		
		return SUCCESS;
				
	}
	
	/**
	 * To Logout
	 * 
	 * @return
	 */
	public String doLogout(){
		String retStr = "error";
		if (session == null || (String)session.get("userName") == null || session.get("userName").equals("")) {
			return "invalid-session";
		}else{
			try {
				session.clear();
				request.getSession().invalidate();
				retStr = "success";
			} catch (Exception bex) {
				LOGGER.fatal("UserLogoutAction # execute error : " + bex);
				retStr = "error";
			}
		}		
		return retStr;
	}
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String depatment) {
		this.department = depatment;
	}
	public HashMap<Integer, String> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<Integer, String> hashMap) {
		this.hashMap = hashMap;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
