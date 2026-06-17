package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.PoliceFormBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationStatusViewSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.PoliceFormVO;
import lk.icta.police.util.ReportFileExportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PoliceFormAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PoliceFormAction.class);
    private Map<String, Object> session;

    private Date fromDate;
    private Date toDate;

    private long aid;

    private ApplicationStatusViewSearchCriteriaVO searchCriteriaVO;

    private List<ApplicationVO> applicationList;

    private List<PoliceFormVO> policeFormVOs;

    private String fileName;

    public String searchPoliceForm() {
        int userRole = getUserRoleFromSession();
        LOGGER.info("userRole :" + userRole);
        if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode() || userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
            initializeSearchCriteria();
            applicationList = PoliceFormBusiness.getInstance().searchApplication(searchCriteriaVO, getUserRoleFromSession(), getUserLocationFromSession(), getUserIdFromSession());
            return SUCCESS;
        } else {
            addActionError("Sorry, you are not authorized to access this page!");
            return ERROR;
        }
    }

    public String printPoliceForm() {
        int userRole = getUserRoleFromSession();
        LOGGER.info("userRole :" + userRole);
        LOGGER.info("aid :" + aid);
        if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode() || userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
            if (!(aid <= 0)) {
                try {
                    policeFormVOs = PoliceFormBusiness.getInstance().getSelectedApplicationPoliceFormByApplicationId(aid, getUserRoleFromSession(), getUserLocationFromSession());

                    Date reportDate = Calendar.getInstance().getTime();
                    // 1. Add report parameters
                    JRDataSource reportData = new JRBeanCollectionDataSource(policeFormVOs);
                    // 1. Add report parameters
                    HashMap<String, Object> params = new HashMap<String, Object>();

                    String reportUser = getUserNameFromSession();

                    UserVO selectedUser = new UserVO();
                    selectedUser.setId(getUserIdFromSession());
                    UserVO user = null;
                    try {
                        user = CommonUserBusiness.getInstance().getUserDetails(selectedUser);
                    } catch (BusinessException e1) {
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
                    InputStream subReport = this.getClass().getResourceAsStream(PoliceConstant.POLICE_FORM_SUB_REPORT);
                    // 3. Convert template to JasperDesign
                    JasperDesign jdSubCid = JRXmlLoader.load(subReport);
                    JasperReport jasperSubCid = JasperCompileManager.compileReport(jdSubCid);
                    params.put("POLICE_FORM_ITEM_SUB_REPORT", jasperSubCid);

                    InputStream reportStream = null;
                    // 2.  Retrieve template
                    if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()) {
                        reportStream = this.getClass().getResourceAsStream(PoliceConstant.POLICE_POLICE_FORM_PRINT);
                    } else {
                        reportStream = this.getClass().getResourceAsStream(PoliceConstant.POLICE_FORM_PRINT);
                    }

                    // 3. Convert template to JasperDesign
                    JasperDesign jd = JRXmlLoader.load(reportStream);
                    // 4. Compile design to JasperReport
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    // 5. Create the JasperPrint object
                    // Make sure to pass the JasperReport, report parameters, and data source
                    JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);
                    // 6. Create an output byte stream where data will be written
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    String fileType = PoliceConstant.FILE_TYPE_PDF;

                    fileName = "temppoliceformintfile_" + System.currentTimeMillis();
                    try {
                        fileName = ReportFileExportUtil.getInstance().export(fileType, jp, baos, fileName);
                        if (StringUtils.isNotEmpty(fileName)) {
                            addActionMessage("Status report was generated successfully!");
                        } else {
                            addActionError("Could not generate the status report. Internal Server Error!");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (JRException e1) {
                    e1.printStackTrace();
                }
            } else {
                addActionError("Please select an application!");
            }
        } else {
            addActionError("Sorry, you are not authorized to access this page!");
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

    private void initializeSearchCriteria() {
        if (searchCriteriaVO == null) {
            searchCriteriaVO = new ApplicationStatusViewSearchCriteriaVO(null, null, null, fromDate, toDate);
            this.fromDate = Calendar.getInstance().getTime();
            this.toDate = Calendar.getInstance().getTime();
        }
        searchCriteriaVO.setFromDate(fromDate);
        searchCriteriaVO.setToDate(toDate);
        searchCriteriaVO.updateSearchCriteriaVO(getUserRoleFromSession(), getUserLocationFromSession());
    }


    private int getUserIdFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getId();
        }
        return 0;
    }


    private long getUserLocationFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getAssignedLocation();
        }
        return 0;
    }

    private int getUserRoleFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null || userVO.getDept() == null)) {
            return userVO.getDept().getId();
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


    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public List<ApplicationVO> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<ApplicationVO> applicationList) {
        this.applicationList = applicationList;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public Map<String, Object> getSession() {
        return session;
    }


    public ApplicationStatusViewSearchCriteriaVO getSearchCriteriaVO() {
        return searchCriteriaVO;
    }

    public void setSearchCriteriaVO(
            ApplicationStatusViewSearchCriteriaVO searchCriteriaVO) {
        this.searchCriteriaVO = searchCriteriaVO;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<PoliceFormVO> getPoliceFormVOs() {
        return policeFormVOs;
    }

    public void setPoliceFormVOs(List<PoliceFormVO> policeFormVOs) {
        this.policeFormVOs = policeFormVOs;
    }


}
