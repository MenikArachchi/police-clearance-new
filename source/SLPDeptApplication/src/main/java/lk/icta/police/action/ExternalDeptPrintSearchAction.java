package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.app.business.CommonUserBusiness;
import lk.icta.commonuser.framework.exception.BusinessException;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ExternalDeptPrintSearchBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ExternalDeptPrintSearchCriteriaVO;
import lk.icta.police.framework.vo.UserRoleDisplayVO;
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

public class ExternalDeptPrintSearchAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ExternalDeptPrintSearchAction.class);
    private Map<String, Object> session;

    private Date fromSentDate;
    private Date toSentDate;

    private Date fromReceivedDate;
    private Date toReceivedDate;

    private ExternalDeptPrintSearchCriteriaVO searchCriteriaVO;

    private List<ApplicationVO> applicationList;

    private int userRole;
    private UserRoleDisplayVO userRoleDisplayVO;

    private List<ApplicationVO> selectedList;

    private String fileName;

    public String viewPrintList() {
        initializeSearchCriteria(false);

//        applicationList =
//                ExternalDeptPrintSearchBusiness.getInstance().searchApplication(searchCriteriaVO, getUserRoleFromSession(),
//                        getUserLocationFromSession(), getUserIdFromSession());

        if (!(applicationList == null || applicationList.isEmpty())) {
            loadUserRoleData();
        }
        return SUCCESS;
    }

    public String searchPrintList() {
        initializeSearchCriteria(true);
        applicationList =
                ExternalDeptPrintSearchBusiness.getInstance().searchApplication(searchCriteriaVO, getUserRoleFromSession(),
                        getUserLocationFromSession(), getUserIdFromSession());
        if (!(applicationList == null || applicationList.isEmpty())) {
            loadUserRoleData();
        }
        return SUCCESS;
    }


    public String loadSelectedApplicationList() {
        String resultType = SUCCESS;
        if (!(selectedList == null || selectedList.isEmpty())) {
            initializeSearchCriteria(true);
            loadUserRoleData();
            try {
                Date reportDate = Calendar.getInstance().getTime();

                int i = 0;
                String newNicNo;
                for (ApplicationVO applicationVO : selectedList) {
                    i++;
                    applicationVO.setRowNumber(i);

                    newNicNo = applicationVO.getNewNic();
                    if (!(newNicNo == null || newNicNo.isEmpty())) {
                        applicationVO.setCurrentNicNo(newNicNo);
                    } else if ((newNicNo == null || newNicNo.isEmpty())&& applicationVO.getNic() != null) {
                        applicationVO.setCurrentNicNo(applicationVO.getNic());
                    }
                }


                // 1. Add report parameters
                JRDataSource reportData = new JRBeanCollectionDataSource(selectedList);
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

                PoliceEnumConstant.UserDepartment userRoleEnum =
                        PoliceEnumConstant.UserDepartment.fromCode(getUserRoleFromSession());
                String reportDept = null;
                if (!(userRoleEnum == null)) {
                    reportDept = userRoleEnum.getDisplayName();
                }

                params.put("REPORT_DATE", reportDate);
                params.put("REPORT_DEPT", reportDept);
                params.put("REPORT_USER", reportUser);
                params.put("TOTAL_RECORDS", selectedList.size());

                BufferedImage image = null;
                try {
                    image = ImageIO.read(getClass().getResource(PoliceConstant.POL_LOGO));
                    params.put("LOGO_IMAGE", image);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

        // 2. Retrieve template
        InputStream reportStream = null;
        int userRole = getUserRoleFromSession();
        if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()
            || userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
          LOGGER.info("userRole POL OR PHQ:");
          reportStream = this.getClass().getResourceAsStream(PoliceConstant.EXTERNAL_DEPARTMENT_PRINT_APP_LIST_POLICE);
        } else if(PoliceEnumConstant.UserDepartment.NIC.getCode() == userRole ){
            reportStream = this.getClass().getResourceAsStream(PoliceConstant.EXTERNAL_DEPARTMENT_PRINT_APP_NIC);
        }else if(PoliceEnumConstant.UserDepartment.IMI.getCode() == userRole){
            reportStream = this.getClass().getResourceAsStream(PoliceConstant.EXTERNAL_DEPARTMENT_PRINT_APP_IMI);
        }
        else {
          LOGGER.info("userRole NOT POL OR PHQ:");
          reportStream = this.getClass().getResourceAsStream(PoliceConstant.EXTERNAL_DEPARTMENT_PRINT_APP_LIST);
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

                fileName = "tempappprintfile_" + System.currentTimeMillis();
                try {
                    fileName = ReportFileExportUtil.getInstance().export(fileType, jp, baos, fileName);
                    if (StringUtils.isNotEmpty(fileName)) {
                        String status =
                                ExternalDeptPrintSearchBusiness.getInstance().updateApplicationPrintedStatusExtrenalDept(selectedList,
                                        getUserIdFromSession(), getUserNameFromSession(), getUserRoleFromSession());
                        if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                            addActionMessage("Clearance List was printed successfully!");
                            return SUCCESS;
                        } else {
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
        } else {
            addActionError("No application has been selected!");
        }
        return SUCCESS;
    }

    private void loadUserRoleData() {
        this.userRole = getUserRoleFromSession();
        this.userRoleDisplayVO = new UserRoleDisplayVO();

    }

    private void initializeSearchCriteria(boolean doAssignDate) {
        if (searchCriteriaVO == null) {
            searchCriteriaVO = new ExternalDeptPrintSearchCriteriaVO(null, null, null, null, null, null, null, null);
            searchCriteriaVO.setStartFrom(0);
            searchCriteriaVO.setClearanceStatus("");
            if (doAssignDate) {
                this.fromSentDate = Calendar.getInstance().getTime();
                this.toSentDate = Calendar.getInstance().getTime();
                this.fromReceivedDate = Calendar.getInstance().getTime();
                this.toReceivedDate = Calendar.getInstance().getTime();
            }
        }
        searchCriteriaVO.setLimit(PoliceConstant.UNLIMITED_RECORDS_LIMIT);
        searchCriteriaVO.setFromSentDate(fromSentDate);
        searchCriteriaVO.setToSentDate(toSentDate);
        searchCriteriaVO.setFromReceivedDate(fromReceivedDate);
        searchCriteriaVO.setToReceivedDate(toReceivedDate);
        searchCriteriaVO.updateSearchCriteriaVO(getUserRoleFromSession(), getUserLocationFromSession());
    }

    private String getUserNameFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getFullName();
        }
        return null;
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


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Map<String, Object> getSession() {
        return session;
    }


    public Date getFromSentDate() {
        return fromSentDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setFromSentDate(Date fromSentDate) {
        this.fromSentDate = fromSentDate;
    }

    public Date getToSentDate() {
        return toSentDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setToSentDate(Date toSentDate) {
        this.toSentDate = toSentDate;
    }

    public Date getFromReceivedDate() {
        return fromReceivedDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setFromReceivedDate(Date fromReceivedDate) {
        this.fromReceivedDate = fromReceivedDate;
    }

    public Date getToReceivedDate() {
        return toReceivedDate;
    }

    @TypeConversion(converter = "lk.icta.police.util.StringToDateConverter")
    public void setToReceivedDate(Date toReceivedDate) {
        this.toReceivedDate = toReceivedDate;
    }

    public ExternalDeptPrintSearchCriteriaVO getSearchCriteriaVO() {
        return searchCriteriaVO;
    }

    public void setSearchCriteriaVO(ExternalDeptPrintSearchCriteriaVO searchCriteriaVO) {
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
