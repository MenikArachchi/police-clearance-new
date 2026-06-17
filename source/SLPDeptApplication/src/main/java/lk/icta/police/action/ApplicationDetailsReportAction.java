package lk.icta.police.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.ApplicationDetailsReportBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationDetailsReportSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.CountryVO;
import lk.icta.police.util.ReportFileExportUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

public class ApplicationDetailsReportAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ApplicationDetailsReportAction.class);
    private Map<String, Object> session;

    private Map<String, PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap;
    private Map<String, PoliceEnumConstant.ApplicationClearenceStatus> clearanceStatusMap;
    private Date fromDate;
    private Date toDate;
    private String referenceNo;
    private String reviewStatus;
    private String clearanceStatus;
    private String nicNo;
    private String newNicNo;
    private String passportNo;
    private String name;
    private long countryId;

    private String searchReviewStatus;
    private String searchReferenceNo;
    private List<ApplicationVO> applicationList;
    private List<CountryVO> countryList;

    private String rptType;


    HttpServletRequest request;
    HttpServletResponse response;
    private String fileName;

    public String execute() {
        LOGGER.info("Application Details Report -> EXECUTE");
        setToDate(new Date());
        setFromDate(new Date());
        loadReviewStatusList();
        loadClearanceStatusList();
        loadApplicationDetailsReport();
        return SUCCESS;
    }


    private void loadClearanceStatusList() {
        clearanceStatusMap = PoliceEnumConstant.ApplicationClearenceStatus.getApplicationClearenceStatusMap();
    }


    private String loadReviewStatusList() {
        reviewStatusMap = PoliceEnumConstant.ApplicationReviewStatus.getApplicationReviewStatusMap();
        countryList = ApplicationBusiness.getInstance().getCountryList();
        LOGGER.info("Application Details Report -> " + reviewStatusMap.size());
        return SUCCESS;
    }

    public String loadApplicationDetailsReport() {
//		loadReviewStatusList();
//		LOGGER.info("TO DATE       -> " + toDate);
//		LOGGER.info("FROM DATE     -> " + fromDate);
//		LOGGER.info("REVIEW STATUS -> " + searchReviewStatus);
//		LOGGER.info("CLEARANCE STATUS -> " + clearanceStatus);
//		LOGGER.info("REFERENCE NO  -> " + searchReferenceNo);
//		LOGGER.info("NIC NO  -> " + nicNo);
//		LOGGER.info("Passport NO  -> " + passportNo);
//		LOGGER.info("Country   -> " + countryId);


        ApplicationDetailsReportSearchCriteriaVO applicationDetailsReportSearchCriteriaVO = new ApplicationDetailsReportSearchCriteriaVO();

        applicationDetailsReportSearchCriteriaVO.setToDate(toDate == null ? new Date() : toDate);
        applicationDetailsReportSearchCriteriaVO.setFromDate(fromDate == null ? new Date() : fromDate);
        applicationDetailsReportSearchCriteriaVO.setStatus((searchReviewStatus == null || searchReviewStatus.trim().equals("")) ? "" : searchReviewStatus);
        applicationDetailsReportSearchCriteriaVO.setStatus((clearanceStatus == null || clearanceStatus.trim().equals("")) ? "" : clearanceStatus);
        applicationDetailsReportSearchCriteriaVO.setReferenceNo((searchReferenceNo == null || searchReferenceNo.trim().equals("")) ? "" : searchReferenceNo);
        applicationDetailsReportSearchCriteriaVO.setNicNo((nicNo == null || nicNo.trim().equals("")) ? "" : nicNo);
        applicationDetailsReportSearchCriteriaVO.setNicNo((newNicNo == null || newNicNo.trim().equals("")) ? "" : newNicNo);
        applicationDetailsReportSearchCriteriaVO.setPassportNo((passportNo == null || passportNo.trim().equals("")) ? "" : passportNo);
        applicationDetailsReportSearchCriteriaVO.setName((name == null || name.trim().equals("")) ? "" : name);
        applicationDetailsReportSearchCriteriaVO.setCountryId(countryId);
        applicationDetailsReportSearchCriteriaVO.updateApplicationDetailsReportSearchCriteriaVO();
        applicationList = (ApplicationDetailsReportBusiness.getInstance().getAllocationList(applicationDetailsReportSearchCriteriaVO, getUserIdFromSession()));

        if ((applicationList == null || applicationList.isEmpty())) {
            addActionError("No Records Found");
            return ERROR;
        }

        if (!(this.fromDate == null)) {
            session.put("FROM_DATE", fromDate);
        }

        if (!(this.toDate == null)) {
            session.put("TO_DATE", toDate);
        }

        if (!(this.searchReviewStatus == null)) {
            session.put("search_ReviewStatus", searchReviewStatus);
        }

        if (!(this.clearanceStatus == null)) {
            session.put("search_ClearanceStatus", clearanceStatus);
        }

        if (!(this.searchReferenceNo == null)) {
            session.put("search_ReferenceNo", searchReferenceNo);
        }

        if (!(this.nicNo == null)) {
            session.put("nic_No", nicNo);
        }

        if (!(this.passportNo == null)) {
            session.put("passport_No", passportNo);
        }

        if (!(this.name == null)) {
            session.put("name", name);
        }

        if (!(this.countryId <= 0)) {
            session.put("Country", countryId);
        }

        //session.put("FROM_DATE", fromDate);
        //session.put("TO_DATE", toDate);

        LOGGER.info("fromDate2 - " + session.get("FROM_DATE"));
        LOGGER.info("toDate2 - " + session.get("TO_DATE"));

        LOGGER.info("reviewStatusMap -> " + reviewStatusMap.size());
        return SUCCESS;
    }

    public String searchApplicationDetailsReport() {
        loadReviewStatusList();
        loadClearanceStatusList();
//		LOGGER.info("TO DATE       -> " + toDate);
//		LOGGER.info("FROM DATE     -> " + fromDate);
//		LOGGER.info("REVIEW STATUS -> " + searchReviewStatus);
//		LOGGER.info("Clearance STATUS -> " + clearanceStatus);
//		LOGGER.info("REFERENCE NO  -> " + searchReferenceNo);
//		LOGGER.info("NIC NO  -> " + nicNo);
//		LOGGER.info("Passport NO  -> " + passportNo);
//		LOGGER.info("Country   -> " + countryId);


        ApplicationDetailsReportSearchCriteriaVO applicationDetailsReportSearchCriteriaVO = new ApplicationDetailsReportSearchCriteriaVO();

        applicationDetailsReportSearchCriteriaVO.setToDate(toDate);
        applicationDetailsReportSearchCriteriaVO.setFromDate(fromDate);
        applicationDetailsReportSearchCriteriaVO.setStatus(searchReviewStatus);
        applicationDetailsReportSearchCriteriaVO.setClearanceStatus(clearanceStatus);
        applicationDetailsReportSearchCriteriaVO.setReferenceNo(searchReferenceNo);
        applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
        applicationDetailsReportSearchCriteriaVO.setNewNicNo(newNicNo);
        applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
        applicationDetailsReportSearchCriteriaVO.setName(name);
        applicationDetailsReportSearchCriteriaVO.setCountryId(countryId);
        applicationDetailsReportSearchCriteriaVO.updateApplicationDetailsReportSearchCriteriaVO();
        applicationList = (ApplicationDetailsReportBusiness.getInstance().getAllocationList(applicationDetailsReportSearchCriteriaVO, getUserIdFromSession()));

        if ((applicationList == null || applicationList.isEmpty())) {
            addActionError("No Records Found");
            return ERROR;
        }

        if (!(this.fromDate == null)) {
            session.put("FROM_DATE", fromDate);
        }

        if (!(this.toDate == null)) {
            session.put("TO_DATE", toDate);
        }

        if (!(this.searchReviewStatus == null)) {
            session.put("search_ReviewStatus", searchReviewStatus);
        }

        if (!(this.clearanceStatus == null)) {
            session.put("search_ClearanceStatus", clearanceStatus);
        }

        if (!(this.searchReferenceNo == null)) {
            session.put("search_ReferenceNo", searchReferenceNo);
        }

        if (!(this.nicNo == null)) {
            session.put("nic_No", nicNo);
        }

        if (!(this.passportNo == null)) {
            session.put("passport_No", passportNo);
        }

        if (!(this.name == null)) {
            session.put("name", name);
        }

        if (!(this.countryId <= 0)) {
            session.put("Country", countryId);
        }

        //session.put("FROM_DATE", fromDate);
        //session.put("TO_DATE", toDate);

        LOGGER.info("fromDate2 - " + session.get("FROM_DATE"));
        LOGGER.info("toDate2 - " + session.get("TO_DATE"));

        LOGGER.info("reviewStatusMap -> " + reviewStatusMap.size());
        return SUCCESS;
    }

	
	
	/*public String printApplicationDetailReport(){
		LOGGER.info("ApplicationDetailReportAction -> printApplicationDetailReport()");
		try {
		
			fileName=PoliceConstant.APPLICATION_DETAILS_REPORT_FILE_NAME;
			LOGGER.info("File Name - "+fileName);
			
			//List<ApplicationVO> applicationVOList = ApplicationBusiness.getInstance().loadApplicationToClearanceReport(fromDate, toDate);			
			//ClearanceReportPrintVO clearanceReportPrintVO = constructClearanceReportPrintVO(applicationVOList);
			
		//	List<ClearanceReportPrintVO> dtoList = new ArrayList<ClearanceReportPrintVO>(); 
		//	dtoList.add(clearanceReportPrintVO);			
			JRDataSource reportData = new JRBeanCollectionDataSource(null);
			Date date = new Date();
			// 1. Add report parameters
			HashMap<String, Object> params = new HashMap<String, Object>(); 		
//			params.put("fromDate", date.getTime());	
		
			
			// 2.  Retrieve template
			//InputStream reportStreamSubdailyTransaction = this.getClass().getResourceAsStream(PoliceConstant.CLEARANCE_REPORT_SUB_REPORT); 		
			// 3. Convert template to JasperDesign
			//JasperDesign jdSubDailyTransaction = JRXmlLoader.load(reportStreamSubdailyTransaction);
			//JasperReport jasperSubReportDailyTransaction = JasperCompileManager.compileReport(jdSubDailyTransaction);				
			//params.put("CLEARANCE_REPORT_SUB_REPORT", jasperSubReportDailyTransaction);
						
			// 2.  Retrieve template
			InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.APPLICATION_DETAILS_REPORT); 

			// 3. Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(reportStream);
			
			// 4. Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			// 5. Create the JasperPrint object
			// Make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = JasperFillManager.fillReport(jr, params);
			
			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	

			fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.EXTENSION_TYPE_PDF, jp, baos, fileName);
			LOGGER.info("File Name - "+fileName);
						
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("ApplicationDetailReportAction --> Exception");
			return ERROR;
		}
		return SUCCESS;
	}*/

    public String printApplicationDetailReport() {
        try {
            ApplicationDetailsReportSearchCriteriaVO applicationDetailsReportSearchCriteriaVO = new ApplicationDetailsReportSearchCriteriaVO();
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//			LOGGER.info("fromDate1 - "+session.get("FROM_DATE"));
//			LOGGER.info("toDate1 - "+session.get("TO_DATE"));

            LOGGER.info("session fromDate - " + session.get("FROM_DATE"));
            LOGGER.info("session toDate - " + session.get("TO_DATE"));

            if (!(session.get("FROM_DATE") == null)) {
                fromDate = (Date) formatter.parse(session.get("FROM_DATE").toString());
            }

            if (!(session.get("TO_DATE") == null)) {
                toDate = (Date) formatter.parse(session.get("TO_DATE").toString());
            }

            if (!(session.get("search_ReviewStatus") == null)) {
                searchReviewStatus = session.get("search_ReviewStatus").toString();
            }

            if (!(session.get("search_ClearanceStatus") == null)) {
                clearanceStatus = session.get("search_ClearanceStatus").toString();
            }


            if (!(session.get("search_ReferenceNo") == null)) {
                searchReferenceNo = session.get("search_ReferenceNo").toString();
            }

            if (!(session.get("nic_No") == null)) {
                nicNo = session.get("nic_No").toString();
            }

            if (!(session.get("new_nic_No") == null)) {
                newNicNo = session.get("new_nic_No").toString();
            }

            if (!(session.get("passport_No") == null)) {
                passportNo = session.get("passport_No").toString();
            }

            if (!(session.get("name") == null)) {
                name = session.get("name").toString();
            }

            if (!(session.get("Country") == null)) {
                countryId = (Long) session.get("Country");
            }


            LOGGER.info("fromDate - " + fromDate);
            LOGGER.info("toDate - " + toDate);

//			rptType=(Integer) session.get("rptType");
//			LOGGER.info("rptType - "+rptType);

            applicationDetailsReportSearchCriteriaVO.setToDate(toDate);
            applicationDetailsReportSearchCriteriaVO.setFromDate(fromDate);
            applicationDetailsReportSearchCriteriaVO.setStatus(searchReviewStatus);
            applicationDetailsReportSearchCriteriaVO.setClearanceStatus(clearanceStatus);
            applicationDetailsReportSearchCriteriaVO.setReferenceNo(searchReferenceNo);
//			applicationDetailsReportSearchCriteriaVO.setReferenceNo("REF0000");
            applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
            applicationDetailsReportSearchCriteriaVO.setNewNicNo(newNicNo);
            applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
            applicationDetailsReportSearchCriteriaVO.setName(name);
            applicationDetailsReportSearchCriteriaVO.setCountryId(countryId);
            applicationDetailsReportSearchCriteriaVO.updateApplicationDetailsReportSearchCriteriaVO();
            applicationList = ApplicationDetailsReportBusiness.getInstance().getAllocationList(applicationDetailsReportSearchCriteriaVO, getUserIdFromSession());

//			if((applicationList==null || applicationList.isEmpty())){
//				addActionError("No Records Found");
//				return ERROR;
//			}
            fileName = PoliceConstant.APPLICATION_DETAILS_REPORT_FILE_NAME;


            JRDataSource reportData = new JRBeanCollectionDataSource(applicationList);

            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("START_DATE", fromDate);
            params.put("END_DATE", toDate);
            if (!(applicationList == null)) {
                params.put("TOTAL_RECORDS", applicationList.size());
            } else {
                params.put("TOTAL_RECORDS", 0);
            }

            //params.put("toDay", new Date());

            Date reportDate = Calendar.getInstance().getTime();
            String reportUser = getUserNameFromSession();
            UserVO selectedUser = new UserVO();
            selectedUser.setId(getUserIdFromSession());
            UserVO user = null;
            try {
                user = CommonUserBusiness.getInstance().getUserDetails(selectedUser);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (!(user == null)) {
                reportUser = user.getFullName();
            }

            params.put("REPORT_DATE", reportDate);
            params.put("REPORT_USER", reportUser);


            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource(PoliceConstant.POL_LOGO));
                params.put("LOGO_IMAGE", image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.APPLICATION_DETAILS_REPORT);

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);

            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();


//			fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.EXTENSION_TYPE_PDF, jp, baos,fileName);
            fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.FILE_TYPE_PDF, jp, baos, fileName);


        } catch (BusinessException e) {
            e.printStackTrace();
            LOGGER.info("printDailyTransactionReport --> BusinessException");
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("printDailyTransactionReport --> Exception");
            return ERROR;
        }
        return SUCCESS;
    }

    private String getUserNameFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getFullName();
        }
        return null;
    }

    public String printApplicationDetailReport_Excel() {
        try {
            ApplicationDetailsReportSearchCriteriaVO applicationDetailsReportSearchCriteriaVO = new ApplicationDetailsReportSearchCriteriaVO();

            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//			LOGGER.info("fromDate1 - "+session.get("FROM_DATE"));
//			LOGGER.info("toDate1 - "+session.get("TO_DATE"));
            if (!(session.get("FROM_DATE") == null)) {
                fromDate = (Date) formatter.parse(session.get("FROM_DATE").toString());
            }

            if (!(session.get("TO_DATE") == null)) {
                toDate = (Date) formatter.parse(session.get("TO_DATE").toString());
            }

            if (!(session.get("search_ReviewStatus") == null)) {
                searchReviewStatus = session.get("search_ReviewStatus").toString();
            }

            if (!(session.get("search_ClearanceStatus") == null)) {
                clearanceStatus = session.get("search_ClearanceStatus").toString();
            }


            if (!(session.get("search_ReferenceNo") == null)) {
                searchReferenceNo = session.get("search_ReferenceNo").toString();
            }

            if (!(session.get("nic_No") == null)) {
                nicNo = session.get("nic_No").toString();
            }

            if (!(session.get("new_nic_No") == null)) {
                newNicNo = session.get("new_nic_No").toString();
            }

            if (!(session.get("passport_No") == null)) {
                passportNo = session.get("passport_No").toString();
            }

            if (!(session.get("name") == null)) {
                name = session.get("name").toString();
            }

            if (!(session.get("Country") == null)) {
                countryId = (Long) session.get("Country");
            }

            LOGGER.info("fromDate - " + fromDate);
            LOGGER.info("toDate - " + toDate);

//			rptType=(Integer) session.get("rptType");
//			LOGGER.info("rptType - "+rptType);


            applicationDetailsReportSearchCriteriaVO.setToDate(toDate);
            applicationDetailsReportSearchCriteriaVO.setFromDate(fromDate);
            applicationDetailsReportSearchCriteriaVO.setStatus(searchReviewStatus);
            applicationDetailsReportSearchCriteriaVO.setClearanceStatus(clearanceStatus);
            applicationDetailsReportSearchCriteriaVO.setReferenceNo(searchReferenceNo);
//			applicationDetailsReportSearchCriteriaVO.setReferenceNo("REF0000");
            applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
            applicationDetailsReportSearchCriteriaVO.setNewNicNo(newNicNo);
            applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
            applicationDetailsReportSearchCriteriaVO.setName(name);
            applicationDetailsReportSearchCriteriaVO.setCountryId(countryId);
            applicationDetailsReportSearchCriteriaVO.updateApplicationDetailsReportSearchCriteriaVO();
            applicationList = ApplicationDetailsReportBusiness.getInstance().getAllocationList(applicationDetailsReportSearchCriteriaVO, getUserIdFromSession());

//			if((applicationList==null || applicationList.isEmpty())){
//				addActionError("No Records Found");
//				return ERROR;
//			}


            fileName = PoliceConstant.APPLICATION_DETAILS_REPORT_FILE_NAME;


            JRDataSource reportData = new JRBeanCollectionDataSource(applicationList);

            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("START_DATE", fromDate);
            params.put("END_DATE", toDate);
            //params.put("toDay", new Date());
            if (!(applicationList == null)) {
                params.put("TOTAL_RECORDS", applicationList.size());
            } else {
                params.put("TOTAL_RECORDS", 0);
            }

            Date reportDate = Calendar.getInstance().getTime();
            String reportUser = getUserNameFromSession();
            UserVO selectedUser = new UserVO();
            selectedUser.setId(getUserIdFromSession());
            UserVO user = null;
            try {
                user = CommonUserBusiness.getInstance().getUserDetails(selectedUser);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (!(user == null)) {
                reportUser = user.getFullName();
            }

            params.put("REPORT_DATE", reportDate);
            params.put("REPORT_USER", reportUser);

            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource(PoliceConstant.POL_LOGO));
                params.put("LOGO_IMAGE", image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.APPLICATION_DETAILS_REPORT);

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);

            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.FILE_TYPE_EXCEL, jp, baos, fileName);
        } catch (BusinessException e) {
            e.printStackTrace();
            LOGGER.info("printDailyTransactionReport --> BusinessException");
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("printDailyTransactionReport --> Exception");
            return ERROR;
        }
        return SUCCESS;
    }


    private int getUserIdFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getId();
        }
        return 0;
    }

    private UserVO getUserFromSession() {
        if (!(session.get(PoliceConstant.SESSION_USER) == null)) {
            return (UserVO) session.get(PoliceConstant.SESSION_USER);
        }
        return null;
    }

    public Date getFromDate() {
        return fromDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Map<String, PoliceEnumConstant.ApplicationReviewStatus> getReviewStatusMap() {
        return reviewStatusMap;
    }

    public void setReviewStatusMap(
            Map<String, PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap) {
        this.reviewStatusMap = reviewStatusMap;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getSearchReviewStatus() {
        return searchReviewStatus;
    }

    public void setSearchReviewStatus(String searchReviewStatus) {
        this.searchReviewStatus = searchReviewStatus;
    }

    public String getSearchReferenceNo() {
        return searchReferenceNo;
    }

    public void setSearchReferenceNo(String searchReferenceNo) {
        this.searchReferenceNo = searchReferenceNo;
    }

    public List<ApplicationVO> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<ApplicationVO> applicationList) {
        this.applicationList = applicationList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;

    }


    public String getRptType() {
        return rptType;
    }


    public void setRptType(String rptType) {
        this.rptType = rptType;
    }


    public Map<String, PoliceEnumConstant.ApplicationClearenceStatus> getClearanceStatusMap() {
        return clearanceStatusMap;
    }


    public void setClearanceStatusMap(
            Map<String, PoliceEnumConstant.ApplicationClearenceStatus> clearanceStatusMap) {
        this.clearanceStatusMap = clearanceStatusMap;
    }


    public String getClearanceStatus() {
        return clearanceStatus;
    }


    public void setClearanceStatus(String clearanceStatus) {
        this.clearanceStatus = clearanceStatus;
    }


    public List<CountryVO> getCountryList() {
        return countryList;
    }


    public void setCountryList(List<CountryVO> countryList) {
        this.countryList = countryList;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getNewNicNo() {
        return newNicNo;
    }

    public void setNewNicNo(String newNicNo) {
        this.newNicNo = newNicNo;
    }
}
