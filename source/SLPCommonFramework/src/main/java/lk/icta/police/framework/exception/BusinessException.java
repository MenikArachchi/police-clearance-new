package lk.icta.police.framework.exception;

/**
 * the class BusinessException. This class is used to create a new exception
 * called BusinessException
 * 
 * @author pwcl
 * 
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -9046588951532319094L;

	private String errorKey;

	public String getErrorKey() {
		return errorKey;
	}

	/**
	 * @param errorKey
	 * @param cause
	 */
	public BusinessException(String errorKey, Throwable cause) {
		super(errorKey, cause);
		this.errorKey = errorKey;
	}

	/**
	 * @param errorKey
	 */
	public BusinessException(String errorKey) {
		super(errorKey);
		this.errorKey = errorKey;
	}

	/**
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}
}
