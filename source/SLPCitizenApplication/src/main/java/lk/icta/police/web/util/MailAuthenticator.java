package lk.icta.police.web.util;

import java.io.Serializable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator  implements Serializable {
	
	private static final long serialVersionUID = 287775135599665740L;
	
	private String userName = null;
	private String password = null;
 
	/**
	 * Instantiates a new mail authenticator.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	public MailAuthenticator(String userName,String password) {
		super();
		this.userName = userName;
		this.password = password; 
	}
 
	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Gets the user name.
   *
   * @return the user name
   */
  public String getUserName()
  {
    return userName;
  }

  /**
   * Sets the user name.
   *
   * @param userName the new user name
   */
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
}

