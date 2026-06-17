package lk.icta.police.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.CertificateIssuanceBusiness;
import lk.icta.police.business.MasterFileBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.CertificateAuthPersonVO;
import lk.icta.police.framework.vo.CommissionerVO;
import lk.icta.police.framework.vo.CountryVO;
import lk.icta.police.util.RadioObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class MasterFileAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MasterFileAction.class);
	private Map<String,Object> session;
	
	private List<RadioObject> masterFileTypeList;
	private int masterFileType;
	private int showHideFlag;
	
	//For Certificate Signing
	private String curAuthPerson;
	private Date activeDate;
	private String curAuthPersonDesignation;
	private String curAuthPersonAddress;
	
	private String newAuthPerson;
	private Date effectiveDate;
	private long curAuthPersonId;
	private String designation;
	private String authPersonAddress;
	
	//For High commissions
	private List<CountryVO> countryList;
	private long countryId;
	private String hcType;
	private String nameOfAuthority;
	private String address;
	private Map<String,PoliceEnumConstant.HighcommissionTypes> highcommissionTypesMap;
	private List<RadioObject> highcommissionTypeList;
	private String highcommissionType;
	private String addressee;
	
	//For Search Highcommissioner by country
	private long searchCountry;
	private List<CommissionerVO> commissionerVOs;
	
	//Edit High commission
	private long hcId;
	
	public String populateMasterFiles(){
		populateMasterData();
		setShowHideFlag(1);
		return SUCCESS;
	}
	
	public String saveCertificateSigning() {
		
		LOGGER.info("newAuthPerson   -> " + newAuthPerson);
		LOGGER.info("effectiveDate   -> " + effectiveDate);
		LOGGER.info("curAuthPersonId -> " + curAuthPersonId);
		LOGGER.info("designation -> " + designation);
		LOGGER.info("authPersonAddress -> " + authPersonAddress);
		
		CertificateAuthPersonVO certificateAuthPersonVO = new CertificateAuthPersonVO();
		certificateAuthPersonVO.setName(newAuthPerson);
		certificateAuthPersonVO.setEffectiveStartDate(effectiveDate);
		certificateAuthPersonVO.setDesignation(designation);
		certificateAuthPersonVO.setAddress(authPersonAddress);
		
		long authPersonId = MasterFileBusiness.getInstance().saveCertificateAuthPerson(certificateAuthPersonVO);
		if(authPersonId > 0L){
			addActionMessage("New Authorized person successfully added to the database.");
			//the day before the effective date. may be i need to change the method
			Calendar cal = Calendar.getInstance();
			cal.setTime(effectiveDate);
			cal.add(Calendar.DAY_OF_YEAR,-1);
			Date oneDayBeforeEffectiveDate= cal.getTime();
			MasterFileBusiness.getInstance().updateCertificateAuthPerson(curAuthPersonId, oneDayBeforeEffectiveDate);
		} else {
			addActionError("Unable to add the new authorized person to database.");
			populateMasterData();
			setShowHideFlag(2);
			return ERROR;
		}
		populateMasterData();
		setShowHideFlag(2);
		return SUCCESS;
	}
	
	public String saveHighCommissions() {
		LOGGER.info("ADD HC");
		LOGGER.info("hc id                -> " + hcId);
		LOGGER.info("countryId            -> " + countryId);
		LOGGER.info("highcommissionType   -> " + hcType);
		LOGGER.info("nameOfAuthority      -> " + nameOfAuthority);
		LOGGER.info("address              -> " + address);
		LOGGER.info("addressee            -> " + addressee);
		
		CommissionerVO commissionerVO = new CommissionerVO();
		commissionerVO.setCommissionEmbassyConsultantName(nameOfAuthority);
		commissionerVO.setCommissionEmbassyConsultantType(hcType);
		commissionerVO.setCommissionEmbassyConsultantAddress(address);
		commissionerVO.setActiveStatus(1);
		commissionerVO.setCountryId(countryId);
		commissionerVO.setAddressee(addressee);
		
		//Edit mode - hcId is zero
		if(hcId == 0) {
			int isExistsCommissioner = MasterFileBusiness.getInstance().isExistsCommissioner(commissionerVO);
			
			if(isExistsCommissioner == 0) {
				long commissionerId = MasterFileBusiness.getInstance().saveCommissioner(commissionerVO);
				
				if(commissionerId > 0L){
					addActionMessage("New High commissions successfully added to the database.");
				} else {
					addActionError("Unable to add the new High commissions to database.");
					populateMasterData();
					setShowHideFlag(3);
					return ERROR;
				}
			} else {
				addActionError("Record already exists");
				populateMasterData();
				setShowHideFlag(3);
				return ERROR;
			}
			
		} else {
			addActionError("Please click on modify button to modify the record");
			populateMasterData();
			setShowHideFlag(3);
			return ERROR;
		}

		populateMasterData();
		setShowHideFlag(3);
		return SUCCESS;
	}
	
	private String populateMasterData() {
		masterFileTypeList = new ArrayList<RadioObject>();
		masterFileTypeList.add(new RadioObject("1", "Certificate Signing"));
		masterFileTypeList.add(new RadioObject("2", "High Commissions"));
		
		countryList = ApplicationBusiness.getInstance().getCountryList();
		
		highcommissionTypesMap = PoliceEnumConstant.HighcommissionTypes.getHighcommissionTypesMap();
		highcommissionTypeList = new ArrayList<RadioObject>();
		for (Map.Entry<String,PoliceEnumConstant.HighcommissionTypes> entry : highcommissionTypesMap.entrySet()) {
		    highcommissionTypeList.add(new RadioObject(entry.getValue().toString(), entry.getKey()));
		}
		
		CertificateAuthPersonVO certificateAuthPersonVO = CertificateIssuanceBusiness.getInstance().getCurrentCertificateAuthPerson();
		if(certificateAuthPersonVO != null) {
			setCurAuthPerson(certificateAuthPersonVO.getName());
			setActiveDate(certificateAuthPersonVO.getEffectiveStartDate());
			setCurAuthPersonId(certificateAuthPersonVO.getId());
			setCurAuthPersonAddress(certificateAuthPersonVO.getAddress());
			setCurAuthPersonDesignation(certificateAuthPersonVO.getDesignation());
		}
		
		return SUCCESS;
	}
	
	public String loadAvailableHighCommissionerList() {
		
		LOGGER.info("SEARCH COUNTRY ID -> " + searchCountry);
		
		commissionerVOs = MasterFileBusiness.getInstance().getCommissionerListByCountry(searchCountry);
		
		if(commissionerVOs != null){
			LOGGER.info("COMMISSIONER LIST SIZZZE -> " + commissionerVOs.size());
			for (CommissionerVO commissionerVO : commissionerVOs) {
				commissionerVO.setCommissionEmbassyConsultantType(PoliceEnumConstant.HighcommissionTypes
						.fromCode(commissionerVO.getCommissionEmbassyConsultantType()).getDisplayName());
			}
		}
		return SUCCESS;
	}
	
	public String updateHighCommissions() {
		
		LOGGER.info("UPDATE HC");
		LOGGER.info("hc id                -> " + hcId);
		LOGGER.info("countryId            -> " + countryId);
		LOGGER.info("highcommissionType   -> " + hcType);
		LOGGER.info("nameOfAuthority      -> " + nameOfAuthority);
		LOGGER.info("address              -> " + address);
		LOGGER.info("addressee            -> " + addressee);

		CommissionerVO commissionerVODB = ApplicationBusiness.getInstance().getCommissionerVOById(hcId);
		
		if(commissionerVODB != null) {
			CommissionerVO commissionerVO = new CommissionerVO();
			commissionerVO.setCommissionEmbassyConsultantName(nameOfAuthority);
			commissionerVO.setCommissionEmbassyConsultantType(hcType);
			commissionerVO.setCommissionEmbassyConsultantAddress(address);
			commissionerVO.setActiveStatus(1);
			commissionerVO.setCountryId(countryId);
			commissionerVO.setId(hcId);
			commissionerVO.setAddressee(addressee);
			String status = MasterFileBusiness.getInstance().updateCommissioner(commissionerVO);
			
			if(status.equals(PoliceConstant.SUCCESS)){
				addActionMessage("High commissions successfully updated.");
			} else {
				addActionError("Unable to update the High commissions to database.");
				populateMasterData();
				setShowHideFlag(4);
				return ERROR;
			}
		} else {
			addActionError("No Records Found");
			populateMasterData();
			setShowHideFlag(4);
			return ERROR;
		}

		populateMasterData();
		setShowHideFlag(4);
		return SUCCESS;
	}
	
	public String deleteHighCommissionsForm() {
		LOGGER.info("DELETE HC");
		LOGGER.info("hc id                -> " + hcId);
		
		int hcCount = MasterFileBusiness.getInstance().isExistsCommissionerApplication(hcId);
		
		if(hcCount == 0) {
			try {
				boolean status = MasterFileBusiness.getInstance().deleteCommissionerById(hcId);
				
				if(status){
					addActionMessage("Successfully Deleted");
				} else {
					addActionError("Unable to delete record.");
					populateMasterData();
					setShowHideFlag(5);
					return ERROR;
				}
			} catch (Exception e) {
				e.printStackTrace();
				addActionError("Unable to delete record.");
				populateMasterData();
				setShowHideFlag(5);
				return ERROR;
			}
			
		}else {
			addActionError("Unable to delete record.");
			populateMasterData();
			setShowHideFlag(5);
			return ERROR;
		}
		populateMasterData();
		setShowHideFlag(5);
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}

	public List<RadioObject> getMasterFileTypeList() {
		return masterFileTypeList;
	}

	public void setMasterFileTypeList(List<RadioObject> masterFileTypeList) {
		this.masterFileTypeList = masterFileTypeList;
	}

	public int getMasterFileType() {
		return masterFileType;
	}

	public void setMasterFileType(int masterFileType) {
		this.masterFileType = masterFileType;
	}

	public int getShowHideFlag() {
		return showHideFlag;
	}

	public void setShowHideFlag(int showHideFlag) {
		this.showHideFlag = showHideFlag;
	}

	public String getCurAuthPerson() {
		return curAuthPerson;
	}

	public void setCurAuthPerson(String curAuthPerson) {
		this.curAuthPerson = curAuthPerson;
	}

	public Date getActiveDate() {
		return activeDate;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public String getNewAuthPerson() {
		return newAuthPerson;
	}

	public void setNewAuthPerson(String newAuthPerson) {
		this.newAuthPerson = newAuthPerson;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public List<CountryVO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}

	public String getNameOfAuthority() {
		return nameOfAuthority;
	}

	public void setNameOfAuthority(String nameOfAuthority) {
		this.nameOfAuthority = nameOfAuthority;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, PoliceEnumConstant.HighcommissionTypes> getHighcommissionTypesMap() {
		return highcommissionTypesMap;
	}

	public void setHighcommissionTypesMap(
			Map<String, PoliceEnumConstant.HighcommissionTypes> highcommissionTypesMap) {
		this.highcommissionTypesMap = highcommissionTypesMap;
	}

	public List<RadioObject> getHighcommissionTypeList() {
		return highcommissionTypeList;
	}

	public void setHighcommissionTypeList(List<RadioObject> highcommissionTypeList) {
		this.highcommissionTypeList = highcommissionTypeList;
	}

	public long getCurAuthPersonId() {
		return curAuthPersonId;
	}

	public void setCurAuthPersonId(long curAuthPersonId) {
		this.curAuthPersonId = curAuthPersonId;
	}

	public String getHcType() {
		return hcType;
	}

	public void setHcType(String hcType) {
		this.hcType = hcType;
	}

	public String getHighcommissionType() {
		return highcommissionType;
	}

	public void setHighcommissionType(String highcommissionType) {
		this.highcommissionType = highcommissionType;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public long getSearchCountry() {
		return searchCountry;
	}

	public void setSearchCountry(long searchCountry) {
		this.searchCountry = searchCountry;
	}

	public List<CommissionerVO> getCommissionerVOs() {
		return commissionerVOs;
	}

	public void setCommissionerVOs(List<CommissionerVO> commissionerVOs) {
		this.commissionerVOs = commissionerVOs;
	}

	public long getHcId() {
		return hcId;
	}

	public void setHcId(long hcId) {
		this.hcId = hcId;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAuthPersonAddress() {
		return authPersonAddress;
	}

	public void setAuthPersonAddress(String authPersonAddress) {
		this.authPersonAddress = authPersonAddress;
	}

	public String getCurAuthPersonDesignation() {
		return curAuthPersonDesignation;
	}

	public void setCurAuthPersonDesignation(String curAuthPersonDesignation) {
		this.curAuthPersonDesignation = curAuthPersonDesignation;
	}

	public String getCurAuthPersonAddress() {
		return curAuthPersonAddress;
	}

	public void setCurAuthPersonAddress(String curAuthPersonAddress) {
		this.curAuthPersonAddress = curAuthPersonAddress;
	}
	
	

}
