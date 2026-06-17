package lk.icta.commonuser.framework.vo;

import java.io.Serializable;

/**
 * 
 * @author smarambage001
 *
 */
public class LocationVO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9092355391075776982L;

	/** The id. */
	private int id;

	/** The name. */
	private String locationName;

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
}
