/**
 * 
 */
package lk.icta.oauth.response;

/**
 * @author dvirajith
 * 
 * Designed to support the following OAuth 2.0 providers.
 * 
 * Google, Facebook, Live, LinkedIn.
 * 
 */
public class CommonResponse implements OAuthResponse {

	/** Hold the name value. */
	private String name;

	/** Hold the email value. */
	private String email;

	/** Hold the id value. */
	private String id;

	/** Hold the verified_email value. */
	private String verified_email;

	/** Hold the email address value. */
	private String emailAddress;

	/** Hold the first name value. */
	private String firstName;

	/** Hold the middle name value. */
	private String middleName;

	/** Hold the last name value. */
	private String lastName;

	/** Hold the gender value. */
	private String gender;

	/** Hold the email list. */
	private Emails emails;

	/**
	 * Instantiates a new common response.
	 */
	public CommonResponse() {

	}

	/**
	 * @return the name
	 */
	public String getName() {
		String result;
		if (name != null && !"".equals(name.trim())) {
			result = name;
		} else if (firstName != null && !"".equals(firstName.trim())) {
			result = firstName + " " + lastName;
		} else {
			result = "";
		}
		return result;
	}

	/**
	 * @param nameValue
	 *            the name to set
	 */
	public void setName(final String nameValue) {
		this.name = nameValue;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		String result;
		if (email != null && !"".equals(email.trim())) {
			result = email;
		} else if (emailAddress != null && !"".equals(emailAddress.trim())) {
			result = emailAddress;
		} else if (emails != null && !"".equals(emails.getPreferred())) {
			result = emails.getPreferred();
		} else {
			result = "";
		}
		return result;
	}

	/**
	 * @param emailValue
	 *            the email to set
	 */
	public void setEmail(final String emailValue) {
		this.email = emailValue;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idValue
	 *            the id to set
	 */
	public void setId(final String idValue) {
		this.id = idValue;
	}

	/**
	 * @return the verified_email
	 */
	public String getVerified_email() {
		return verified_email;
	}

	/**
	 * @param verified_emailValue
	 *            the verified_email to set
	 */
	public void setVerified_email(final String verified_emailValue) {
		this.verified_email = verified_emailValue;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddressValue
	 *            the emailAddress to set
	 */
	public void setEmailAddress(final String emailAddressValue) {
		this.emailAddress = emailAddressValue;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstNameValue
	 *            the firstName to set
	 */
	public void setFirstName(final String firstNameValue) {
		this.firstName = firstNameValue;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastNameValue
	 *            the lastName to set
	 */
	public void setLastName(final String lastNameValue) {
		this.lastName = lastNameValue;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleNameValue
	 *            the middleName to set
	 */
	public void setMiddleName(final String middleNameValue) {
		this.middleName = middleNameValue;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param genderValue
	 *            the gender to set
	 */
	public void setGender(final String genderValue) {
		this.gender = genderValue;
	}

	/**
	 * @return the email list
	 */
	public Emails getEmails() {
		return emails;
	}

	/**
	 * @param emailsValue
	 *            the email list to set
	 */
	public void setEmails(final Emails emailsValue) {
		this.emails = emailsValue;
	}
}
