/**
 * 
 */
package lk.icta.oauth.response;

/**
 * @author dvirajith
 * For Accessing Email when multiple email addresses are sent from providers.
 */
public class Emails {
	
	/** Hold the preferred email. */
	private String preferred;
	
	/** Hold the account email. */
	private String account;
	
	/** Hold the personal email. */
	private String personal;
	
	/** Hold the business email . */
	private String business;

	public Emails() {
		
	}
	
	/**
	 * @return the preferred
	 */
	public String getPreferred() {
		return preferred;
	}

	/**
	 * @param preferred the preferred to set
	 */
	public void setPreferred(String preferred) {
		this.preferred = preferred;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the personal
	 */
	public String getPersonal() {
		return personal;
	}

	/**
	 * @param personal the personal to set
	 */
	public void setPersonal(String personal) {
		this.personal = personal;
	}

	/**
	 * @return the business
	 */
	public String getBusiness() {
		return business;
	}

	/**
	 * @param business the business to set
	 */
	public void setBusiness(String business) {
		this.business = business;
	}
}
