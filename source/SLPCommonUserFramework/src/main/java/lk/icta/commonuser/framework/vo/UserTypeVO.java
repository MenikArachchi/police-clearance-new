package lk.icta.commonuser.framework.vo;

import lk.icta.commonuser.framework.constant.CommonUserEnumConstant.UserType;

// TODO: Auto-generated Javadoc
/**
 * The Class UserTypeVO.
 */
public class UserTypeVO {

	/** The id. */
	private int id;

	/** The name. */
	private UserType name;

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(UserType name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public UserType getName() {
		return name;
	}

	@Override
	public String toString() {
		return new StringBuilder("UserTypeVO: [").append("id: ")
				.append(getId()).append(", Name: ").append(getName())
				.append("]").toString();
	}

}
