package lk.icta.police.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ClearanceReportPrintVO;
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


/**
 * Action class for the Clearance Report
 * 
 * @author smarambage001
 *
 */
public class ClearanceReportAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = -5258530376835402458L;
	private static final Logger LOGGER = Logger.getLogger(ClearanceReportAction.class);
	
	private Map<String, Object> session;
	HttpServletRequest request;
	HttpServletResponse response;	
	private Date fromDate;
	private Date toDate;
	private String fileName;
	private List<ApplicationVO> applicationList;
	
	
	
	/**
	 * to execute
	 */
	public String execute(){
		LOGGER.info("ClearanceReportAction -> EXECUTE");
		return SUCCESS;
	}
	
	
	/**
	 * to load data
	 * 
	 * @return
	 */
	public String loadClearanceReport() {
		LOGGER.info("ClearanceReportAction -> loadClearanceReport()");
		LOGGER.info("FROM_DATE -- "+fromDate);
		LOGGER.info("TO_DATE -- "+toDate);
		try {
			this.setApplicationList(null);
			List<ApplicationVO> applicationVOList = ApplicationBusiness.getInstance().loadApplicationToClearanceReport(fromDate, toDate);
			LOGGER.info("List_Size__________________ "+applicationVOList.size());
			
			if (applicationVOList.size() == 0) {
				addActionError(getText("No data found for the given search criteria")); 
			}
			this.setApplicationList(applicationVOList); 
			session.put(PoliceConstant.FROM_DATE, fromDate);
			session.put(PoliceConstant.TO_DATE, toDate);
			
		} catch (Exception e) {
			LOGGER.error("ClearanceReportAction :: loadClearanceReport :: Exception - "+e.getMessage());
			e.printStackTrace();
			
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * to print
	 * 
	 * @return
	 */
	public String printClearanceReportAction(){
		LOGGER.info("ClearanceReportAction -> printClearanceReportAction()");
		try {
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
						
			fromDate = (Date)formatter.parse(session.get(PoliceConstant.FROM_DATE).toString());
			toDate = (Date)formatter.parse(session.get(PoliceConstant.TO_DATE).toString());
		
			fileName=PoliceConstant.CLEARANCE_REPORT_FILE_NAME;
			LOGGER.info("File Name - "+fileName);
			
			List<ApplicationVO> applicationVOList = ApplicationBusiness.getInstance().loadApplicationToClearanceReport(fromDate, toDate);			
			ClearanceReportPrintVO clearanceReportPrintVO = constructClearanceReportPrintVO(applicationVOList);
			
			List<ClearanceReportPrintVO> dtoList = new ArrayList<ClearanceReportPrintVO>(); 
			dtoList.add(clearanceReportPrintVO);			
			JRDataSource reportData = new JRBeanCollectionDataSource(dtoList);
			
			// 1. Add report parameters
			HashMap<String, Object> params = new HashMap<String, Object>(); 		
			params.put("fromDate", fromDate);	
			params.put("toDate", toDate);	
			
			// 2.  Retrieve template
			InputStream reportStreamSubdailyTransaction = this.getClass().getResourceAsStream(PoliceConstant.CLEARANCE_REPORT_SUB_REPORT); 		
			// 3. Convert template to JasperDesign
			JasperDesign jdSubDailyTransaction = JRXmlLoader.load(reportStreamSubdailyTransaction);
			JasperReport jasperSubReportDailyTransaction = JasperCompileManager.compileReport(jdSubDailyTransaction);				
			params.put("CLEARANCE_REPORT_SUB_REPORT", jasperSubReportDailyTransaction);
						
			// 2.  Retrieve template
			InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.CLEARANCE_REPORT); 

			// 3. Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(reportStream);
			
			// 4. Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			// 5. Create the JasperPrint object
			// Make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);
			
			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	

			fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.EXTENSION_TYPE_PDF, jp, baos, fileName);
						
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("printTransactionReport --> Exception");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * to construct clearance report print
	 * 
	 * @param applicationVOList
	 * @return
	 * @throws BusinessException
	 */
	private ClearanceReportPrintVO constructClearanceReportPrintVO(List<ApplicationVO> applicationVOList)  throws BusinessException {
		ClearanceReportPrintVO clearanceReportPrintVO = new ClearanceReportPrintVO();
		clearanceReportPrintVO.setApplicationVOs(applicationVOList);
		clearanceReportPrintVO.setFileName(PoliceConstant.CLEARANCE_REPORT_FILE_NAME);
		return clearanceReportPrintVO;
	}
	
	

	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
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
	public List<ApplicationVO> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<ApplicationVO> applicationList) {
		this.applicationList = applicationList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
