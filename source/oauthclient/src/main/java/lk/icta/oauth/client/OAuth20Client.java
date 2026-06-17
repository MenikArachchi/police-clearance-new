/**
 * 
 */
package lk.icta.oauth.client;

import java.io.IOException;

import lk.icta.oauth.response.CommonResponse;
import lk.icta.oauth.response.OAuthResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.google.gson.Gson;

/**
 * @author dvirajith
 *
 */
public class OAuth20Client {
	
	/** Hold the query line for request. */
	private String queryLine;
	
	/** Hold the access token value. */
	private String accessToken;
	
	/** Hold the common res value. */
	private CommonResponse comRes;
	
	/**
	 * Instantiates a new o auth20 client.
	 */
	public OAuth20Client() {
		
	}
	
	/**
	 * Generate query line.
	 *
	 * @param accessCodeUri the access code URI
	 * @param redirectUri the redirect URI
	 * @param clientId the client id
	 * @param Scope the scope
	 * @param otherParams the other params
	 * @return the string
	 */
	public String generateQueryLine(String accessCodeUri, String redirectUri, String clientId, String scope, String otherParams) {

		queryLine = accessCodeUri + "?redirect_uri=" + redirectUri + "&client_id=" + clientId + "&scope=" + scope + otherParams;

		return queryLine;
	}
	
	/**
	 * Request access token.
	 *
	 * @param accessCode the access code
	 * @param tokenLocation the token location
	 * @param redirectUri the redirect uri
	 * @param clientId the client id
	 * @param clientSecret the client secret
	 * @param tokenResponseJson the token response json
	 * @return the string
	 */
	public String requestAccessToken(String accessCode, String tokenLocation, String redirectUri, String clientId,
			String clientSecret, String tokenResponseJson) throws OAuthSystemException, OAuthProblemException {
		System.out.println("######################################3 WS Client #######################################"); 
		OAuthClientRequest request;
		try {
			request = OAuthClientRequest.tokenLocation(tokenLocation)
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setCode(accessCode)
					.setRedirectURI(redirectUri).setClientId(clientId).setClientSecret(clientSecret)
					.buildBodyMessage();

			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			if ("N".equals(tokenResponseJson)) {
				GitHubTokenResponse gitHubTokenResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);
				setAccessToken(gitHubTokenResponse.getAccessToken());
			} else {
				OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(request);
				setAccessToken(oAuthResponse.getAccessToken());
			}
			
		} catch (OAuthSystemException e) {
			setAccessToken("");
			throw e;
		} catch (OAuthProblemException e) {
			setAccessToken("");
			throw e;
		}

		return getAccessToken();
	}
	
	/**
	 * Gets the user info with token which results the plain user information string.
	 *
	 * @param accessToken the access token
	 * @param bearerRequest the bearer request
	 * @return the user info with token
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public String getUserInfoWithToken(String accessToken, String bearerRequest) throws ClientProtocolException, IOException,
			ParseException {
		String userInfo = null;
		
		try {
			CloseableHttpClient client = HttpClients.createDefault();

			HttpGet post = new HttpGet(bearerRequest + accessToken);
			HttpResponse response = client.execute(post);

			HttpEntity entity = response.getEntity();
			userInfo = EntityUtils.toString(entity);

		} catch (ClientProtocolException e) {
			setComRes(null);
			throw e;
		} catch (IOException e) {
			setComRes(null);
			throw e;
		} catch (ParseException e) {
			setComRes(null);
			throw e;
		}
		
		return userInfo;
	}
	
	/**
	 * Request information with token in the CommonResponse format.
	 *
	 * @param accessToken the access token
	 * @param bearerRequest the bearer request
	 * @return the common response
	 */
	public CommonResponse getResponseWithToken(String accessToken, String bearerRequest) throws ClientProtocolException,
			IOException, ParseException {

		String userInfo = getUserInfoWithToken(accessToken, bearerRequest);
		Gson gson = new Gson();
		setComRes(gson.fromJson(userInfo, CommonResponse.class));
		
		return getComRes();
	}

	/**
	 * Gets the given response with token.
	 *
	 * @param accessToken the access token
	 * @param bearerRequest the bearer request
	 * @param returnType the return type
	 * @return the given response with token
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings({ "unchecked"})
	public <T extends OAuthResponse> T getGivenResponseWithToken(String accessToken, String bearerRequest, Class<T> returnType)
			throws ClientProtocolException, IOException, ParseException {
		Object result;

		String userInfo = getUserInfoWithToken(accessToken, bearerRequest);
		Gson gson = new Gson();
		result = gson.fromJson(userInfo, returnType);

		return (T) result;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessTokenValue the accessToken to set
	 */
	public void setAccessToken(final String accessTokenValue) {
		this.accessToken = accessTokenValue;
	}

	/**
	 * @return the comRes
	 */
	public CommonResponse getComRes() {
		return comRes;
	}

	/**
	 * @param comResValue the comRes to set
	 */
	public void setComRes(final CommonResponse comResValue) {
		this.comRes = comResValue;
	}

	/**
	 * @return the queryLine
	 */
	public String getQueryLine() {
		return queryLine;
	}

	/**
	 * @param queryLineValue the queryLine to set
	 */
	public void setQueryLine(final String queryLineValue) {
		this.queryLine = queryLineValue;
	}
}
