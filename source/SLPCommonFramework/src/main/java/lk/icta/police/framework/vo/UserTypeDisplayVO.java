package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;

public class UserTypeDisplayVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	'1', 'Super User'
//	'2', 'Department Admin'
//	'3', 'Department User'
//	'4', 'Checking Officer no Adverse'
//	'5', 'Checking Officer Adverse'
//	'6', 'OIC'
//	'7', 'ASP'
//	'8', 'DHA'
//	'9', 'DIG'
//	'10', 'Posting Officer'
	
//	SA(1), DA(2), DU(3), CN(4), CA(5), OI(6), AS(7), DH(8), DI(9), PO(10);
	
	private int superUser;
	private int departmentAdmin;
	private int departmentUser;
	private int checkingOfficerNoAdverse;
	private int checkingOfficerAdverse;	
	private int oicUser;
	private int aspUser;
	private int dhaUser;
	private int digUser;
	private int postingOfficer;
	
	
//	'3', 'CID'
//	'7', 'Immigration Department'
//	'6', 'NIC'
//	'1', 'Police Head Quarter'
//	'2', 'Police Station'
//	'5', 'SIS'
//	'4', 'TID'
	
	private int cidUser;
	private int imiUser;
	private int nicUser;
	private int phqUser;
	private int polUser;
	private int sisUser;
	private int tidUser;
	

	public Map<String,Object> toBasicMap(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("superUser", superUser);
		map.put("departmentAdmin", departmentAdmin);
		map.put("departmentUser", departmentUser);
		map.put("checkingOfficerNoAdverse", checkingOfficerNoAdverse);
		map.put("checkingOfficerAdverse", checkingOfficerAdverse);
		map.put("oicUser", oicUser);
		map.put("aspUser", aspUser);
		map.put("dhaUser", dhaUser);
		map.put("digUser", digUser);
		map.put("postingOfficer", postingOfficer);
		
		
		map.put("cidUser", cidUser);
		map.put("imiUser", imiUser);
		map.put("nicUser", nicUser);
		map.put("phqUser", phqUser);
		map.put("polUser", polUser);
		map.put("sisUser", sisUser);
		map.put("tidUser", tidUser);
		return map;
	}
	
	
	
	public UserTypeDisplayVO() {
		super();
	    this.superUser=PoliceEnumConstant.UserType.SA.getCode();
		this.departmentAdmin=PoliceEnumConstant.UserType.DA.getCode();
		this.departmentUser=PoliceEnumConstant.UserType.DU.getCode();
		this.checkingOfficerNoAdverse=PoliceEnumConstant.UserType.CN.getCode();
		this.checkingOfficerAdverse=PoliceEnumConstant.UserType.CA.getCode();
		this.oicUser=PoliceEnumConstant.UserType.OI.getCode();
		this.aspUser=PoliceEnumConstant.UserType.AS.getCode();
		this.dhaUser=PoliceEnumConstant.UserType.DH.getCode();
		this.digUser=PoliceEnumConstant.UserType.DI.getCode();
		this.postingOfficer=PoliceEnumConstant.UserType.PO.getCode();
		
		this.cidUser=PoliceEnumConstant.UserDepartment.CID.getCode();
		this.imiUser=PoliceEnumConstant.UserDepartment.IMI.getCode();
		this.nicUser=PoliceEnumConstant.UserDepartment.NIC.getCode();
		this.phqUser=PoliceEnumConstant.UserDepartment.PHQ.getCode();
		this.polUser=PoliceEnumConstant.UserDepartment.POL.getCode();
		this.sisUser=PoliceEnumConstant.UserDepartment.SIS.getCode();
		this.tidUser=PoliceEnumConstant.UserDepartment.TID.getCode();
	}

	@Override
	public String toString() {
		return this.toBasicMap().toString();
	}

	public int getSuperUser() {
		return superUser;
	}
	public void setSuperUser(int superUser) {
		this.superUser = superUser;
	}

	public int getDepartmentAdmin() {
		return departmentAdmin;
	}
	public void setDepartmentAdmin(int departmentAdmin) {
		this.departmentAdmin = departmentAdmin;
	}

	public int getDepartmentUser() {
		return departmentUser;
	}
	public void setDepartmentUser(int departmentUser) {
		this.departmentUser = departmentUser;
	}

	public int getOicUser() {
		return oicUser;
	}
	public void setOicUser(int oicUser) {
		this.oicUser = oicUser;
	}

	public int getAspUser() {
		return aspUser;
	}
	public void setAspUser(int aspUser) {
		this.aspUser = aspUser;
	}

	public int getDhaUser() {
		return dhaUser;
	}
	public void setDhaUser(int dhaUser) {
		this.dhaUser = dhaUser;
	}

	public int getPostingOfficer() {
		return postingOfficer;
	}
	public void setPostingOfficer(int postingOfficer) {
		this.postingOfficer = postingOfficer;
	}

	public int getCheckingOfficerNoAdverse() {
		return checkingOfficerNoAdverse;
	}
	public void setCheckingOfficerNoAdverse(int checkingOfficerNoAdverse) {
		this.checkingOfficerNoAdverse = checkingOfficerNoAdverse;
	}

	public int getCheckingOfficerAdverse() {
		return checkingOfficerAdverse;
	}
	public void setCheckingOfficerAdverse(int checkingOfficerAdverse) {
		this.checkingOfficerAdverse = checkingOfficerAdverse;
	}

	public int getDigUser() {
		return digUser;
	}
	public void setDigUser(int digUser) {
		this.digUser = digUser;
	}



	public int getCidUser() {
		return cidUser;
	}



	public void setCidUser(int cidUser) {
		this.cidUser = cidUser;
	}



	public int getImiUser() {
		return imiUser;
	}



	public void setImiUser(int imiUser) {
		this.imiUser = imiUser;
	}



	public int getNicUser() {
		return nicUser;
	}



	public void setNicUser(int nicUser) {
		this.nicUser = nicUser;
	}



	public int getPhqUser() {
		return phqUser;
	}



	public void setPhqUser(int phqUser) {
		this.phqUser = phqUser;
	}



	public int getPolUser() {
		return polUser;
	}



	public void setPolUser(int polUser) {
		this.polUser = polUser;
	}



	public int getSisUser() {
		return sisUser;
	}



	public void setSisUser(int sisUser) {
		this.sisUser = sisUser;
	}



	public int getTidUser() {
		return tidUser;
	}



	public void setTidUser(int tidUser) {
		this.tidUser = tidUser;
	}

	

	
}
