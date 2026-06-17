package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.BlackListReportBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.BlackListVO;
import lk.icta.police.framework.vo.BlacklistReportPrintVO;
import lk.icta.police.framework.vo.BlacklistSearchCriteriaVO;
import lk.icta.police.util.ReportFileExportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BlacklistAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(BlacklistAction.class);
    private Map<String, Object> session;

    private String nicNo;
    private String newNicNo;
    private String currentNic;
    private String passportNo;
    private String name;
    private List<BlackListVO> applicationList;
    private String fileName;

    public String execute() {
        searchBlacklist();
//		LOGGER.info("Blacklist -> EXECUTE");
        return SUCCESS;
    }


    public String searchBlacklist() {
//		LOGGER.info("Daily Transaction Report -> EXECUTE Search");
//		LOGGER.info("NIC NO  -> " + nicNo);
//		LOGGER.info("Passport NO  -> " + passportNo);
//		LOGGER.info("Country   -> " + country);
        BlacklistSearchCriteriaVO blacklistSearchCriteriaVO = new BlacklistSearchCriteriaVO();
        blacklistSearchCriteriaVO.setNicNo(nicNo);
        blacklistSearchCriteriaVO.setNewNicNo(newNicNo);
        blacklistSearchCriteriaVO.setPassportNo(passportNo);
        blacklistSearchCriteriaVO.setName(name);
//		applicationDetailsReportSearchCriteriaVO.setCountry(country);
        blacklistSearchCriteriaVO.updateBlacklistSearchCriteriaVO();
        applicationList = (BlackListReportBusiness.getInstance().getAllocationList(blacklistSearchCriteriaVO, getUserIdFromSession()));

        if ((applicationList == null || applicationList.isEmpty())) {
            addActionError("No Records Found");
            return ERROR;
        }

        session.put("NIC_NO", nicNo);
        session.put("NEW_NIC_NO", newNicNo);
        session.put("PASSPORT_NO", passportNo);
        session.put("NAME", name);

        if (!(this.nicNo == null)) {
            session.put("NIC_NO", nicNo);
        }
        if (!(this.newNicNo == null)) {
            session.put("NEW_NIC_NO", newNicNo);
        }

        if (!(this.passportNo == null)) {
            session.put("PASSPORT_NO", passportNo);
        }

        if (!(this.name == null)) {
            session.put("NAME", name);
        }

//		LOGGER.info("NIC_NO - "+session.get("NIC_NO"));
//		LOGGER.info("PASSPORT_NO - "+session.get("PASSPORT_NO"));


        return SUCCESS;
    }

    public String printBlacklistReport() {
        try {
            BlacklistSearchCriteriaVO blacklistSearchCriteriaVO = new BlacklistSearchCriteriaVO();

            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//			LOGGER.info("fromDate1 - "+session.get("FROM_DATE"));
//			LOGGER.info("toDate1 - "+session.get("TO_DATE"));
//			nicNo =session.get("NIC_NO").toString();
//			passportNo =session.get("PASSPORT_NO").toString();

            if (!(session.get("NIC_NO") == null)) {
                nicNo = session.get("NIC_NO").toString();
            }

            if (!(session.get("NEW_NIC_NO") == null)) {
                newNicNo = session.get("NEW_NIC_NO").toString();
            }
            if (!(session.get("PASSPORT_NO") == null)) {
                passportNo = session.get("PASSPORT_NO").toString();
            }

            if (!(session.get("NAME") == null)) {
                name = session.get("NAME").toString();
            }

//			LOGGER.info("nicNo - "+nicNo);
//			LOGGER.info("passportNo - "+passportNo);

//			rptType=(Integer) session.get("rptType");
//			LOGGER.info("rptType - "+rptType);


            blacklistSearchCriteriaVO.setNicNo(nicNo);
            blacklistSearchCriteriaVO.setNewNicNo(newNicNo);
            blacklistSearchCriteriaVO.setPassportNo(passportNo);
            blacklistSearchCriteriaVO.setName(name);
//			applicationDetailsReportSearchCriteriaVO.setCountry(country);
            blacklistSearchCriteriaVO.updateBlacklistSearchCriteriaVO();
            applicationList = (BlackListReportBusiness.getInstance().getAllocationList(blacklistSearchCriteriaVO, getUserIdFromSession()));

//			if((applicationList==null || applicationList.isEmpty())){
//				addActionError("No Records Found");
//				return ERROR;
//			}

            BlacklistReportPrintVO blacklistReportPrintVO = constructApplicationDetailPrintVO(applicationList);

            fileName = PoliceConstant.BLACKLIST_REPORT_FILE_NAME;

            List<BlacklistReportPrintVO> dtoList = new ArrayList<BlacklistReportPrintVO>();
            dtoList.add(blacklistReportPrintVO);

            JRDataSource reportData = new JRBeanCollectionDataSource(dtoList);

            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            if (StringUtils.isEmpty(nicNo)) {
                params.put("nic", "All");
            } else {
                params.put("nic", nicNo);
            }
            if (StringUtils.isEmpty(newNicNo)) {
                params.put("newNicNo", "All");
            } else {
                params.put("newNicNo", newNicNo);
            }
            if (StringUtils.isEmpty(passportNo)) {
                params.put("passport", "All");
            } else {
                params.put("passport", passportNo);
            }
            if (StringUtils.isEmpty(name)) {
                params.put("name", "All");
            } else {
                params.put("name", name);
            }


            if (!(getUserFromSession() == null)) {
                params.put("REPORT_USER", getUserFromSession().getFullName());
            } else {
                params.put("REPORT_USER", StringUtils.EMPTY);
            }
            params.put("REPORT_DATE", Calendar.getInstance().getTime());
            //params.put("toDay", new Date());

            // 2.  Retrieve template
            InputStream reportStreamSubdailyTransaction = this.getClass().getResourceAsStream(PoliceConstant.BLACKLIST_REPORT_SUB_REPORT);
            // 3. Convert template to JasperDesign
            JasperDesign jdSubDailyTransaction = JRXmlLoader.load(reportStreamSubdailyTransaction);
            JasperReport jasperSubReportDailyTransaction = JasperCompileManager.compileReport(jdSubDailyTransaction);
            params.put("BLACKLIST_REPORT_SUB_REPORT", jasperSubReportDailyTransaction);

            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.BLACKLIST_REPORT);

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);

            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            fileName = ReportFileExportUtil.getInstance().export(PoliceConstant.FILE_TYPE_PDF, jp, baos, fileName);
        } catch (BusinessException e) {
            e.printStackTrace();
            LOGGER.info("BlacklistReport --> BusinessException");
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("BlacklistReport --> Exception");
            return ERROR;
        }
        return SUCCESS;
    }

    public String printBlacklistReportExcel() {
        try {
            BlacklistSearchCriteriaVO blacklistSearchCriteriaVO = new BlacklistSearchCriteriaVO();

            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//			LOGGER.info("fromDate1 - "+session.get("FROM_DATE"));
//			LOGGER.info("toDate1 - "+session.get("TO_DATE"));
//			nicNo =session.get("NIC_NO").toString();
//			passportNo =session.get("PASSPORT_NO").toString();

            if (!(session.get("NIC_NO") == null)) {
                nicNo = session.get("NIC_NO").toString();
            }

            if (!(session.get("PASSPORT_NO") == null)) {
                passportNo = session.get("PASSPORT_NO").toString();
            }

            if (!(session.get("NAME") == null)) {
                name = session.get("NAME").toString();
            }

//			LOGGER.info("nicNo - "+nicNo);
//			LOGGER.info("passportNo - "+passportNo);

//			rptType=(Integer) session.get("rptType");
//			LOGGER.info("rptType - "+rptType);


            blacklistSearchCriteriaVO.setNicNo(nicNo);
            blacklistSearchCriteriaVO.setNewNicNo(newNicNo);
            blacklistSearchCriteriaVO.setPassportNo(passportNo);
            blacklistSearchCriteriaVO.setName(name);
//			applicationDetailsReportSearchCriteriaVO.setCountry(country);
            blacklistSearchCriteriaVO.updateBlacklistSearchCriteriaVO();
            applicationList = (BlackListReportBusiness.getInstance().getAllocationList(blacklistSearchCriteriaVO, getUserIdFromSession()));

//			if((applicationList==null || applicationList.isEmpty())){
//				addActionError("No Records Found");
//				return ERROR;
//			}

            BlacklistReportPrintVO blacklistReportPrintVO = constructApplicationDetailPrintVO(applicationList);

            fileName = PoliceConstant.BLACKLIST_REPORT_FILE_NAME;

            List<BlacklistReportPrintVO> dtoList = new ArrayList<BlacklistReportPrintVO>();
            dtoList.add(blacklistReportPrintVO);

            JRDataSource reportData = new JRBeanCollectionDataSource(dtoList);

            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            if (StringUtils.isEmpty(nicNo)) {
                params.put("nic", "All");
            } else {
                params.put("nic", nicNo);
            }
            if (StringUtils.isEmpty(newNicNo)) {
                params.put("newNicNo", "All");
            } else {
                params.put("newNicNo", newNicNo);
            }

            if (StringUtils.isEmpty(currentNic)) {
                params.put("currentNic", "All");
            } else {
                params.put("currentNic", currentNic );
            }

            if (StringUtils.isEmpty(passportNo)) {
                params.put("passport", "All");
            } else {
                params.put("passport", passportNo);
            }
            if (StringUtils.isEmpty(name)) {
                params.put("name", "All");
            } else {
                params.put("name", name);
            }

            if (!(getUserFromSession() == null)) {
                params.put("REPORT_USER", getUserFromSession().getFullName());
            } else {
                params.put("REPORT_USER", StringUtils.EMPTY);
            }
            params.put("REPORT_DATE", Calendar.getInstance().getTime());
            //params.put("toDay", new Date());

            // 2.  Retrieve template
            InputStream reportStreamSubdailyTransaction = this.getClass().getResourceAsStream(PoliceConstant.BLACKLIST_REPORT_SUB_REPORT);
            // 3. Convert template to JasperDesign
            JasperDesign jdSubDailyTransaction = JRXmlLoader.load(reportStreamSubdailyTransaction);
            JasperReport jasperSubReportDailyTransaction = JasperCompileManager.compileReport(jdSubDailyTransaction);
            params.put("BLACKLIST_REPORT_SUB_REPORT", jasperSubReportDailyTransaction);

            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.BLACKLIST_REPORT);

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
            LOGGER.info("BlacklistReport --> BusinessException");
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("BlacklistReport --> Exception");
            return ERROR;
        }
        return SUCCESS;
    }

    private BlacklistReportPrintVO constructApplicationDetailPrintVO(List<BlackListVO> blacklistVOs) throws BusinessException {
        BlacklistReportPrintVO blacklistPrintVO = new BlacklistReportPrintVO();
        blacklistPrintVO.setBlacklistVOs(blacklistVOs);
        blacklistPrintVO.setFileName(PoliceConstant.BLACKLIST_REPORT_FILE_NAME);
        return blacklistPrintVO;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;

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


    public List<BlackListVO> getApplicationList() {
        return applicationList;
    }


    public void setApplicationList(List<BlackListVO> applicationList) {
        this.applicationList = applicationList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getCurrentNic() {
        return currentNic;
    }

    public void setCurrentNic(String currentNic) {
        this.currentNic = currentNic;
    }
}
