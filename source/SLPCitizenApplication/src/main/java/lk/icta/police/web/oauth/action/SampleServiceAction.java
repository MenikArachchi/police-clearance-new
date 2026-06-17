package lk.icta.police.web.oauth.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lk.icta.oauth.client.OAuth20Client;
import lk.icta.oauth.client.OAuthClientHandler;
import lk.icta.oauth.response.OAuthResponse;
import lk.icta.police.web.app.constant.ApplicationConstants;
import lk.icta.police.web.oauth.util.Constants;
import lk.icta.police.web.oauth.util.SessionConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SampleServiceAction extends SampleBaseAction{


    private static final long serialVersionUID = -2212675180679187232L;
    private final Log log = LogFactory.getLog(SampleServiceAction.class);
	
	private static final String THROUGH_COUNTRY_PORTAL = "cp";
	
	/** Hold the code value. */
	private String code;
	
	/** The selected provider. */
	private String selectedProvider;
	
	/** The OAuth select result. */
	private Map<String, String> oauthSelectResult;
	
	/** Hold the result status of OAuth. */
	private String result;
	
	/** The appcode value. */
	private String appcode;
	
	/** The email result. */
	private String emailResult;
	
	/** The name result. */
	private String nameResult;
	
	private String clientRequestType;
	
	private String loginAttemp;
	
	private String userName;
	
	/**
	 * 
	 * @return
	 */
	public String redirectToOArth() {
		return SUCCESS;
	}
	
	/**
     * Action method to show the index page and clear any errors and messages.
     *
     * @return SUCCESS
     */
	public String indexOauth() {	
		((ActionSupport) ActionContext.getContext().getActionInvocation().getAction()).clearErrorsAndMessages();
		log.info("App CODE - "+appcode);
		System.out.println("?????????????????????????????????????????????");
		System.out.println("App CODE - "+appcode);
		System.out.println("?????????????????????????????????????????????");
		if (appcode != null && THROUGH_COUNTRY_PORTAL.equals(appcode)) {
			getSession().put(SessionConstants.APP_CODE, appcode);
		}	
		clientRequestType=(String) getSession().get(ApplicationConstants.CLIENT_REQUEST_TYPE);
		log.info("clientRequestType : " + clientRequestType);	
		
		if(StringUtils.isNotEmpty(clientRequestType)){
			getSession().put(ApplicationConstants.EMAIL, getSession().get(SessionConstants.EMAIL));
			getSession().put(ApplicationConstants.USER_NAME, getSession().get(SessionConstants.USER_NAME));
			setUserName((String) getSession().get(ApplicationConstants.USER_NAME));
			if(clientRequestType.equals(ApplicationConstants.APPLICATION)){
				return ApplicationConstants.APPLICATION;
			}else{
				return ApplicationConstants.CLARIFICATION;
			}
		}
		return ApplicationConstants.INVALID_SESSION;

	}
    
    /**
     * View OAuth login.
     *
     * @return the string
     */
    public String viewOAuthLogin() {
    	log.info("--------- viewOAuthLogin() ----------------");
    	getSession().put(SessionConstants.OAUTH_USED, "true");
    	selectOAuthProviders();
    	return SUCCESS;
    }
    
    /**
     * Select the OAuth provider and generate the authentication code query line.
     *
     */
	public void selectOAuthProviders() {
		String throughCP = (String) getSession().get(SessionConstants.APP_CODE);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++ "+throughCP+" ++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		try {
			//if (throughCP == null || !THROUGH_COUNTRY_PORTAL.equals(throughCP)) {
				throughCP = Constants.EMPTY_STRING;
		//	} else {
			//	throughCP = throughCP + ".";
			//}
			String providerString = getText(Constants.PROVIDERS);
			String[] providerArray = providerString.split(",");
			List<String> providerList = Arrays.asList(providerArray);
			Map<String, String> providers = new LinkedHashMap<String, String>();
			String queryLine;
			OAuthClientHandler oauthClientHandler = new OAuthClientHandler();
			for (String selectedProvider : providerList) {
				queryLine = oauthClientHandler.generateQueryLine(getText(selectedProvider + Constants.CODE_LOCATION),
						getText(throughCP + Constants.REDIRECT_URI),
						getText(throughCP + selectedProvider + Constants.CLIENT_ID),
						getText(selectedProvider + Constants.SCOPE),
						getText(selectedProvider + Constants.OTHER_PARAMS));
				providers.put(selectedProvider, queryLine);
				
			}
			getSession().put(SessionConstants.PROVIDERS, providers);
			
		} catch (Exception e) {
			log.error("Exception in selectOAuthProviders() - "+e.getMessage()); 
		}
		
		
	}
	
	/**
	 * Sets the OAuth provider AJAX JSON action.
	 *
	 * @return the string
	 */
	public String setOAuthProvider() {		
		oauthSelectResult = new HashMap<String, String>();
		if (selectedProvider == null || Constants.EMPTY_STRING.equals(selectedProvider)) {
			oauthSelectResult.put("status", "error");
		} else {
			oauthSelectResult.put("status", "success");
			getSession().put(SessionConstants.OAUTH_PROVIDER, selectedProvider.trim());
		}
		
		return SUCCESS;
	}
	
	/**
	 * Check email AJAX JSON action.
	 *
	 * @return the string
	 */
	public String checkLoggedInStatus() {
		loginAttemp="PENDING";
		//System.out.println("CHECKING LOGIN STATUS!");
//		getSession().put(SessionConstants.EMAIL, "nadee158@gmail.com");
//		getSession().put(SessionConstants.USER_NAME, "nadee158");
		
		emailResult = (String) getSession().get(SessionConstants.EMAIL);
		nameResult = (String) getSession().get(SessionConstants.USER_NAME);	
		if(getSession().containsKey("LOGIN_STATUS")){
			if(StringUtils.equals((String) getSession().get("LOGIN_STATUS"), "FAILED")){
				loginAttemp="FAILED";
			}
		}
		return SUCCESS;
	}
	
	/**
	 * UPDATE ON LOGIN FALIURE
	 *
	 * @return the string
	 */
	public String updateLoginFailure() {
		System.out.println("*************************" + loginAttemp);
		System.out.println("UPDATING LOGIN STATUS FAILED!");
		getSession().put("LOGIN_STATUS", loginAttemp);
		return SUCCESS;
	}
    
    /**
     * Redirect OAuth.
     *
     * @return the string
     */
    public String redirectOAuth() {
    	String resultStatus = null;
    	String email = (String) getSession().get(SessionConstants.EMAIL);
    	selectOAuthProviders();
    	if (email == null || Constants.EMPTY_STRING.equals(email.trim())) { 
	    	if (code == null || Constants.EMPTY_STRING.equals(code.trim())) {
				addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.authProviderFailed"));
				resultStatus = INPUT;
			} else {
				final String provider = (String) getSession().get(SessionConstants.OAUTH_PROVIDER);
				if (provider == null || Constants.EMPTY_STRING.equals(provider)) {
					addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.invalidsession"));
					return INPUT;
				}
				String throughCP = (String) getSession().get(SessionConstants.APP_CODE);
				//if (throughCP == null || !THROUGH_COUNTRY_PORTAL.equals(throughCP)) {
					throughCP = Constants.EMPTY_STRING;
				//} else {
					//throughCP = throughCP + ".";
				//}
				// Get the values from the property files.
				final String tokenLocation = getText(provider + Constants.TOKEN_LOCATION);
				final String bearerReq = getText(provider + Constants.BEARER_REQUEST);
				final String tokenResJson = getText(provider + Constants.JSON_RESULT_TYPE);
				final String resultClassName = getText(provider + Constants.OAUTH_RESULT_CLASS);
				String logoutURL = getText(provider + Constants.LOGOUT_URL_PARAM);
				
				final String clientId = getText(throughCP + provider + Constants.CLIENT_ID);
				final String clientSecret = getText(throughCP + provider + Constants.CLIENT_SECRET);
				
				final String redirectUri = getText(throughCP + Constants.REDIRECT_URI);
				final String logoutRedirectURL = getText(throughCP + Constants.LOGOUT_REDIRECT_URI);
				String name = Constants.EMPTY_STRING;
				String accessToken = Constants.EMPTY_STRING;
				try {
					OAuth20Client client = new OAuth20Client();					
					accessToken = client.requestAccessToken(code, tokenLocation, redirectUri, clientId, clientSecret, tokenResJson);
					
					Class<? extends OAuthResponse> className = Class.forName(resultClassName).asSubclass(OAuthResponse.class);					
					
					OAuthResponse response = client.getGivenResponseWithToken(accessToken, bearerReq, className);										
					
					email = response.getEmail();
					name = response.getName();
					
					// Replace the logout URL's parameter values.
					String logoutLink = logoutURL.replace("<ACCESS_TOKEN>", accessToken);
					logoutLink = logoutLink.replace("<CLIENT_ID>", clientId);
					logoutLink = logoutLink.replace("<REDIRECT_URL>", logoutRedirectURL);
					getSession().put(Constants.LOGOUT_URL, logoutLink);
					
				} catch (IOException e) {
					log.error("IO Exception:" + e.getMessage());
					addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.infoFailed"));
				} catch (OAuthSystemException e) {
					log.error("OAuthSystemException:" + e.getMessage());
					addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.infoFailed"));
				} catch (OAuthProblemException e) {
					log.error("OAuthProblemException:" + e.getMessage());
					addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.infoFailed"));
				} catch (ParseException e) {
					log.error("ParseException:" + e.getMessage());
					addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.infoFailed"));
				} catch (ClassNotFoundException e) {
					log.error("ClassNotFoundException:" + e.getMessage());
					addActionMessage(resourceMessage.getDisplayMessages().getString("error.oauth.infoFailed"));
				} 
				
				getSession().put(SessionConstants.EMAIL, email);
				log.info("Email: " + email);
				getSession().put(SessionConstants.USER_NAME, name);
				log.info("Name: " + name);
				getSession().put(Constants.ACCESS_TOKEN, accessToken);
				log.info("Access Token: " + accessToken);
				
				if (email == null || Constants.EMPTY_STRING.equals(email.trim())) {
					resultStatus = INPUT;
				} else {
					resultStatus = SUCCESS;
				}
			}
    	} else {
    		resultStatus = SUCCESS;
    	}
    	return resultStatus;
	
    	
    	
    }
    
    /**
     * Logout.
     *
     * @return the string
     */
    public String logout() {
    	getSession().put(SessionConstants.EMAIL, Constants.EMPTY_STRING);
    	getSession().put(SessionConstants.USER_NAME, Constants.EMPTY_STRING);
    	getSession().put(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING);
    	log.info("User logged out......................................");
    	getSession().clear();
    	try {
    		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
        	session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    /**
     * Redirect OAuth Provider logout.
     *
     * @return the string
     */
    public String redirectOAuthLogout() {
     	return SUCCESS;
    }
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param codeValue the new code
	 */
	public void setCode(final String codeValue) {
		this.code = codeValue;
	}

	/**
	 * @return the selectedProvider
	 */
	public String getSelectedProvider() {
		return selectedProvider;
	}

	/**
	 * @param selectedProviderValue the selectedProvider to set
	 */
	public void setSelectedProvider(final String selectedProviderValue) {
		this.selectedProvider = selectedProviderValue;
	}

	/**
	 * @return the oauthSelectResult
	 */
	public Map<String, String> getOauthSelectResult() {
		return oauthSelectResult;
	}

	/**
	 * @param oauthSelectResultValue the oauthSelectResult to set
	 */
	public void setOauthSelectResult(final Map<String, String> oauthSelectResultValue) {
		this.oauthSelectResult = oauthSelectResultValue;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param resultValue the result to set
	 */
	public void setResult(final String resultValue) {
		this.result = resultValue;
	}

	/**
	 * @return the appcode
	 */
	public String getAppcode() {
		return appcode;
	}

	/**
	 * @param appcodeValue the appcode to set
	 */
	public void setAppcode(final String appcodeValue) {
		this.appcode = appcodeValue;
	}

	/**
	 * @return the emailResult
	 */
	public String getEmailResult() {
		return emailResult;
	}

	/**
	 * @param emailResultValue the emailResult to set
	 */
	public void setEmailResult(final String emailResultValue) {
		this.emailResult = emailResultValue;
	}

	/**
	 * @return the nameResult
	 */
	public String getNameResult() {
		return nameResult;
	}

	/**
	 * @param nameResult the nameResult to set
	 */
	public void setNameResult(String nameResult) {
		this.nameResult = nameResult;
	}

	public String getClientRequestType() {
		return clientRequestType;
	}

	public void setClientRequestType(String clientRequestType) {
		this.clientRequestType = clientRequestType;
	}

	public String getLoginAttemp() {
		return loginAttemp;
	}

	public void setLoginAttemp(String loginAttemp) {
		this.loginAttemp = loginAttemp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
