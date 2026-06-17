package lk.icta.commonuser.framework.vo;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceVO.
 */
public class ServiceVO {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/** The department id. */
	private int departmentId;

	/** The parent id. */
	private Integer parentId;

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

	/**
	 * Sets the department id.
	 * 
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * Gets the department id.
	 * 
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * Sets the parent id.
	 * 
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the parent id.
	 * 
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	@Override
	public String toString() {
		return new StringBuilder("ServiceVO: [").append("id: ").append(getId())
				.append(", Name: ").append(getName()).append("parentId: ")
				.append(getParentId()).append(", departmentId: ")
				.append(getDepartmentId()).append("]").toString();
	}
}
