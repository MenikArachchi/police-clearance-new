package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.DailyTransactionReportBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.DailyTransactionReportPrintVO;
import lk.icta.police.framework.vo.DailyTransactionReportSearchCriteriaVO;
import lk.icta.police.framework.vo.DailyTransactionVO;
import lk.icta.police.util.ReportFileExportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class DailyTransactionReportAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DailyTransactionReportAction.class);
	private Map<String, Object> session;
	
	private Map<String,PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap;
	private Map<String,PoliceEnumConstant.ApplicationType> applicationTypeMap;
	
	private Date fromDate;
	private Date toDate;
	private String searchReviewStatus;
	private String searchSubmissionMode;
	private BigDecimal tot;
	private BigDecimal totConvenience;
	
	private List<DailyTransactionVO> applicationList;
	private String fileName;
	
	public String execute(){
		LOGGER.info("Daily Transaction Report -> EXECUTE");
		setToDate(new Date());
		setFromDate(new Date());
		loadReviewStatusList();
		loadModeList();
		loadDailyTransactionReport();
		return SUCCESS;
	}
	
	private String loadReviewStatusList() {
		reviewStatusMap = PoliceEnumConstant.ApplicationReviewStatus.getApplicationReviewStatusMap();
		LOGGER.info("Daily Transaction Report -> " + reviewStatusMap.size());
		return SUCCESS;
	}
	
	private String loadModeList() {
		applicationTypeMap = PoliceEnumConstant.ApplicationType.getPaymentModeMap();
		LOGGER.info("Daily Transaction Report -> " + applicationTypeMap.size());
		return SUCCESS;
	}
	
	public String loadDailyTransactionReport() {
		LOGGER.info("Daily Transaction Report -> EXECUTE Search");
//		loadReviewStatusList();
//		loadModeList();
		LOGGER.info("TO DATE       -> " + toDate);
		LOGGER.info("FROM DATE     -> " + fromDate);
		LOGGER.info("REVIEW STATUS -> " + searchReviewStatus);
		LOGGER.info("Submission Mode  -> " + searchSubmissionMode);
//		LOGGER.info("NIC NO  -> " + nicNo);
//		LOGGER.info("Passport NO  -> " + passportNo);
//		LOGGER.info("Country   -> " + country);
		

		
		DailyTransactionReportSearchCriteriaVO dailyTransactionReportSearchCriteriaVO = new DailyTransactionReportSearchCriteriaVO();
		
		dailyTransactionReportSearchCriteriaVO.setToDate(toDate == null ? new Date() : toDate);
		dailyTransactionReportSearchCriteriaVO.setFromDate(fromDate == null ? new Date() : fromDate);
		dailyTransactionReportSearchCriteriaVO.setStatus((searchReviewStatus == null ||searchReviewStatus.trim().equals("")) ? "" : searchReviewStatus);
		dailyTransactionReportSearchCriteriaVO.setSubmission((searchSubmissionMode == null ||searchSubmissionMode.trim().equals("")) ? "" : searchSubmissionMode);
//		applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
//		applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
//		applicationDetailsReportSearchCriteriaVO.setCountry(country);
		dailyTransactionReportSearchCriteriaVO.updateDailyTransactionReportSearchCriteriaVO();
		applicationList =(DailyTransactionReportBusiness.getInstance().getAllocationList(dailyTransactionReportSearchCriteriaVO, getUserIdFromSession()));
		
		if((applicationList==null || applicationList.isEmpty())){
			addActionError("No Records Found");
			return ERROR;
		}
		
		tot = new BigDecimal("0.00");
		totConvenience= new BigDecimal("0.00");
		for (DailyTransactionVO iterable_element : applicationList) {
			tot =tot.add(iterable_element.getTotalFee());
			totConvenience =totConvenience.add(iterable_element.getConvenienceFee());
			
		}
		
		if(!(this.fromDate==null)){
			session.put("FROM_DATE", fromDate);
		}
		
		if(!(this.toDate==null)){
			session.put("TO_DATE", toDate);
		}
		
		if(!(this.searchReviewStatus==null)){
			session.put("search_ReviewStatus", searchReviewStatus);
		}
		
		if(!(this.searchSubmissionMode==null)){
			session.put("search_SubmissionMode", searchSubmissionMode);
		}
		
//		session.put("FROM_DATE", fromDate);
//		session.put("TO_DATE", toDate);
		
		LOGGER.info("fromDate2 - "+session.get("FROM_DATE"));
		LOGGER.info("toDate2 - "+session.get("TO_DATE"));
		
		LOGGER.info("reviewStatusMap -> " + reviewStatusMap.size());
		return SUCCESS;
	}
	
	public String searchDailyTransactionReport() {
		LOGGER.info("Daily Transaction Report -> EXECUTE Search");
		loadReviewStatusList();
		loadModeList();
		LOGGER.info("TO DATE       -> " + toDate);
		LOGGER.info("FROM DATE     -> " + fromDate);
		LOGGER.info("REVIEW STATUS -> " + searchReviewStatus);
		LOGGER.info("Submission Mode  -> " + searchSubmissionMode);
//		LOGGER.info("NIC NO  -> " + nicNo);
//		LOGGER.info("Passport NO  -> " + passportNo);
//		LOGGER.info("Country   -> " + country);
		

		
		DailyTransactionReportSearchCriteriaVO dailyTransactionReportSearchCriteriaVO = new DailyTransactionReportSearchCriteriaVO();
		
		dailyTransactionReportSearchCriteriaVO.setToDate(toDate);
		dailyTransactionReportSearchCriteriaVO.setFromDate(fromDate);
		dailyTransactionReportSearchCriteriaVO.setStatus(searchReviewStatus);
		dailyTransactionReportSearchCriteriaVO.setSubmission(searchSubmissionMode);
//		applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
//		applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
//		applicationDetailsReportSearchCriteriaVO.setCountry(country);
		dailyTransactionReportSearchCriteriaVO.updateDailyTransactionReportSearchCriteriaVO();
		applicationList =(DailyTransactionReportBusiness.getInstance().getAllocationList(dailyTransactionReportSearchCriteriaVO, getUserIdFromSession()));
		
		if((applicationList==null || applicationList.isEmpty())){
			addActionError("No Records Found");
			return ERROR;
		}
		
		tot = new BigDecimal("0.00");
		totConvenience= new BigDecimal("0.00");
		for (DailyTransactionVO iterable_element : applicationList) {
			tot =tot.add(iterable_element.getTotalFee());
			totConvenience =totConvenience.add(iterable_element.getConvenienceFee());
			
		}
		
		if(!(this.fromDate==null)){
			session.put("FROM_DATE", fromDate);
		}
		
		if(!(this.toDate==null)){
			session.put("TO_DATE", toDate);
		}
		
		if(!(this.searchReviewStatus==null)){
			session.put("search_ReviewStatus", searchReviewStatus);
		}
		
		if(!(this.searchSubmissionMode==null)){
			session.put("search_SubmissionMode", searchSubmissionMode);
		}
		
//		session.put("FROM_DATE", fromDate);
//		session.put("TO_DATE", toDate);
		
		LOGGER.info("fromDate2 - "+session.get("FROM_DATE"));
		LOGGER.info("toDate2 - "+session.get("TO_DATE"));
		
		LOGGER.info("reviewStatusMap -> " + reviewStatusMap.size());
		return SUCCESS;
	}
	
	public String printDailyTransactionReport(){
		try {
			DailyTransactionReportSearchCriteriaVO dailyTransactionReportSearchCriteriaVO = new DailyTransactionReportSearchCriteriaVO();
			
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//			LOGGER.info("fromDate1 - "+session.get("FROM_DATE"));
//			LOGGER.info("toDate1 - "+session.get("TO_DATE"));
//			fromDate =(Date)formatter.parse(session.get("FROM_DATE").toString());
//			toDate =(Date)formatter.parse(session.get("TO_DATE").toString());
			
			if(!(session.get("FROM_DATE")==null)){
				fromDate =(Date)formatter.parse(session.get("FROM_DATE").toString());
			}
			
			if(!(session.get("TO_DATE")==null)){
				toDate =(Date)formatter.parse(session.get("TO_DATE").toString());
			}
			
			if(!(session.get("search_ReviewStatus")==null)){
				searchReviewStatus =session.get("search_ReviewStatus").toString();
			}
			
			if(!(session.get("search_SubmissionMode")==null)){
				searchSubmissionMode =session.get("search_SubmissionMode").toString();
			}
			
			LOGGER.info("fromDate - "+fromDate);
			LOGGER.info("toDate - "+toDate);
			
//			rptType=(Integer) session.get("rptType");
//			LOGGER.info("rptType - "+rptType);
			
			
			dailyTransactionReportSearchCriteriaVO.setToDate(toDate);
			dailyTransactionReportSearchCriteriaVO.setFromDate(fromDate);
			dailyTransactionReportSearchCriteriaVO.setStatus(searchReviewStatus);
			dailyTransactionReportSearchCriteriaVO.setSubmission(searchSubmissionMode);
//			applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
//			applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
//			applicationDetailsReportSearchCriteriaVO.setCountry(country);
			dailyTransactionReportSearchCriteriaVO.updateDailyTransactionReportSearchCriteriaVO();
			applicationList =(DailyTransactionReportBusiness.getInstance().getAllocationList(dailyTransactionReportSearchCriteriaVO, getUserIdFromSession()));
			
//			if((applicationList==null || applicationList.isEmpty())){
//				addActionError("No Records Found");
//				return ERROR;
//			}
			
			DailyTransactionReportPrintVO dailyTransactionReportPrintVO = constructApplicationDetailPrintVO(applicationList);
			
			fileName=PoliceConstant.DAILY_TRANSACTION_REPORT_FILE_NAME;
			
			List<DailyTransactionReportPrintVO> dtoList=new ArrayList<DailyTransactionReportPrintVO>();
			dtoList.add(dailyTransactionReportPrintVO);
			
			JRDataSource reportData = new JRBeanCollectionDataSource(dtoList);
			
			// 1. Add report parameters
			HashMap<String, Object> params = new HashMap<String, Object>(); 
			params.put("startDate", fromDate);	
			params.put("endDate", toDate);	
			//params.put("toDay", new Date());
            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource(PoliceConstant.POL_LOGO));
                params.put("LOGO_IMAGE", image);
            } catch (IOException e1) {
                LOGGER.error(e1.getStackTrace());
//                e1.printStackTrace();
            }

            Date reportDate = Calendar.getInstance().getTime();
            String reportUser = getUserNameFromSession();
            params.put("REPORT_DATE", reportDate);
            params.put("REPORT_USER", reportUser);
            params.put("TOTAL_RECORDS", applicationList.size());

			// 2.  Retrieve template
			InputStream reportStreamSubdailyTransaction = this.getClass().getResourceAsStream(PoliceConstant.DAILY_TRANSACTION_REPORT_SUB_REPORT); 		
			// 3. Convert template to JasperDesign
			JasperDesign jdSubDailyTransaction = JRXmlLoader.load(reportStreamSubdailyTransaction);
			JasperReport jasperSubReportDailyTransaction = JasperCompileManager.compileReport(jdSubDailyTransaction);				
			params.put("DAILY_TRANSACTION_REPORT_SUB_REPORT", jasperSubReportDailyTransaction);
			
			// 2.  Retrieve template
			InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.DAILY_TRANSACTION_REPORT); 
			
			// 3. Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(reportStream);
			
			// 4. Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			// 5. Create the JasperPrint object
			// Make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);
			
			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			
			fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.FILE_TYPE_PDF, jp, baos,fileName);
		} catch (BusinessException e) {
			e.printStackTrace();
			LOGGER.info("DailyTransactionReport --> BusinessException");
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("DailyTransactionReport --> Exception");
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

	public String printDailyTransactionReportExcel(){
		try {
			DailyTransactionReportSearchCriteriaVO dailyTransactionReportSearchCriteriaVO = new DailyTransactionReportSearchCriteriaVO();
			
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//			LOGGER.info("fromDate1 - "+session.get("FROM_DATE"));
//			LOGGER.info("toDate1 - "+session.get("TO_DATE"));
//			fromDate =(Date)formatter.parse(session.get("FROM_DATE").toString());
//			toDate =(Date)formatter.parse(session.get("TO_DATE").toString());
			
			if(!(session.get("FROM_DATE")==null)){
				fromDate =(Date)formatter.parse(session.get("FROM_DATE").toString());
			}
			
			if(!(session.get("TO_DATE")==null)){
				toDate =(Date)formatter.parse(session.get("TO_DATE").toString());
			}
			
			if(!(session.get("search_ReviewStatus")==null)){
				searchReviewStatus =session.get("search_ReviewStatus").toString();
			}
			
			if(!(session.get("search_SubmissionMode")==null)){
				searchSubmissionMode =session.get("search_SubmissionMode").toString();
			}
			
			LOGGER.info("fromDate - "+fromDate);
			LOGGER.info("toDate - "+toDate);
			
//			rptType=(Integer) session.get("rptType");
//			LOGGER.info("rptType - "+rptType);
			
			
			dailyTransactionReportSearchCriteriaVO.setToDate(toDate);
			dailyTransactionReportSearchCriteriaVO.setFromDate(fromDate);
			dailyTransactionReportSearchCriteriaVO.setStatus(searchReviewStatus);
			dailyTransactionReportSearchCriteriaVO.setSubmission(searchSubmissionMode);
//			applicationDetailsReportSearchCriteriaVO.setNicNo(nicNo);
//			applicationDetailsReportSearchCriteriaVO.setPassportNo(passportNo);
//			applicationDetailsReportSearchCriteriaVO.setCountry(country);
			dailyTransactionReportSearchCriteriaVO.updateDailyTransactionReportSearchCriteriaVO();
			applicationList =(DailyTransactionReportBusiness.getInstance().getAllocationList(dailyTransactionReportSearchCriteriaVO, getUserIdFromSession()));
			
//			if((applicationList==null || applicationList.isEmpty())){
//				addActionError("No Records Found");
//				return ERROR;
//			}

			DailyTransactionReportPrintVO dailyTransactionReportPrintVO = constructApplicationDetailPrintVO(applicationList);
			
			fileName=PoliceConstant.DAILY_TRANSACTION_REPORT_FILE_NAME;
			
			List<DailyTransactionReportPrintVO> dtoList=new ArrayList<DailyTransactionReportPrintVO>();
			dtoList.add(dailyTransactionReportPrintVO);
			
			JRDataSource reportData = new JRBeanCollectionDataSource(dtoList);
			
			// 1. Add report parameters
			HashMap<String, Object> params = new HashMap<String, Object>(); 
			params.put("startDate", fromDate);	
			params.put("endDate", toDate);	
			//params.put("toDay", new Date());
            Date reportDate = Calendar.getInstance().getTime();
            String reportUser = getUserNameFromSession();
            params.put("REPORT_DATE", reportDate);
            params.put("REPORT_USER", reportUser);
            params.put("TOTAL_RECORDS", applicationList.size());
			
			// 2.  Retrieve template
			InputStream reportStreamSubdailyTransaction = this.getClass().getResourceAsStream(PoliceConstant.DAILY_TRANSACTION_REPORT_SUB_REPORT); 		
			// 3. Convert template to JasperDesign
			JasperDesign jdSubDailyTransaction = JRXmlLoader.load(reportStreamSubdailyTransaction);
			JasperReport jasperSubReportDailyTransaction = JasperCompileManager.compileReport(jdSubDailyTransaction);				
			params.put("DAILY_TRANSACTION_REPORT_SUB_REPORT", jasperSubReportDailyTransaction);
			
			// 2.  Retrieve template
			InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.DAILY_TRANSACTION_REPORT); 
			
			// 3. Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(reportStream);
			
			// 4. Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			// 5. Create the JasperPrint object
			// Make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);
			
			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			
			fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.FILE_TYPE_EXCEL, jp, baos,fileName);
		} catch (BusinessException e) {
			e.printStackTrace();
			LOGGER.info("DailyTransactionReport --> BusinessException");
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("DailyTransactionReport --> Exception");
			return ERROR;
		}
		return SUCCESS;
	}
	
	private DailyTransactionReportPrintVO constructApplicationDetailPrintVO(List<DailyTransactionVO> dailyTransactionVOs)  throws BusinessException {
		DailyTransactionReportPrintVO dailyTransactionPrintVO = new DailyTransactionReportPrintVO();
		dailyTransactionPrintVO.setDailyTransactionVOs(dailyTransactionVOs);
		dailyTransactionPrintVO.setFileName(PoliceConstant.DAILY_TRANSACTION_REPORT_FILE_NAME);
		return dailyTransactionPrintVO;
	}
	
	
	private int getUserIdFromSession() {
		UserVO userVO=getUserFromSession();
		if(!(userVO==null)){
			return userVO.getId();
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

	public Map<String,PoliceEnumConstant.ApplicationReviewStatus> getReviewStatusMap() {
		return reviewStatusMap;
	}

	public void setReviewStatusMap(Map<String,PoliceEnumConstant.ApplicationReviewStatus> reviewStatusMap) {
		this.reviewStatusMap = reviewStatusMap;
	}

	public Map<String,PoliceEnumConstant.ApplicationType> getApplicationTypeMap() {
		return applicationTypeMap;
	}

	public void setApplicationTypeMap(Map<String,PoliceEnumConstant.ApplicationType> applicationTypeMap) {
		this.applicationTypeMap = applicationTypeMap;
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

	public String getSearchReviewStatus() {
		return searchReviewStatus;
	}

	public void setSearchReviewStatus(String searchReviewStatus) {
		this.searchReviewStatus = searchReviewStatus;
	}

	public String getSearchSubmissionMode() {
		return searchSubmissionMode;
	}

	public void setSearchSubmissionMode(String searchSubmissionMode) {
		this.searchSubmissionMode = searchSubmissionMode;
	}

	public List<DailyTransactionVO> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<DailyTransactionVO> applicationList) {
		this.applicationList = applicationList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getTot() {
		return tot;
	}

	public void setTot(BigDecimal tot) {
		this.tot = tot;
	}

	public BigDecimal getTotConvenience() {
		return totConvenience;
	}

	public void setTotConvenience(BigDecimal totConvenience) {
		this.totConvenience = totConvenience;
	}

	

	
	

}
