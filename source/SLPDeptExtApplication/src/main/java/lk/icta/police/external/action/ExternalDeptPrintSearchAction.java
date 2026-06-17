package lk.icta.police.external.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.external.business.ExternalDeptPrintSearchBusiness;
import lk.icta.police.external.util.ReportFileExportUtil;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ExternalDeptPrintSearchCriteriaVO;
import lk.icta.police.framework.vo.UserRoleDisplayVO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class ExternalDeptPrintSearchAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ExternalDeptPrintSearchAction.class);
	private Map<String, Object> session;
	
	private Date fromDate;
	private Date toDate;
	
	private ExternalDeptPrintSearchCriteriaVO searchCriteriaVO;
	
	private List<ApplicationVO> applicationList;
	
	private int userRole;
	private UserRoleDisplayVO userRoleDisplayVO;
	
	private List<ApplicationVO> selectedList;
	
	private String fileName;
	
	public String viewPrintList(){
		initializeSearchCriteria();
		
		applicationList=ExternalDeptPrintSearchBusiness.getInstance().searchApplication(searchCriteriaVO, getUserRoleFromSession(), getUserLocationFromSession(), getUserIdFromSession());
		
		if(!(applicationList==null || applicationList.isEmpty())){
			loadUserRoleData();
		}
		return SUCCESS;
	}
	
	public String searchPrintList(){
		initializeSearchCriteria();
		applicationList=ExternalDeptPrintSearchBusiness.getInstance().searchApplication(searchCriteriaVO, getUserRoleFromSession(), getUserLocationFromSession(), getUserIdFromSession());
		if(!(applicationList==null || applicationList.isEmpty())){
			loadUserRoleData();
		}
		return SUCCESS;
	}
	
	
	public String loadSelectedApplicationList(){
		String resultType=SUCCESS;
		if(!(selectedList==null || selectedList.isEmpty())){
			initializeSearchCriteria();
			loadUserRoleData();
			try {
				Date reportDate=Calendar.getInstance().getTime();
				// 1. Add report parameters			
				JRDataSource reportData = new JRBeanCollectionDataSource(selectedList);
				// 1. Add report parameters
				HashMap<String, Object> params = new HashMap<String, Object>();
				
				String reportUser=getUserNameFromSession();
				
				UserVO selectedUser=new UserVO();
				selectedUser.setId(getUserIdFromSession());
				UserVO user=null;
				try {
					user = CommonUserBusiness.getInstance().getUserDetails(selectedUser);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
				if(!(user==null)){
					reportUser=user.getFullName();
				}
				
				PoliceEnumConstant.UserDepartment userRoleEnum=PoliceEnumConstant.UserDepartment.fromCode(getUserRoleFromSession());	
				String reportDept=null;
				if(!(userRoleEnum==null)){
					reportDept=userRoleEnum.getDisplayName();
				}
				
				params.put("REPORT_DATE", reportDate);
				params.put("REPORT_DEPT", reportDept);
				params.put("REPORT_USER", reportUser);
				
				BufferedImage image=null;
				try {
					image = ImageIO.read(getClass().getResource(PoliceConstant.POL_LOGO));
					params.put("LOGO_IMAGE", image );	
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// 2.  Retrieve template
				InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.EXTERNAL_DEPARTMENT_PRINT_APP_LIST); 			
				// 3. Convert template to JasperDesign
				JasperDesign jd = JRXmlLoader.load(reportStream);			
				// 4. Compile design to JasperReport
				JasperReport jr = JasperCompileManager.compileReport(jd);			
				// 5. Create the JasperPrint object
				// Make sure to pass the JasperReport, report parameters, and data source
				JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);			
				// 6. Create an output byte stream where data will be written
				ByteArrayOutputStream baos = new ByteArrayOutputStream();	
				
				String fileType=PoliceConstant.FILE_TYPE_PDF;
				
				fileName="tempappprintfile_" + System.currentTimeMillis();
				try {
					fileName = ReportFileExportUtil.getInstance().export(fileType, jp, baos,fileName);
					if(StringUtils.isNotEmpty(fileName)){
						String status=ExternalDeptPrintSearchBusiness.getInstance().updateApplicationPrintedStatusExtrenalDept(selectedList,getUserIdFromSession(),getUserNameFromSession(),getUserRoleFromSession());
						if(StringUtils.equals(status, PoliceConstant.SUCCESS)){
							addActionMessage("Addresses were printed successfully!");
							return SUCCESS;
						}else{
							addActionError("Could not update the printed status. Internal Server Error!");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}	
				return resultType;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
			addActionError("Could not generate the file. Internal Server Error!");
		}else{
			addActionError("No application has been selected!");
		}
		return SUCCESS;
	}
	
	private void loadUserRoleData(){
		this.userRole=getUserRoleFromSession();
		this.userRoleDisplayVO=new UserRoleDisplayVO();
		
	}
	
	private void initializeSearchCriteria() {
		if(searchCriteriaVO==null){
			searchCriteriaVO=new ExternalDeptPrintSearchCriteriaVO(null, null, null, null, null);
			searchCriteriaVO.setStartFrom(0);
			searchCriteriaVO.setClearanceStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		}
		searchCriteriaVO.setLimit(PoliceConstant.UNLIMITED_RECORDS_LIMIT);
		searchCriteriaVO.setFromDate(fromDate);
		searchCriteriaVO.setToDate(toDate);		
		searchCriteriaVO.updateSearchCriteriaVO(getUserRoleFromSession(), getUserLocationFromSession());		
		System.out.println("searchCriteriaVO :" + searchCriteriaVO);
	}
	
	private String getUserNameFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getFullName();
		}
		return null;
	}
	
	private int getUserIdFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getId();
		}
		return 0;
	}
	
	
	private long getUserLocationFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getAssignedLocation();
		}
		return 0;
	}

	private int getUserRoleFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null || userVO.getDept()==null)){
			return userVO.getDept().getId();
		}
		return 0;
	}
	
	private UserVO getUserFromSession(){
		if(!(session.get(PoliceConstant.SESSION_USER)==null)){
			return (UserVO) session.get(PoliceConstant.SESSION_USER);
		}
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
		

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Date getFromDate() {
		return fromDate;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}
	@TypeConversion(converter="lk.icta.police.util.StringToDateConverter")
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public ExternalDeptPrintSearchCriteriaVO getSearchCriteriaVO() {
		return searchCriteriaVO;
	}
	public void setSearchCriteriaVO(
			ExternalDeptPrintSearchCriteriaVO searchCriteriaVO) {
		this.searchCriteriaVO = searchCriteriaVO;
	}

	public List<ApplicationVO> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<ApplicationVO> applicationList) {
		this.applicationList = applicationList;
	}

	
	public List<ApplicationVO> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<ApplicationVO> selectedList) {
		this.selectedList = selectedList;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public UserRoleDisplayVO getUserRoleDisplayVO() {
		return userRoleDisplayVO;
	}

	public void setUserRoleDisplayVO(UserRoleDisplayVO userRoleDisplayVO) {
		this.userRoleDisplayVO = userRoleDisplayVO;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
