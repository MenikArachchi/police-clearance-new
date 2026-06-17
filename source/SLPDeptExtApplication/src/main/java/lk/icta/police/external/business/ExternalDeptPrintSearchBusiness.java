package lk.icta.police.external.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.ApprovalStatus;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.CertificateIssuanceDAO;
import lk.icta.police.framework.dao.ExternalDeptPrintSearchDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.AddressTempVO;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationClearanceDate;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ChangeAuditVO;
import lk.icta.police.framework.vo.ExternalDeptPrintSearchCriteriaVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ExternalDeptPrintSearchBusiness {
	
	private static final Logger LOGGER = Logger.getLogger(ExternalDeptPrintSearchBusiness.class);
	private static ExternalDeptPrintSearchBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static ExternalDeptPrintSearchBusiness getInstance() {
		synchronized (ExternalDeptPrintSearchBusiness.class) {
			if (instance == null) {
				instance = new ExternalDeptPrintSearchBusiness();
			}
			return instance;
		}
	}
	
    private ExternalDeptPrintSearchBusiness() {
		
	}
    
    
    public List<ApplicationVO> searchApplication(ExternalDeptPrintSearchCriteriaVO searchCriteriaVO, int userRole, long location, int userId) {
		Connection con = null;
		List<ApplicationVO> applicationVOs=new ArrayList<ApplicationVO>();
		List<ApplicationVO> returnedApplicationVOs=new ArrayList<ApplicationVO>();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			if(!(searchCriteriaVO==null)){
				searchCriteriaVO.setLocationId(location);
							
				LOGGER.info("userRole :" + userRole);
				if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
					LOGGER.info("userRole POL OR PHQ:");
					applicationVOs = ExternalDeptPrintSearchDAO.getInstance().searchApplicationForExternalReviewPolice(searchCriteriaVO,userRole, con);
								
				}else{
					LOGGER.info("userRole NOT POL OR PHQ:");
					applicationVOs = ExternalDeptPrintSearchDAO.getInstance().searchApplicationForExternalReview(searchCriteriaVO, con);	
				}
				
				LOGGER.info("returnedApplicationVOs EXT :" + applicationVOs);
				
				boolean doAddApplication=false;
				
				if(!(applicationVOs==null || applicationVOs.isEmpty())){
					List<AddressVO> addressVOs=null;
					for (ApplicationVO applicationVO : applicationVOs) {
						
						applicationVO.constructcertificatepostalAddress();	
						applicationVO.allocateFileTypes();
						
						List<ApplicationClearanceDate> clearanceDatesByApplicationId=ApplicationDAO.getInstance().listAllApplicationClearanceDatesByApplicationId(applicationVO.getApplicationId(), con);
						List<ApplicationClearanceDate> clearanceDatesByAddressId=null;
						
						
						PoliceEnumConstant.UserDepartment userRoleEnum=PoliceEnumConstant.UserDepartment.fromCode(userRole);		
						
						PoliceEnumConstant.ApprovalStatus approvalStatus=null;
						String appStatus=null;
						
						
						switch (userRoleEnum) {
						case CID:
							if(!(StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
								if(!(StringUtils.equals(applicationVO.getCidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
									doAddApplication=true;
								}
							}else{
								doAddApplication=true;
							}
							
							if(doAddApplication){
								appStatus=applicationVO.getCidStatus();
								if(StringUtils.isNotEmpty(appStatus)){
									approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
								}
								if(approvalStatus==null){
									approvalStatus=ApprovalStatus.PN;
								}
								applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
								
								if(!(clearanceDatesByApplicationId==null || clearanceDatesByApplicationId.isEmpty())){
									for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByApplicationId) {
										if(StringUtils.equals(applicationClearanceDate.getDepartment(), PoliceEnumConstant.UserDepartment.CID.toString())){
											
											if(!(applicationClearanceDate.getSentDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
													applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
												}else{
													applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()));
												}
											}
											
											if(!(applicationClearanceDate.getResponsedDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
													applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
												}else{
													applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification() + ", " + format.format(applicationClearanceDate.getResponsedDate()));
												}
											}
											
											setExternalApprovedUser(applicationClearanceDate,applicationVO);
											setInternalSentUser(applicationClearanceDate, applicationVO);
											
											applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
										}
									}
								}
								returnedApplicationVOs.add(applicationVO);
							}
							
							
							break;
						case TID:
							if(!(StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
								if(!(StringUtils.equals(applicationVO.getTidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
									doAddApplication=true;
								}
							}else{
								doAddApplication=true;
							}
							
							
							if(doAddApplication){
								appStatus=applicationVO.getTidStatus();
								if(StringUtils.isNotEmpty(appStatus)){
									approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
								}
								if(approvalStatus==null){
									approvalStatus=ApprovalStatus.PN;
								}
								applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
								
								if(!(clearanceDatesByApplicationId==null || clearanceDatesByApplicationId.isEmpty())){
									for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByApplicationId) {
										if(StringUtils.equals(applicationClearanceDate.getDepartment(), PoliceEnumConstant.UserDepartment.TID.toString())){
											
											if(!(applicationClearanceDate.getSentDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
													applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
												}else{
													applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()));
												}
											}
											
											if(!(applicationClearanceDate.getResponsedDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
													applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
												}else{
													applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification() + ", " + format.format(applicationClearanceDate.getResponsedDate()));
												}
											}
											
											setExternalApprovedUser(applicationClearanceDate, applicationVO);
											setInternalSentUser(applicationClearanceDate, applicationVO);
											
											applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
										}
										
									}
								}
								returnedApplicationVOs.add(applicationVO);
							}
							
							break;
						case SIS:
							if(!(StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
								if(!(StringUtils.equals(applicationVO.getSisStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
									doAddApplication=true;
								}
							}else{
								doAddApplication=true;
							}
							
							if(doAddApplication){
								appStatus=applicationVO.getSisStatus();
								if(StringUtils.isNotEmpty(appStatus)){
									approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
								}
								if(approvalStatus==null){
									approvalStatus=ApprovalStatus.PN;
								}
								applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
								
								if(!(clearanceDatesByApplicationId==null || clearanceDatesByApplicationId.isEmpty())){
									int counter=0;
									for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByApplicationId) {
										if(StringUtils.equals(applicationClearanceDate.getDepartment(), PoliceEnumConstant.UserDepartment.SIS.toString())){
											
											if(!(applicationClearanceDate.getSentDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
													applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
												}else{
													applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()));
												}
											}
											
											if(!(applicationClearanceDate.getResponsedDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
													applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
												}else{
													applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification() + ", " + format.format(applicationClearanceDate.getResponsedDate()));
												}
											}
											
											setExternalApprovedUser(applicationClearanceDate, applicationVO);
											setInternalSentUser(applicationClearanceDate, applicationVO);
											
											applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
										}
										
									}
								}
								returnedApplicationVOs.add(applicationVO);
							}
							
							break;
						case NIC:
							
							if(!(StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
								if(!(StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
									doAddApplication=true;
								}
							}else{
								doAddApplication=true;
							}
							
							if(doAddApplication){
								appStatus=applicationVO.getNicStatus();
								if(StringUtils.isNotEmpty(appStatus)){
									approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
								}
								if(approvalStatus==null){
									approvalStatus=ApprovalStatus.PN;
								}
								applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
								
								if(!(clearanceDatesByApplicationId==null || clearanceDatesByApplicationId.isEmpty())){
									for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByApplicationId) {
										if(StringUtils.equals(applicationClearanceDate.getDepartment(), PoliceEnumConstant.UserDepartment.NIC.toString())){
											
											if(!(applicationClearanceDate.getSentDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
													applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
												}else{
													applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()));
												}
											}
											
											if(!(applicationClearanceDate.getResponsedDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
													applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
												}else{
													applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification() + ", " + format.format(applicationClearanceDate.getResponsedDate()));
												}
											}
											
											setExternalApprovedUser(applicationClearanceDate, applicationVO);
											setInternalSentUser(applicationClearanceDate, applicationVO);
											
											applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
										}
										
									}
								}
								returnedApplicationVOs.add(applicationVO);
							}
							
							break;
						case IMI:
							if(!(StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
								if(!(StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
									doAddApplication=true;
								}
							}else{
								doAddApplication=true;
							}
							
							if(doAddApplication){
								appStatus=applicationVO.getImiStatus();
								if(StringUtils.isNotEmpty(appStatus)){
									approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
								}
								if(approvalStatus==null){
									approvalStatus=ApprovalStatus.PN;
								}
								applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
								
								if(!(clearanceDatesByApplicationId==null || clearanceDatesByApplicationId.isEmpty())){
									for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByApplicationId) {
										if(StringUtils.equals(applicationClearanceDate.getDepartment(), PoliceEnumConstant.UserDepartment.IMI.toString())){
											
											if(!(applicationClearanceDate.getSentDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
													applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
												}else{
													applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()));
												}
											}
											
											if(!(applicationClearanceDate.getResponsedDate()==null)){
												if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
													applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
												}else{
													applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification() + ", " + format.format(applicationClearanceDate.getResponsedDate()));
												}
											}
											
											setExternalApprovedUser(applicationClearanceDate, applicationVO);
											setInternalSentUser(applicationClearanceDate, applicationVO);
											
											applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
										}
									}
								}
								returnedApplicationVOs.add(applicationVO);
							}
							
							break;
						case PHQ:
							//check for lock status in address
							addressVOs=AddressDAO.getInstance().getAddressListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.AddressType.AC.toString(), con);
							if(!(addressVOs==null || addressVOs.isEmpty())){
								for (AddressVO addressVO : addressVOs) {
									doAddApplication=false;
									
									if((StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
										if((StringUtils.equals(addressVO.getPoliceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils.isEmpty(addressVO.getPoliceStatus()))){
											doAddApplication=true;
										}
										
									}else{
										if(!(StringUtils.equals(addressVO.getPoliceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils.isEmpty(addressVO.getPoliceStatus()))){
											doAddApplication=true;
										}
									}
									
									if(doAddApplication){
										applicationVO=new ApplicationVO(applicationVO);
										
										applicationVO.setPrintAddressVO(addressVO);
										applicationVO.setPrintAddressId(addressVO.getAddressId());
										applicationVO.setCertificatePostalAddress(addressVO.getAddress());
										AddressTempVO addressTempVO=AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), con);
										if(!(addressTempVO==null)){
											applicationVO.setHasRequestClarification(1);
											applicationVO.setPrintAddressTempVO(addressTempVO);
										}
										
										appStatus=addressVO.getPoliceStatus();
										if(StringUtils.isNotEmpty(appStatus)){
											approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
										}
										if(approvalStatus==null){
											approvalStatus=ApprovalStatus.PN;
										}
										applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
										
										clearanceDatesByAddressId=ApplicationDAO.getInstance().listAllApplicationClearanceDatesByApplicationId(applicationVO.getApplicationId(), con);
										if(!(clearanceDatesByAddressId==null || clearanceDatesByAddressId.isEmpty())){
											for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByAddressId) {
												if(addressVO.getAddressId()==applicationClearanceDate.getAddressId()){
													if(!(applicationClearanceDate.getSentDate()==null)){
														if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
															applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
														}else{
															applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()) );
														}
													}
													
													if(!(applicationClearanceDate.getResponsedDate()==null)){
														if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
															applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
														}else{
															applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification()+ ", " + format.format(applicationClearanceDate.getResponsedDate()));
														}
													}
													
													setExternalApprovedUser(applicationClearanceDate, applicationVO);
													setInternalSentUser(applicationClearanceDate, applicationVO);
													
													applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
												}
											}
										}
										returnedApplicationVOs.add(applicationVO);
									}
								}	
							}							
							break;
						case POL:
							//check for lock status in address
							addressVOs=AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.AddressType.AC.toString(), location, con);
							
							if(!(addressVOs==null || addressVOs.isEmpty())){
								for (AddressVO addressVO : addressVOs) {
									
									doAddApplication=false;
									
									if(!(StringUtils.equals(searchCriteriaVO.getClearanceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))){
										if(!(StringUtils.equals(addressVO.getPoliceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils.isEmpty(addressVO.getPoliceStatus()))){
											doAddApplication=true;
										}
									}else{
										if((StringUtils.equals(addressVO.getPoliceStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils.isEmpty(addressVO.getPoliceStatus()))){
											doAddApplication=true;
										}
									}
									
									if(doAddApplication){
										applicationVO=new ApplicationVO(applicationVO);
										applicationVO.setCertificatePostalAddress(addressVO.getAddress());
										applicationVO.setPrintAddressVO(addressVO);
										applicationVO.setPrintAddressId(addressVO.getAddressId());
										AddressTempVO addressTempVO=AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), con);
										if(!(addressTempVO==null)){
											applicationVO.setHasRequestClarification(1);
											applicationVO.setPrintAddressTempVO(addressTempVO);
										}
										
										appStatus=addressVO.getPoliceStatus();
										if(StringUtils.isNotEmpty(appStatus)){
											approvalStatus=PoliceEnumConstant.ApprovalStatus.fromCode(appStatus);
										}
										if(approvalStatus==null){
											approvalStatus=ApprovalStatus.PN;
										}
										applicationVO.setExternalApprovalStatus(approvalStatus.getDisplayName());
										
										clearanceDatesByAddressId=ApplicationDAO.getInstance().listAllApplicationClearanceDatesByApplicationId(applicationVO.getApplicationId(), con);
										if(!(clearanceDatesByAddressId==null || clearanceDatesByAddressId.isEmpty())){
											for (ApplicationClearanceDate applicationClearanceDate : clearanceDatesByAddressId) {
												if(addressVO.getAddressId()==applicationClearanceDate.getAddressId()){
													if(!(applicationClearanceDate.getSentDate()==null)){
														if(StringUtils.isEmpty(applicationVO.getDateSentForClarification())){
															applicationVO.setDateSentForClarification(format.format(applicationClearanceDate.getSentDate()));
														}else{
															applicationVO.setDateSentForClarification(applicationVO.getDateSentForClarification() + ", " + format.format(applicationClearanceDate.getSentDate()));
														}
													}
													
													if(!(applicationClearanceDate.getResponsedDate()==null)){
														if(StringUtils.isEmpty(applicationVO.getDateRecievedClarification())){
															applicationVO.setDateRecievedClarification(format.format(applicationClearanceDate.getResponsedDate()));
														}else{
															applicationVO.setDateRecievedClarification(applicationVO.getDateRecievedClarification() + ", " + format.format(applicationClearanceDate.getResponsedDate()) );
														}
													}
													
													setExternalApprovedUser(applicationClearanceDate, applicationVO);
													setInternalSentUser(applicationClearanceDate, applicationVO);
													
													applicationVO.setHasAlreadyPrinted(applicationClearanceDate.getPrintedStatus());
												}
											}
										}
										
										returnedApplicationVOs.add(applicationVO);
									}
									
									
								}
							}	
							break;
						default:
							LOGGER.info("::: CAME TO DEFAULT ::: THIS MUST BE A NEW USER ROLE :::");
							break;
						}
						
					}
				}
				
			}			
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		return returnedApplicationVOs;
	}
    
    
   

	private void setInternalSentUser(ApplicationClearanceDate applicationClearanceDate,	ApplicationVO applicationVO) throws lk.icta.commonuser.framework.exception.BusinessException {
		int clearedUser=0;
		UserVO userVO=null;
		UserVO tempUserVO=null;
		
		clearedUser=applicationClearanceDate.getSentUserId();
		if(!(clearedUser==0)){
			tempUserVO=new UserVO();
			tempUserVO.setId(clearedUser);
			userVO=CommonUserBusiness.getInstance().getUserDetails(tempUserVO);
			if(!(userVO==null)){
				applicationVO.setInternalSentUser(userVO.getFullName());
			}
		}		
	}

	private void setExternalApprovedUser(ApplicationClearanceDate applicationClearanceDate,	ApplicationVO applicationVO) throws lk.icta.commonuser.framework.exception.BusinessException {
		int clearedUser=0;
		UserVO userVO=null;
		UserVO tempUserVO=null;
		
		clearedUser=applicationClearanceDate.getResponsedUserId();
		if(!(clearedUser==0)){
			tempUserVO=new UserVO();
			tempUserVO.setId(clearedUser);
			userVO=CommonUserBusiness.getInstance().getUserDetails(tempUserVO);
			if(!(userVO==null)){
				applicationVO.setExternalApprovedUser(userVO.getFullName());
			}
		}		
	}

//	public List<ApplicationVO> getApplicationListByIds(List<Long> applicationIdList,int userRole, long location, int userId){
//    	Connection con = null;
//    	List<ApplicationVO> returnedApplicationVOs=new ArrayList<ApplicationVO>();
//    	List<ApplicationVO> applicationVOs=new ArrayList<ApplicationVO>();
//    	
//    	try {
//			con = DatabaseManager.getPOLDBConnection();
//			con.setAutoCommit(false);
//	    	
//	    	applicationVOs=ExternalDeptPrintSearchDAO.getInstance().getApplicationListByIds(applicationIdList,con);
//	    	
//	    	for (ApplicationVO applicationVO : applicationVOs) {
//	    		if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){    		
//	        		List<AddressVO> addressVOs=AddressDAO.getInstance().getPendingAddressListByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.AddressType.AC.toString(), con);
//	        		
//	        		for (AddressVO addressVO : addressVOs) {			
//	    				if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode()){
//	    	    			if(addressVO.getPoliceAreaId()==location){
//	    	    				applicationVO.setPrintAddressVO(addressVO);
//	    	    				AddressTempVO addressTempVO=AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), con);
//	    	    				if(!(addressTempVO==null)){
//	    	    					applicationVO.setHasRequestClarification(1);
//	    	    					applicationVO.setPrintAddressTempVO(addressTempVO);
//	    	    				}
//	    	    				returnedApplicationVOs.add(applicationVO);
//	    	    			}	    						
//	    	    		}else if(userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
//	    	    			applicationVO.setPrintAddressVO(addressVO);
//	    					AddressTempVO addressTempVO=AddressDAO.getInstance().getActiveAddressTempByAddressId(addressVO.getAddressId(), con);
//	    					if(!(addressTempVO==null)){
//	    						applicationVO.setHasRequestClarification(1);
//	    						applicationVO.setPrintAddressTempVO(addressTempVO);
//	    					}
//	    					returnedApplicationVOs.add(applicationVO);
//	    	    		}				
//	    			}						
//	    		}else{
//	    			returnedApplicationVOs.add(applicationVO);
//	    		}
//			}
//			
//			
//    	} catch (BusinessException e) {
//			LOGGER.error(e);
//			e.printStackTrace();
//		} catch (SQLException e) {
//			LOGGER.error(e);
//			e.printStackTrace();
//		} catch (Exception e) {
//			LOGGER.error(e);
//			e.printStackTrace();
//		} finally {
//			DatabaseManager.close(con);
//		}
//    	
//    	
//    	return returnedApplicationVOs;
//    }

	public String updateApplicationPrintedStatusExtrenalDept(List<ApplicationVO> list, int userIdFromSession,String userNameFromSession,int userRole) {
		Connection con = null;
		boolean isSuccess=true;
		LOGGER.info("updateApplicationPrintedStatusExtrenalDept list :" + list);
		try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			
			for (ApplicationVO applicationVO : list) {
				LOGGER.info("applicationVO :" + applicationVO);
				if(!(applicationVO==null)){
					
						String comment="The application with refernce no " + applicationVO.getReferenceNo() + " was printed, no of times printed :" + (applicationVO.getAddressPrintedStatus() + 1);
						//check if the current user has the record lock
						LOGGER.info("comment :" + comment);
						
						PoliceEnumConstant.UserDepartment userRoleEnum=PoliceEnumConstant.UserDepartment.fromCode(userRole);
							
						//first update the application status;
						String status=null;
						if(userRole==PoliceEnumConstant.UserDepartment.POL.getCode() || userRole==PoliceEnumConstant.UserDepartment.PHQ.getCode()){
							LOGGER.info("userRole POL OR PHQ:");
							status=ExternalDeptPrintSearchDAO.getInstance().updateApplicationPrintedStatusExtrenalDeptPolice(applicationVO.getPrintAddressId(),userRoleEnum.toString(), con);
						}else{
							LOGGER.info("userRole NOT POL OR PHQ:");
							status=ExternalDeptPrintSearchDAO.getInstance().updateApplicationPrintedStatusExtrenalDept(applicationVO.getApplicationId(),userRoleEnum.toString(), con);
						}	
							
						LOGGER.info("hasGainedLock  update app review approval updated status" + status);
						if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
							//insert the audit action
							ChangeAuditVO changeAuditVO=new ChangeAuditVO(applicationVO.getApplicationId(), userIdFromSession, userNameFromSession, comment);
							status=ApplicationDAO.getInstance().addChangeAudit(changeAuditVO, con);
							
							if(!(StringUtils.equals(status, PoliceConstant.SUCCESS))){
								isSuccess=false;
								break;
							}
						}else{
							isSuccess=false;
							break;
						}
				}else{
					isSuccess=false;
					break;
				}
			}
			
			if(isSuccess){
				con.commit();
			}else{
				con.rollback();
			}
			
		} catch (BusinessException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (SQLException e) {
			LOGGER.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			DatabaseManager.close(con);
		}
		if(isSuccess){
			return PoliceConstant.SUCCESS;
		}
		return PoliceConstant.ERROR;
	}

}
