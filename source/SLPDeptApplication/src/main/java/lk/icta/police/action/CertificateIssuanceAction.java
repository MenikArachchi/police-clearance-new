package lk.icta.police.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import lk.icta.commonuser.framework.vo.UserVO;
import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.ApplicationStatusViewBusiness;
import lk.icta.police.business.CertificateIssuanceBusiness;
import lk.icta.police.business.SequenceNumberBusiness;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant.CertificateType;
import lk.icta.police.framework.constant.PoliceEnumConstant.UserDepartment;
import lk.icta.police.framework.vo.*;
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
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class CertificateIssuanceAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CertificateIssuanceAction.class);
    private Map<String, Object> session;

    private Date fromDate;
    private Date toDate;

    private long applicationId;

    private String recordLockstatus;

    private CommentTypeDisplayVO typeDisplayVO;

    private int userType;

    private String emailSentStatus;

    private String emailText;

    private File nicFile;
    private String nicFileExtension;
    private String fileType;
    private String nicFileName;

    private String lockedUserName;

    private Map<Integer, String> departmentList;
    private List<PoliceAreaVO> policeAreaVOs;


    private List<Integer> applicationIdList;
    private String fileName;
    private long policeAreaId;

    private List<ResendClearanceViewVO> clearanceViewVOList;
    private ResendClearanceViewVO clearanceViewVO;

    private int department;

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;
    private static final String PROP_FILE_NAME = "police";

    public String loadCommentList() {
        userType = getUserTypeFromSession();
        typeDisplayVO = CertificateIssuanceBusiness.getInstance().getCommentListForApplication(applicationId);
        return SUCCESS;
    }

    public String checkAndLockClearenceRecord() {
        if (!(applicationId == 0)) {
            Map<String, Object> map = CertificateIssuanceBusiness.getInstance().checkAndLockRecord(applicationId, getUserIdFromSession());

            recordLockstatus = (String) map.get("MESSAGE");
            if (StringUtils.equals(recordLockstatus, PoliceConstant.RECORD_IS_LOCKED_BY_ANOTHER_USER)) {
                int lockedOtherUserId = (Integer) map.get("LOCKED_USER");
                lockedUserName = ApplicationStatusViewBusiness.getInstance().getUserNameById(lockedOtherUserId);
            }

            if (StringUtils.equals(recordLockstatus, PoliceConstant.SUCCESS)) {
                addRecordLockedStatusToSession();
            }
        }
        return SUCCESS;
    }

    public String checkAndRemoveLockClearence() {
        if (!(applicationId == 0)) {
            recordLockstatus = CertificateIssuanceBusiness.getInstance().checkAndUnLockRecord(applicationId, getUserIdFromSession());
            if (StringUtils.equals(recordLockstatus, PoliceConstant.SUCCESS)) {
                removeRecordLockedStatusFromSession(applicationId);
            }
        }
        return SUCCESS;
    }

    public String loadResendingDepartments() {
        if (!(applicationId <= 0)) {
            try {
                this.departmentList = new HashMap<Integer, String>();
                List<UserDepartment> departments = CertificateIssuanceBusiness.getInstance().getResendingDepartmentsByApplicationId(applicationId);
                if (!(departments == null)) {
                    for (UserDepartment userDepartment : departments) {
                        departmentList.put(userDepartment.getCode(), userDepartment.getDisplayName());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    public String loadPoliceAreaListForApplication() {
        if (!(applicationId <= 0)) {
            try {
                this.policeAreaVOs = CertificateIssuanceBusiness.getInstance().getPoliceAreaVOsByApplicationIdAndType(applicationId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }


    public String loadCurrentCommentDetailsForPoliceArea() {
        if (!(applicationId <= 0 || policeAreaId <= 0)) {
            try {
                this.clearanceViewVOList = CertificateIssuanceBusiness.getInstance().getCurrentCommentDetailsForPoliceArea(applicationId, policeAreaId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    public String loadCurrentCommentDetailsForDepartment() {
        if (!(applicationId <= 0 || department <= 0)) {
            try {
                this.clearanceViewVO = CertificateIssuanceBusiness.getInstance().getCurrentCommentDetailsByDepartmentAndApplicationId(applicationId, department);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }


    public String updateEmailSentStatus() {
        if ((getUserTypeFromSession() == PoliceEnumConstant.UserType.CN.getCode()) || (getUserTypeFromSession() == PoliceEnumConstant.UserType.CA.getCode())) {
            if (!(applicationId == 0 || StringUtils.isEmpty(emailText))) {
                emailText = StringUtils.trim(emailText);
                emailSentStatus = CertificateIssuanceBusiness.getInstance().sendNotificationEmailToApplicant(applicationId, emailText, getUserIdFromSession(), getUserNameFromSession());
            } else {
                emailSentStatus = "PLEASE ENTER EMAIL TEXT";
            }
        } else {
            emailSentStatus = "You don't have permission to update NIC Revision!";
        }
        return SUCCESS;
    }


    public String printCertificate() {
        String resultType = ERROR;
        if (!(applicationId <= 0)) {
            try {

                Date reportDate = Calendar.getInstance().getTime();

                List<PrintClearenceCertificateVO> list = new ArrayList<PrintClearenceCertificateVO>();

                CertificateAuthPersonVO authPersonVO = null;

                ApplicationVO applicationVO = ApplicationBusiness.getInstance().getApplicationByApplicationId(applicationId);

                if (!(applicationVO == null)) {

                    if(applicationVO.getCurrentNicNo().equals("N/A")){
                        applicationVO.setNic(null);
                    }else {
                        applicationVO.setNic(applicationVO.getCurrentNicNo());
                    }

                    List<AddressVO> addressVOs = CertificateIssuanceBusiness.getInstance().getAddressVOsByApplicationIdAndType(applicationId, PoliceEnumConstant.AddressType.AC.toString(), null);
                    applicationVO.setApplicantCertificateAddresses(addressVOs);


                    authPersonVO = CertificateIssuanceBusiness.getInstance().getCurrentCertificateAuthPerson();
                    if (!(authPersonVO == null)) {

                        PoliceEnumConstant.CertificateType type = null;
                        String certificateType = applicationVO.getCertificateType();
                        if (StringUtils.isEmpty(certificateType)) {
                            type = CertificateType.CT;
                        } else {
                            type = PoliceEnumConstant.CertificateType.fromCode(certificateType);
                        }
                        boolean hasCerialNoGenerated = false;
                        try {
                            String certificateCerialNo = null;
                            if (StringUtils.equals(type.toString(), PoliceEnumConstant.CertificateType.CT.toString())) {
                            	PoliceEnumConstant.CommentType commentType = PoliceEnumConstant.CommentType.LTR;
                                CommentsVO commentsVO = CertificateIssuanceBusiness.getInstance().getCommentByCommentTypeAndApplicationId(applicationId, commentType.toString());
                                if (!(commentsVO == null)) {
                                    applicationVO.setLetterConent(commentsVO.getComment());
                                }
                            	if (StringUtils.isEmpty(applicationVO.getCertificateSerialNo())) {
                                    certificateCerialNo = SequenceNumberBusiness.getInstance().getNextReferenceNumberByNumberType(PoliceEnumConstant.SequenceNumbers.CERTIFICATE_CERIAL_NUMBER.getCode());
                                    hasCerialNoGenerated = true;
                                }
                            } else if (StringUtils.equals(type.toString(), PoliceEnumConstant.CertificateType.CL.toString())) {
                                PoliceEnumConstant.CommentType commentType = PoliceEnumConstant.CommentType.LTR;
                                CommentsVO commentsVO = CertificateIssuanceBusiness.getInstance().getCommentByCommentTypeAndApplicationId(applicationId, commentType.toString());
                                if (!(commentsVO == null)) {
                                    applicationVO.setLetterConent(commentsVO.getComment());
                                }
                                if (StringUtils.isEmpty(applicationVO.getCertificateSerialNo())) {
                                    certificateCerialNo = SequenceNumberBusiness.getInstance().getNextReferenceNumberByNumberType(PoliceEnumConstant.SequenceNumbers.CERTIFICATE_CERIAL_NUMBER.getCode());
                                    hasCerialNoGenerated = true;
                                }
                            } else {
                                PoliceEnumConstant.CommentType commentType = PoliceEnumConstant.CommentType.LTR;
                                CommentsVO commentsVO = CertificateIssuanceBusiness.getInstance().getCommentByCommentTypeAndApplicationId(applicationId, commentType.toString());
                                if (!(commentsVO == null)) {
                                    applicationVO.setLetterConent(commentsVO.getComment());
                                }
                                if (StringUtils.isEmpty(applicationVO.getCertificateSerialNo())) {
                                    certificateCerialNo = SequenceNumberBusiness.getInstance().getNextReferenceNumberByNumberType(PoliceEnumConstant.SequenceNumbers.LETTER_CERIAL_NUMBER.getCode());
                                    hasCerialNoGenerated = true;
                                }
                            }
                            if (hasCerialNoGenerated) {
                                applicationVO.setCertificateSerialNo(certificateCerialNo);
                                String status = ApplicationBusiness.getInstance().updateCerificateSerialNumber(applicationVO.getApplicationId(), certificateCerialNo);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //load the commissionerVO if available
//						CommissionerVO commissionerVO=null;
//						if(!(applicationVO.getHighCommisionReferenceId()<=0)){
//							commissionerVO=MasterFileBusiness.getInstance().getCommissionerById(applicationVO.getHighCommisionReferenceId());
//						}

//						if(commissionerVO==null){
                        //user has added a custom commissioner vo
                        CommissionerVO commissionerVO = new CommissionerVO();
                        commissionerVO.setCommissionEmbassyConsultantAddress(StringUtils.upperCase(applicationVO.getHighCommisionReferenceAddress()));
                        commissionerVO.setCommissionEmbassyConsultantName(StringUtils.upperCase(applicationVO.getHighCommisionReference()));
//						}else{
//							commissionerVO.setCommissionEmbassyConsultantName(StringUtils.upperCase(commissionerVO.getAddressee() + ", " + commissionerVO.getCommissionEmbassyConsultantName()));
//						}

                        list.add(new PrintClearenceCertificateVO(applicationVO, authPersonVO.getName(),
                                authPersonVO.getDesignation(), authPersonVO.getAddress(), commissionerVO));

                        // 1. Add report parameters
                        JRDataSource reportData = new JRBeanCollectionDataSource(list);

                        // 1. Add report parameters
                        HashMap<String, Object> params = new HashMap<String, Object>();

                        params.put("REPORT_DATE", reportDate);

                        params.put("YOUR_NO", applicationVO.getForiegnMinistryInvertNo());
                        params.put("MY_NO", applicationVO.getReferenceNo());


                        BufferedImage image = null;
                        try {
                            image = ImageIO.read(getClass().getResource(PoliceConstant.GOV_LOGO));
                            params.put("LOGO_IMAGE", image);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        // 2.  Retrieve template
                        InputStream reportStream = null;

                        switch (type) {
                            case CT:
                                reportStream = this.getClass().getResourceAsStream(PoliceConstant.POL_CLEARENCE_CERTIFICATE_PRINT);
                                break;
                            case CL:
                                reportStream = this.getClass().getResourceAsStream(PoliceConstant.POL_CLEARENCE_CERTIFICATE_WITH_CLAUSE_PRINT);
                                break;
                            case LT:
                                reportStream = this.getClass().getResourceAsStream(PoliceConstant.POL_CLEARENCE_LETTER_PRINT);
                                break;
                            default:
                                break;
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

                        fileName = "tempcertprintfile_" + System.currentTimeMillis();
                        try {
                            fileName = ReportFileExportUtil.getInstance().export(fileType, jp, baos, fileName);
                            if (StringUtils.isNotEmpty(fileName)) {
                                String status = CertificateIssuanceBusiness.getInstance().updateApplicationCertificatePrintedStatus(applicationVO, getUserIdFromSession(), getUserNameFromSession());
                                if (StringUtils.equals(status, PoliceConstant.SUCCESS)) {
                                    addActionMessage("Certificate was printed successfully!");
                                    return SUCCESS;
                                } else {
                                    addActionError("Could not update the certificate printed status. Internal Server Error!");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        addActionError("Certificate authorization Person is not defined yet!");
                    }
                } else {
                    addActionError("Application is not available, Please try again");
                }

            } catch (JRException e1) {
                e1.printStackTrace();
            }
            addActionError("Could not generate the file. Internal Server Error!");
        }
        addActionError("Please select the application to print the certificate!");
        return resultType;
    }


    private String generateUniqueName(String prefix) {
        String uniqueFolderName = prefix + System.currentTimeMillis();
        return uniqueFolderName;
    }

    private static void copyFileUsingFileChannel(File inputFile, File outputFile) throws IOException {
        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(inputFile).getChannel();
            destination = new FileOutputStream(outputFile).getChannel();
            ByteBuffer buf = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
            while ((source.read(buf)) != -1) {
                buf.flip();
                destination.write(buf);
                buf.clear();
            }
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private void addRecordLockedStatusToSession() {
        Map<Long, Integer> recordLockedMap = null;
        if (!(session.get(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP) == null)) {
            recordLockedMap = (Map<Long, Integer>) session.get(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP);
        } else {
            recordLockedMap = new LinkedHashMap<Long, Integer>();
        }
        recordLockedMap.put(applicationId, getUserIdFromSession());
        session.put(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP, recordLockedMap);
    }

    private void removeRecordLockedStatusFromSession(long unlockedApplicationId) {
        if (!(session.get(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP) == null)) {
            Map<Long, Integer> recordLockedMap = (Map<Long, Integer>) session.get(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP);
            if (!(recordLockedMap == null)) {
                recordLockedMap.remove(unlockedApplicationId);
                session.put(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP, recordLockedMap);
            }
        }
    }

    private int getUserTypeFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null || userVO.getUserType() == null)) {
            return userVO.getUserType().getId();
        }
        return 0;
    }


    private int getUserIdFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getId();
        }
        return 0;
    }

    private String getUserNameFromSession() {
        UserVO userVO = getUserFromSession();
        if (!(userVO == null)) {
            return userVO.getFullName();
        }
        return null;
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

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getRecordLockstatus() {
        return recordLockstatus;
    }

    public void setRecordLockstatus(String recordLockstatus) {
        this.recordLockstatus = recordLockstatus;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public CommentTypeDisplayVO getTypeDisplayVO() {
        return typeDisplayVO;
    }

    public void setTypeDisplayVO(CommentTypeDisplayVO typeDisplayVO) {
        this.typeDisplayVO = typeDisplayVO;
    }


    public String getEmailSentStatus() {
        return emailSentStatus;
    }

    public void setEmailSentStatus(String emailSentStatus) {
        this.emailSentStatus = emailSentStatus;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public File getNicFile() {
        return nicFile;
    }

    public void setNicFile(File nicFile) {
        this.nicFile = nicFile;
    }


    public String getNicFileExtension() {
        return nicFileExtension;
    }

    public void setNicFileExtension(String nicFileExtension) {
        this.nicFileExtension = nicFileExtension;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getNicFileName() {
        return nicFileName;
    }

    public void setNicFileName(String nicFileName) {
        this.nicFileName = nicFileName;
    }

    public List<Integer> getApplicationIdList() {
        return applicationIdList;
    }

    public void setApplicationIdList(List<Integer> applicationIdList) {
        this.applicationIdList = applicationIdList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLockedUserName() {
        return lockedUserName;
    }

    public void setLockedUserName(String lockedUserName) {
        this.lockedUserName = lockedUserName;
    }


    public Map<Integer, String> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(Map<Integer, String> departmentList) {
        this.departmentList = departmentList;
    }

    public List<PoliceAreaVO> getPoliceAreaVOs() {
        return policeAreaVOs;
    }

    public void setPoliceAreaVOs(List<PoliceAreaVO> policeAreaVOs) {
        this.policeAreaVOs = policeAreaVOs;
    }

    public long getPoliceAreaId() {
        return policeAreaId;
    }

    public void setPoliceAreaId(long policeAreaId) {
        this.policeAreaId = policeAreaId;
    }

    public List<ResendClearanceViewVO> getClearanceViewVOList() {
        return clearanceViewVOList;
    }

    public void setClearanceViewVOList(
            List<ResendClearanceViewVO> clearanceViewVOList) {
        this.clearanceViewVOList = clearanceViewVOList;
    }

    public ResendClearanceViewVO getClearanceViewVO() {
        return clearanceViewVO;
    }

    public void setClearanceViewVO(ResendClearanceViewVO clearanceViewVO) {
        this.clearanceViewVO = clearanceViewVO;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }


}
