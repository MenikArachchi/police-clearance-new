/**
 * 
 */
package lk.icta.police.web.oauth.util;

/**
 * @author dvirajith Contains the constants of the Application.
 */
public interface Constants {

	/* Property names. */
	/** The providers. */
	public String PROVIDERS = "oauth.providers";

	/** The code location. */
	public String CODE_LOCATION = ".oauth.codeLocation";

	/** The redirect URL. */
	public String REDIRECT_URI = "common.oauth.redirectUri";

	/** The logout redirect URL. */
	public String LOGOUT_REDIRECT_URI = "common.oauth.logout.redirect";
	
	/** The client id. */
	public String CLIENT_ID = ".oauth.clientId";

	/** The scope. */
	public String SCOPE = ".oauth.scope";

	/** The other parameters. */
	public String OTHER_PARAMS = ".oauth.params";

	/** The token location. */
	public String TOKEN_LOCATION = ".oauth.tokenLocation";

	/** The client secret. */
	public String CLIENT_SECRET = ".oauth.clientSecret";

	/** The bearer request. */
	public String BEARER_REQUEST = ".oauth.bearerRequest";

	public String OAUTH_RESULT_CLASS = ".oauth.result.class";
	
	/** The JSON result type. */
	public String JSON_RESULT_TYPE = ".oauth.token.json";
	
	/** The logout URL parameter. */
	public String LOGOUT_URL_PARAM = ".oauth.provider.logout";

	/* Other Constants. */
	/** The empty string. */
	public String EMPTY_STRING = "";
	
	/** The logout URL. */
	public String LOGOUT_URL = "LOGOUT_URL";
	
	/** The access token. */
	public String ACCESS_TOKEN = "ACCESS_TOKEN";
}
