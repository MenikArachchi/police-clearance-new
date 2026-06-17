package lk.icta.commonuser.framework.exception;

import lk.icta.commonuser.framework.utility.ErrorKeyLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessException.
 */
public class BusinessException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9046588951532319094L;

	/** The error key. */
	private transient String errorKey;

	private transient int errorCode;

	/**
	 * Gets the error key.
	 * 
	 * @return the error key
	 */
	public String getErrorKey() {
		return errorKey;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Instantiates a new business exception.
	 * 
	 * @param errorKey
	 *            the error key
	 * @param cause
	 *            the cause
	 */
	public BusinessException(String errorKey, Throwable cause) {
		super(errorKey, cause);
		this.errorKey = errorKey;
	}

	/**
	 * Instantiates a new business exception.
	 * 
	 * @param errorKey
	 *            the error key
	 */
	public BusinessException(String errorKey) {
		super(errorKey);
		this.errorKey = errorKey;
	}
	
	public BusinessException(int errorCode) {
		super(ErrorKeyLoader.getErrorKey(errorCode));
		this.errorCode = errorCode;
	}

	/**
	 * Instantiates a new business exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	
}
