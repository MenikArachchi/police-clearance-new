package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

public class UserRoleDisplayVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//PHQ(1), POL(2), CID(3), TID(4), SIS(5), NIC(6), IMI(7);
	private int phqUserRole;
	private int polUserRole;
	private int cidUserRole;
	private int tidUserRole;
	private int sisUserRole;
	private int nicUserRole;
	private int imiUserRole;

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("phqUserRole", phqUserRole);
		map.put("polUserRole", polUserRole);
		map.put("cidUserRole", cidUserRole);
		map.put("tidUserRole", tidUserRole);
		map.put("sisUserRole", sisUserRole);
		map.put("nicUserRole", nicUserRole);
		map.put("imiUserRole", imiUserRole);
		return map;
	}
	
	
	
	public UserRoleDisplayVO() {
		super();
		this.phqUserRole=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		this.polUserRole=PoliceEnumConstant.UserDepartment.POL.getCode();
		this.cidUserRole=PoliceEnumConstant.UserDepartment.CID.getCode();
		this.tidUserRole=PoliceEnumConstant.UserDepartment.TID.getCode();
		this.sisUserRole=PoliceEnumConstant.UserDepartment.SIS.getCode();
		this.nicUserRole=PoliceEnumConstant.UserDepartment.NIC.getCode();
		this.imiUserRole=PoliceEnumConstant.UserDepartment.IMI.getCode();
	}

	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public int getPhqUserRole() {
		return phqUserRole;
	}

	public void setPhqUserRole(int phqUserRole) {
		this.phqUserRole = phqUserRole;
	}

	public int getPolUserRole() {
		return polUserRole;
	}

	public void setPolUserRole(int polUserRole) {
		this.polUserRole = polUserRole;
	}

	public int getCidUserRole() {
		return cidUserRole;
	}

	public void setCidUserRole(int cidUserRole) {
		this.cidUserRole = cidUserRole;
	}

	public int getTidUserRole() {
		return tidUserRole;
	}

	public void setTidUserRole(int tidUserRole) {
		this.tidUserRole = tidUserRole;
	}

	public int getSisUserRole() {
		return sisUserRole;
	}

	public void setSisUserRole(int sisUserRole) {
		this.sisUserRole = sisUserRole;
	}

	public int getNicUserRole() {
		return nicUserRole;
	}

	public void setNicUserRole(int nicUserRole) {
		this.nicUserRole = nicUserRole;
	}

	public int getImiUserRole() {
		return imiUserRole;
	}

	public void setImiUserRole(int imiUserRole) {
		this.imiUserRole = imiUserRole;
	}

	
}
