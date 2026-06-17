/**
 * 
 */
package lk.icta.oauth.response;

/**
 * @author dvirajith
 * This class should be extended by all response types which gets the email and name.
 */
public interface OAuthResponse {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail();
}
