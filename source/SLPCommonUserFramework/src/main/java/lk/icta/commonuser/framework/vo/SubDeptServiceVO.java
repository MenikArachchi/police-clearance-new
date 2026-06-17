package lk.icta.commonuser.framework.vo;

import java.util.List;

public class SubDeptServiceVO {
	private int subDeptId;
	private String subDeptName;
	private List<ServiceVO> deptServices;

	/**
	 * @param subDeptId
	 *            the subDeptId to set
	 */
	public void setSubDeptId(int subDeptId) {
		this.subDeptId = subDeptId;
	}

	/**
	 * @return the subDeptId
	 */
	public int getSubDeptId() {
		return subDeptId;
	}

	/**
	 * @param subDeptName
	 *            the subDeptName to set
	 */
	public void setSubDeptName(String subDeptName) {
		this.subDeptName = subDeptName;
	}

	/**
	 * @return the subDeptName
	 */
	public String getSubDeptName() {
		return subDeptName;
	}

	/**
	 * @param deptServices
	 *            the deptServices to set
	 */
	public void setDeptServices(List<ServiceVO> deptServices) {
		this.deptServices = deptServices;
	}

	/**
	 * @return the deptServices
	 */
	public List<ServiceVO> getDeptServices() {
		return deptServices;
	}

	@Override
	public String toString() {
		return new StringBuilder("SubDeptServiceVO: [").append("subDeptId: ")
				.append(getSubDeptId()).append(", subDeptName: ")
				.append(getSubDeptName()).append("deptServices: ")
				.append(getDeptServices()).append("]").toString();
	}

}
