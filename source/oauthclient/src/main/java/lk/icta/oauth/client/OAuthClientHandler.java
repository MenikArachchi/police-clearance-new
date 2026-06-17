/**
 * 
 */
package lk.icta.oauth.client;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import lk.icta.oauth.response.CommonResponse;


/**
 * @author dvirajith
 * 
 */
public class OAuthClientHandler {

	/** The token location. */
	private String tokenLocation;

	/** The redirect uri. */
	private String redirectUri;

	/** The client id. */
	private String clientId;

	/** The client secret. */
	private String clientSecret;

	/** The bearer request. */
	private String bearerRequest;

	/** The access token. */
	private String accessToken;

	/** The access code. */
	private String accessCode;
	
	private OAuth20Client client;

	/**
	 * Instantiates a new OAuth client.
	 */
	public OAuthClientHandler() {
		client = new OAuth20Client();
	}

	/**
	 * Instantiates a new OAuth client.
	 * 
	 * @param accessCode
	 *            the access code
	 * @param tokenLocation
	 *            the token location
	 * @param redirectUri
	 *            the redirect URI
	 * @param clientId
	 *            the client id
	 * @param clientSecret
	 *            the client secret
	 */
	public OAuthClientHandler(String accessCode, String tokenLocation, String redirectUri, String clientId, String clientSecret) {
		this.accessCode = accessCode;
		this.tokenLocation = tokenLocation;
		this.redirectUri = redirectUri;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	public String generateQueryLine(String accessCodeUri, String redirectUri, String clientId, String scope, String otherParams) { 
		return client.generateQueryLine(accessCodeUri, redirectUri, clientId, scope, otherParams);
	}
	
	/**
	 * Request information.
	 *
	 * @param accessCode the access code
	 * @param tokenLocation the token location
	 * @param redirectUri the redirect uri
	 * @param clientId the client id
	 * @param clientSecret the client secret
	 * @param bearerRequest the bearer request
	 * @return the common response
	 */
	public CommonResponse getResponse(String accessCode, String tokenLocation, String redirectUri, String clientId,
			String clientSecret, String bearerRequest, String tokenResponseJson) throws OAuthSystemException,
			OAuthProblemException, ClientProtocolException, IOException, ParseException {

		client = new OAuth20Client();
		
		accessToken = client.requestAccessToken(accessCode, tokenLocation, redirectUri, clientId, clientSecret, tokenResponseJson);

		return client.getResponseWithToken(accessToken, bearerRequest);
	}
	
	/**
	 * @return the tokenLocation
	 */
	public String getTokenLocation() {
		return tokenLocation;
	}

	/**
	 * @param tokenLocationValue
	 *            the tokenLocation to set
	 */
	public void setTokenLocation(final String tokenLocationValue) {
		this.tokenLocation = tokenLocationValue;
	}

	/**
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}

	/**
	 * @param redirectUriValue
	 *            the redirectUri to set
	 */
	public void setRedirectUri(final String redirectUriValue) {
		this.redirectUri = redirectUriValue;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientIdValue
	 *            the clientId to set
	 */
	public void setClientId(final String clientIdValue) {
		this.clientId = clientIdValue;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecretValue
	 *            the clientSecret to set
	 */
	public void setClientSecret(final String clientSecretValue) {
		this.clientSecret = clientSecretValue;
	}

	/**
	 * @return the bearerRequest
	 */
	public String getBearerRequest() {
		return bearerRequest;
	}

	/**
	 * @param bearerRequestValue
	 *            the bearerRequest to set
	 */
	public void setBearerRequest(final String bearerRequestValue) {
		this.bearerRequest = bearerRequestValue;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessTokenValue
	 *            the accessToken to set
	 */
	public void setAccessToken(final String accessTokenValue) {
		this.accessToken = accessTokenValue;
	}

	/**
	 * @return the accessCode
	 */
	public String getAccessCode() {
		return accessCode;
	}

	/**
	 * @param accessCodeValue
	 *            the accessCode to set
	 */
	public void setAccessCode(final String accessCodeValue) {
		this.accessCode = accessCodeValue;
	}

}
