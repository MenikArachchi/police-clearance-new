package lk.icta.commonuser.framework.vo;

/**
 * The Class DepartmentVO.
 */
public class DepartmentVO {

	/** The id. */
	private int id;	

	/** The name. */
	private String name;

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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new StringBuilder("DepartmentVO: [").append("id: ")
				.append(getId()).append(", Name: ").append(getName())
				.append("]").toString();
	}
}
