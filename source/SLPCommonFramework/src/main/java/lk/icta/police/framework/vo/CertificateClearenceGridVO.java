package lk.icta.police.framework.vo;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.utility.CommonUtil;

import org.apache.commons.lang3.StringUtils;

public class CertificateClearenceGridVO implements Serializable, Comparable<CertificateClearenceGridVO> {

  private static final long serialVersionUID = 1L;

  private long applicationId;
  private String referenceNo;
  private String nic;
  private String newNic;
  private String passport;
  private String applicantName;
  private String applicationClearanceStatus;
  private String applicationReviewStatus;
  private String updatedDateTime;
  private Date updatedDateTimeObj;
  private String polStatus;
  private String cidStatus;
  private String tidStatus;
  private String sisStatus;
  private String nicStatus;
  private String imiStatus;
  private String coApproved;
  private String oicApproved;
  private String aspApproved;
  private String dhaApproved;
  private String digApproved;
  private String certificateSerialNo;
  private String applicationQueue;
  private String regPostNo;

  private String certificatePostalAddress;
  private int hasCurrentUserLocked;


  private int canEditClearence;
  private int canOicApprove;
  private int canAspApprove;
  private int canDigApprove;
  private int canDHASign;
  private int canEnterCertificateSerial;
  private int canEditRegPost;
  private int canPrintAddress;
  private int canPrintCertificate;
  private int canEditNicDetails;

  private String constructedComment;

  private String nicNameIssueEmail;
  private String imiNameIssueEmail;

  private int commentAvailable;

  private int versionId;

  private String polStatusText;
  private String cidStatusText;
  private String tidStatusText;
  private String sisStatusText;
  private String nicStatusText;
  private String imiStatusText;

  private String passportAttachPath;
  private String pptFileType;
  private String passportBackAttachPath;
  private String pptBackFileType;

  private String nicAttachPath;
  private String newNicAttachPath;
  private String nicFileType;
  private String nicBackAttachPath;
  private String newNicBackAttachPath;
  private String nicBackFileType;

  private String birthCertificatePath;
  private String birthCertificateFileType;
  private String birthCertificateBackPath;
  private String birthCertificateFileBackType;

  private String letterOfReferencePath;
  private String letterOfReferenceFileType;

  private String certificateType;
  private String certificateLetterContent;
  private long certificateLetterContentCommentId;

  Map<String, PoliceEnumConstant.ApplicationClearenceStatus> clearenceStatusMap;

  private int notificationEmailSentStatus;

  private int hasAnyBlacklitedRecord;
  private int hasAnyAdverseRecord;
  private int hasGreenChanneled;

  private int certificatePrintedStatus;

  private String currentNicNo;
  private String affidavitAttachPath;

  public CertificateClearenceGridVO() {
    super();
  }


  public CertificateClearenceGridVO(ApplicationVO applicationVO, int currentUserType) {
    super();

    if (!(applicationVO == null)) {

      boolean isAppInGreenChannel = false;

      applicationVO.constructcertificatepostalAddress(true);
      applicationVO.allocateFileTypes();

      this.certificatePrintedStatus = applicationVO.getCertificatePrintedStatus();

      if (applicationVO.getHasBlackListed() > 0) {
        this.hasAnyBlacklitedRecord = 1;
      }

      if (applicationVO.getAnyAdverseAvailable() > 0) {
        this.hasAnyAdverseRecord = 1;
      }

      this.applicationId = applicationVO.getApplicationId();
      this.referenceNo = applicationVO.getReferenceNo();
      this.nic = applicationVO.getNic();
      this.newNic = applicationVO.getNewNic();

      this.currentNicNo = applicationVO.getCurrentNicNo();

      this.passport = applicationVO.getPassport();
      this.applicantName = applicationVO.getApplicantNameAsNic();
      this.applicationClearanceStatus = applicationVO.getApplicationClearanceStatus();
      this.applicationReviewStatus = applicationVO.getApplicationReviewStatus();

      if (!(applicationVO.getUpdatedDateTime() == null)) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.updatedDateTime = format.format(applicationVO.getUpdatedDateTime());
      }
      this.updatedDateTimeObj = applicationVO.getUpdatedDateTime();

      this.polStatus = applicationVO.getPolStatus();
      this.cidStatus = applicationVO.getCidStatus();
      this.tidStatus = applicationVO.getTidStatus();
      this.sisStatus = applicationVO.getSisStatus();
      this.nicStatus = applicationVO.getNicStatus();
      this.imiStatus = applicationVO.getImiStatus();

      this.notificationEmailSentStatus = applicationVO.getNotificationEmailSentStatus();

      PoliceEnumConstant.ApprovalStatus polApproval = PoliceEnumConstant.ApprovalStatus.fromCode(this.polStatus);
      if (!(polApproval == null)) {
        this.polStatusText = polApproval.getDisplayName();
      }

      PoliceEnumConstant.ApprovalStatus cidApproval = PoliceEnumConstant.ApprovalStatus.fromCode(this.cidStatus);
      if (!(cidApproval == null)) {
        this.cidStatusText = cidApproval.getDisplayName();
      }

      PoliceEnumConstant.ApprovalStatus tidApproval = PoliceEnumConstant.ApprovalStatus.fromCode(this.tidStatus);
      if (!(tidApproval == null)) {
        this.tidStatusText = tidApproval.getDisplayName();
      }

      PoliceEnumConstant.ApprovalStatus sisApproval = PoliceEnumConstant.ApprovalStatus.fromCode(this.sisStatus);
      if (!(sisApproval == null)) {
        this.sisStatusText = sisApproval.getDisplayName();
      }

      PoliceEnumConstant.ApprovalStatus nicApproval = PoliceEnumConstant.ApprovalStatus.fromCode(this.nicStatus);
      if (!(nicApproval == null)) {
        this.nicStatusText = nicApproval.getDisplayName();
      }

      PoliceEnumConstant.ApprovalStatus imiApproval = PoliceEnumConstant.ApprovalStatus.fromCode(this.imiStatus);
      if (!(imiApproval == null)) {
        this.imiStatusText = imiApproval.getDisplayName();
      }

      this.coApproved = applicationVO.getCoApproved();
      this.oicApproved = applicationVO.getOicApproved();
      this.aspApproved = applicationVO.getAspApproved();
      this.dhaApproved = applicationVO.getDhaApproved();
      this.digApproved = applicationVO.getDigApproved();

      this.certificateSerialNo = applicationVO.getCertificateSerialNo();
      this.applicationQueue = applicationVO.getApplicationQueue();
      this.regPostNo = applicationVO.getRegPostNo();

      this.certificateType = applicationVO.getCertificateType();

      this.certificatePostalAddress = applicationVO.getCertificatePostalAddress();
      this.hasCurrentUserLocked = applicationVO.getHasCurrentUserLocked();


      this.versionId = applicationVO.getVersionId();


      this.passportAttachPath = applicationVO.getPassportAttachPath();
      this.pptFileType = applicationVO.getPptFileType();
      this.passportBackAttachPath = applicationVO.getPassportBackAttachPath();
      this.pptBackFileType = applicationVO.getPptBackFileType();

      this.nicAttachPath = applicationVO.getNicAttachPath();
      this.newNicAttachPath = applicationVO.getNewNicAttachPath();
      this.nicFileType = applicationVO.getNicFileType();
      this.nicBackAttachPath = applicationVO.getNicBackAttachPath();
      this.newNicBackAttachPath = applicationVO.getNewNicBackAttachPath();
      this.nicBackFileType = applicationVO.getNicBackFileType();


      this.birthCertificatePath = applicationVO.getBirthCertificatePath();
      this.birthCertificateFileType = applicationVO.getBirthCertificateFileType();
      this.birthCertificateBackPath = applicationVO.getBirthCertificateBackPath();
      this.birthCertificateFileBackType = applicationVO.getBirthCertificateFileBackType();

      this.letterOfReferencePath = applicationVO.getLetterOfReferencePath();
      this.letterOfReferenceFileType = applicationVO.getLetterOfReferenceFileType();

      this.affidavitAttachPath = applicationVO.getAffidavitAttachPath();

      this.clearenceStatusMap = new LinkedHashMap<String, PoliceEnumConstant.ApplicationClearenceStatus>();
      PoliceEnumConstant.ApplicationClearenceStatus clearenceStatus = PoliceEnumConstant.ApplicationClearenceStatus.PN;

      if (StringUtils.isNotEmpty(applicationVO.getApplicationClearanceStatus())) {
        clearenceStatus =
            PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
        if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
            PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
          isAppInGreenChannel = true;
        }
      }

      boolean hasAllExternalCleared = checkIfAllExternalClearanceGiven(applicationVO);
      boolean hasAtLeastOneExternalCleared = checkIfAtLeastOneExternalCleared(applicationVO);

      // default value - to show
      this.clearenceStatusMap.put(clearenceStatus.toString(), clearenceStatus);

      boolean hasNameIssue = false;

      PoliceEnumConstant.UserType userTypeEnum = PoliceEnumConstant.UserType.fromCode(currentUserType);
      switch (userTypeEnum) {
        case CN:
          this.canPrintCertificate = 0;
          this.canOicApprove = 0;
          this.canAspApprove = 0;
          this.canDHASign = 0;
          this.canEnterCertificateSerial = 0;
          this.canEditRegPost = 0;
          this.canPrintAddress = 0;
          this.canEditNicDetails = 0;

          if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
              || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
            hasNameIssue = true;
          }

          if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.PN.toString())) {
            this.canEditClearence = 1;

            if (hasAtLeastOneExternalCleared) {
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RC);
            }

            if (!(hasNameIssue)) {
              if (hasAllExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.IS);
              }
            } else {
              this.canEditNicDetails = 1;
            }
            // this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
            // PoliceEnumConstant.ApplicationClearenceStatus.RJ);
            if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
                || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
              this.nicNameIssueEmail = constructNicNameIssueEmail(applicationVO);
              this.imiNameIssueEmail = constructNicNameIssueEmail(applicationVO);
            }
          } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.NC.toString())) {
            this.canEditClearence = 1;
            if (hasAtLeastOneExternalCleared) {
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RC);
            }
            if ((!(hasNameIssue || StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                .equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.PN.toString())))) {
              if (hasAllExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.IS);
              }
            }
            // this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
            // PoliceEnumConstant.ApplicationClearenceStatus.RJ);

            if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
                || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
              this.nicNameIssueEmail = constructNicNameIssueEmail(applicationVO);
              this.imiNameIssueEmail = constructNicNameIssueEmail(applicationVO);
            }
          } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())) {
            this.canEditClearence = 1;
            if (hasAtLeastOneExternalCleared) {
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RC);
            }
            if (!(hasNameIssue)) {
              if (hasAllExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.IS);
              }
            } else {
              this.canEditNicDetails = 1;
            }
            // this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
            // PoliceEnumConstant.ApplicationClearenceStatus.RJ);

            if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
                || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
              this.nicNameIssueEmail = constructNicNameIssueEmail(applicationVO);
              this.imiNameIssueEmail = constructNicNameIssueEmail(applicationVO);
            }
          } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
            this.canEditClearence = 1;
            if (hasAtLeastOneExternalCleared) {
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RC);
            }
            if (!(hasNameIssue)) {
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS);
            } else {
              this.canEditNicDetails = 1;
            }
            // this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
            // PoliceEnumConstant.ApplicationClearenceStatus.RJ);
            if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
                || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
              this.nicNameIssueEmail = constructNicNameIssueEmail(applicationVO);
              this.imiNameIssueEmail = constructNicNameIssueEmail(applicationVO);
            }
          } else {
            // do not allow to edit clearnec
            this.canEditClearence = 0;
            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
              // allow to print certificate
              this.canPrintCertificate = 1;
            }
          }
          break;

        case OI:

          this.canEditClearence = 0;
          this.canOicApprove = 0;
          this.canAspApprove = 0;
          this.canDHASign = 0;
          this.canEnterCertificateSerial = 0;
          this.canEditRegPost = 0;
          this.canPrintAddress = 0;

          if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())
              || StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                  PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
            this.canPrintCertificate = 1;
          }


          if (!(applicationVO.getAnyAdverseAvailable() == 1)) {
            if (StringUtils.equals(applicationVO.getApplicationQueue(),
                PoliceEnumConstant.ApplicationQueue.OI.toString())
                || (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                    PoliceEnumConstant.ApplicationClearenceStatus.GC.toString()) && (StringUtils.equals(
                    applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                    .isEmpty(applicationVO.getOicApproved())))) {
              this.canOicApprove = 1;
              this.canPrintCertificate = 1;
            }

            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
              if (StringUtils.isEmpty(applicationVO.getAspApproved())
                  || StringUtils
                      .equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                this.canOicApprove = 1;
                this.canPrintCertificate = 1;
              }
            }

          } else {
            applicationVO.setAnyAdverseAvailable(1);
            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())
                && (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                    .isEmpty(applicationVO.getOicApproved()))) {
              this.canOicApprove = 1;
              this.canPrintCertificate = 1;
            }

            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.DC.toString())
                && (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                    .isEmpty(applicationVO.getOicApproved()))) {
              this.canOicApprove = 1;
              this.canPrintCertificate = 1;
            }

            if (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
              if ((StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                  .isEmpty(applicationVO.getOicApproved()))) {
                this.canOicApprove = 1;
                this.canPrintCertificate = 1;
              } else {
                if (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
                  this.canOicApprove = 1;
                  this.canPrintCertificate = 1;
                }
              }
            }

            if (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())
                && (applicationVO.getCertificatePrintedStatus() <= 0)) {
              this.canOicApprove = 1;
              this.canPrintCertificate = 1;
            }
          }


          break;
        case AS:

          this.canEditClearence = 0;
          this.canOicApprove = 0;
          this.canAspApprove = 0;
          this.canDHASign = 0;
          this.canEnterCertificateSerial = 0;
          this.canEditRegPost = 0;
          this.canPrintAddress = 0;

          if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())
              || StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                  PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
            this.canPrintCertificate = 1;
          }

          if (!(applicationVO.getAnyAdverseAvailable() == 1)) {
            if (StringUtils.isEmpty(applicationVO.getAspApproved())
                || StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
              if (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())
                  || isAppInGreenChannel) {
                this.canAspApprove = 1;
                this.canPrintCertificate = 1;
              }
            } else {
              if (StringUtils.isEmpty(applicationVO.getDhaApproved())
                  || StringUtils
                      .equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                this.canAspApprove = 1;
                this.canPrintCertificate = 1;
              }
            }
            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.PN.toString())) {
              this.canEditClearence = 1;
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.GC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.GC);
            }
            
            ///////////////////////////////////////
            
            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                    PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())) {
            	this.canAspApprove = 1;
            	this.canPrintCertificate = 1;
            }
            ///////////////////////////////////////

          } else {

            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())
                && (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                    .isEmpty(applicationVO.getAspApproved()))) {
              this.canAspApprove = 1;
              this.canPrintCertificate = 1;
            } else {
              if (StringUtils.isEmpty(applicationVO.getOicApproved())
                  || StringUtils
                      .equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                this.canAspApprove = 1;
                this.canPrintCertificate = 1;
              }
            }

            if ((StringUtils.isEmpty(applicationVO.getAspApproved()) || StringUtils.equals(
                applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()))
                && StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                    PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
              if (StringUtils.equals(applicationVO.getCertificateType(),
                  PoliceEnumConstant.CertificateType.LT.toString())) {
                if (StringUtils.equals(applicationVO.getDigApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
                  this.canAspApprove = 1;
                  this.canPrintCertificate = 1;
                }
              } else {
                this.canAspApprove = 1;
                this.canPrintCertificate = 1;
              }
            }

            if (StringUtils.isNotEmpty(applicationVO.getAspApproved())) {
              if (StringUtils.isEmpty(applicationVO.getOicApproved())
                  || StringUtils
                      .equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                if (StringUtils.equals(applicationVO.getCertificateType(),
                    PoliceEnumConstant.CertificateType.LT.toString())) {
                  if (StringUtils.equals(applicationVO.getDigApproved(),
                      PoliceEnumConstant.ApprovalStatus.AP.toString())) {
                    this.canAspApprove = 1;
                    this.canPrintCertificate = 1;
                  }
                } else {
                  this.canAspApprove = 1;
                  this.canPrintCertificate = 1;
                }
              }
            }

          }

          break;

        case DH:

          this.canEditClearence = 0;
          this.canOicApprove = 0;
          this.canAspApprove = 0;
          this.canDHASign = 0;
          this.canEnterCertificateSerial = 0;
          this.canEditRegPost = 0;
          this.canPrintAddress = 0;


          if (StringUtils.isEmpty(applicationVO.getDhaApproved())
              || StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
            if (StringUtils.equals(applicationVO.getApplicationQueue(),
                PoliceEnumConstant.ApplicationQueue.DH.toString())
                || isAppInGreenChannel) {
              this.canDHASign = 1;
              this.canPrintCertificate = 1;
            }
          }

          if (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())
              && (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString()) || StringUtils
                  .isEmpty(applicationVO.getDhaApproved()))) {
            this.canDHASign = 1;
            this.canPrintCertificate = 1;
          }


          if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
              PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())
              || StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                  PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
            this.canPrintCertificate = 1;
          }

          // Pending --> Issued/Rejected/Due Course/Blacklisted
          // Name Issue Cleared --> Issued/Rejected
          // Express Issue --> Issued/Rejected
          // Green Channel --> Issued/Rejected
          // Due Course -->Issued/Rejected/Blacklisted


          if (this.hasAnyAdverseRecord == 1) {
            if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.PN.toString())) {
              this.canEditClearence = 1;

              if (hasAtLeastOneExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.RC);
              }

              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.CP.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.CP);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.DC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.DC);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.BL.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.BL);

            } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.NC.toString())) {
              this.canEditClearence = 1;

              if (hasAtLeastOneExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.RC);
              }

              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.DC.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.DC);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.BL.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.BL);

            } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.DC.toString())) {
              this.canEditClearence = 1;

              if (hasAtLeastOneExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.RC);
              }

              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.CP.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.CP);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.BL.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.BL);

            } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
              this.canEditClearence = 1;

              if (hasAtLeastOneExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.RC);
              }

              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);

            } else if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())) {
              this.canEditClearence = 1;

              if (hasAtLeastOneExternalCleared) {
                this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RC.toString(),
                    PoliceEnumConstant.ApplicationClearenceStatus.RC);
              }

              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.IS.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS);
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);

            } else if (!(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString()))) {
              this.canEditClearence = 1;
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);
            }

          } else {

            if (!(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString()))) {
              this.canEditClearence = 1;
              this.clearenceStatusMap.put(PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString(),
                  PoliceEnumConstant.ApplicationClearenceStatus.RJ);
            }

            if (StringUtils.isEmpty(applicationVO.getRegPostNo())) {
              this.canDHASign = 1;
              this.canPrintCertificate = 1;
            }
          }

          break;

        case DI:

          this.canEditClearence = 0;
          this.canOicApprove = 0;
          this.canAspApprove = 0;
          this.canDHASign = 0;
          this.canEnterCertificateSerial = 0;
          this.canEditRegPost = 0;
          this.canPrintAddress = 0;
          this.canDigApprove = 0;

          if (StringUtils.isEmpty(applicationVO.getDigApproved())
              || StringUtils.equals(applicationVO.getDigApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
            if (StringUtils.equals(applicationVO.getApplicationQueue(),
                PoliceEnumConstant.ApplicationQueue.DI.toString())) {
              this.canDigApprove = 1;
            }
          } else {
            if (StringUtils
                .equals(applicationVO.getCertificateType(), PoliceEnumConstant.CertificateType.LT.toString())) {
              if (StringUtils.isEmpty(applicationVO.getAspApproved())
                  || StringUtils
                      .equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
                this.canDigApprove = 1;
              }
            }
          }

          break;

        case CA:

          this.canEditClearence = 0;
          this.canOicApprove = 0;
          this.canAspApprove = 0;
          this.canDHASign = 0;
          this.canEnterCertificateSerial = 0;
          this.canEditRegPost = 0;
          this.canPrintAddress = 0;
          this.canDigApprove = 0;
          this.canPrintCertificate = 0;

          if (StringUtils.equals(applicationVO.getOicApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
            if (StringUtils.equals(applicationVO.getApplicationQueue(),
                PoliceEnumConstant.ApplicationQueue.CA.toString())) {
              this.canPrintCertificate = 1;
            }
          }

          if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
              || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
            hasNameIssue = true;
          }

          if (hasNameIssue) {
            this.canEditNicDetails = 1;

            if (StringUtils.equals(this.nicStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())
                || StringUtils.equals(this.imiStatus, PoliceEnumConstant.ApprovalStatus.NI.toString())) {
              this.nicNameIssueEmail = constructNicNameIssueEmail(applicationVO);
              this.imiNameIssueEmail = constructNicNameIssueEmail(applicationVO);
            }
          }

          break;

        case PO:
          if (StringUtils.isEmpty(applicationVO.getRegPostNo())) {
            if (StringUtils.equals(applicationVO.getApplicationQueue(),
                PoliceEnumConstant.ApplicationQueue.PO.toString())
                || isAppInGreenChannel) {
              this.canEditRegPost = 1;
            }

            if (this.hasAnyAdverseRecord == 1) {
              if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
                  PoliceEnumConstant.ApplicationClearenceStatus.IS.toString())) {
                this.canEditRegPost = 1;
              }
            } else {
              if (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
                this.canEditRegPost = 1;
              }
            }
          }
          this.canPrintAddress = 1;
          break;
        default:
          System.out.println("NEW USER TYPE :" + userTypeEnum);
          break;
      }

      this.hasGreenChanneled = 0;
      if (StringUtils.isNotEmpty(applicationVO.getRecommendedOfficerName())) {
        this.hasGreenChanneled = 1;
      }

    }


  }

  private boolean checkIfAtLeastOneExternalCleared(ApplicationVO applicationVO) {
    boolean hasAtLeastOneExternalCleared = false;
    if (!(StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
      hasAtLeastOneExternalCleared = true;
    }
    if (!(StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
      hasAtLeastOneExternalCleared = true;
    }
    if (!(StringUtils.equals(applicationVO.getSisStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
      hasAtLeastOneExternalCleared = true;
    }
    if (!(StringUtils.equals(applicationVO.getTidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
      hasAtLeastOneExternalCleared = true;
    }
    if (!(StringUtils.equals(applicationVO.getCidStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
      hasAtLeastOneExternalCleared = true;
    }
    if (!(StringUtils.equals(applicationVO.getPolStatus(), PoliceEnumConstant.ApprovalStatus.PN.toString()))) {
      hasAtLeastOneExternalCleared = true;
    }
    return hasAtLeastOneExternalCleared;
  }


  private boolean checkIfAllExternalClearanceGiven(ApplicationVO applicationVO) {
    boolean hasAllExternalCleared = true;
    if (!(StringUtils.equals(applicationVO.getNicStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getImiStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getSisStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getTidStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getCidStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    if (!(StringUtils.equals(applicationVO.getPolStatus(), PoliceEnumConstant.ApprovalStatus.AP.toString()))) {
      hasAllExternalCleared = false;
    }
    return hasAllExternalCleared;
  }


  private String constructNicNameIssueEmail(ApplicationVO applicationVO) {
    String message = CommonUtil.getValueFromFile("mail", "police.mail.content.update.in.nic.name");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Object[] arguments =
        {dateFormat.format(Calendar.getInstance().getTime()), applicationVO.getReferenceNo(),
            getConstructedAddress(applicationVO), applicationVO.getNic()

        };
    return MessageFormat.format(message, arguments);
  }


  private String getConstructedAddress(ApplicationVO applicationVO) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressLineOne())) {
      sb.append(applicationVO.getCertificatePostAddressLineOne()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressLineTwo())) {
      sb.append(applicationVO.getCertificatePostAddressLineTwo()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressCity())) {
      sb.append(applicationVO.getCertificatePostAddressCity()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressState())) {
      sb.append(applicationVO.getCertificatePostAddressState()).append(", <br />");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressPostal())) {
      sb.append(applicationVO.getCertificatePostAddressPostal()).append(",<br /> ");
    }
    if (StringUtils.isNotEmpty(applicationVO.getCertificatePostAddressCountryName())) {
      sb.append(applicationVO.getCertificatePostAddressCountryName()).append(".<br /> ");
    }
    return sb.toString();
  }


  public Map<String, Object> toBasicMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("applicationId", applicationId);
    map.put("referenceNo", referenceNo);
    map.put("nic", nic);
    map.put("newNic", newNic);
    map.put("passport", passport);
    map.put("applicantName", applicantName);
    map.put("passportAttachPath", passportAttachPath);
    map.put("nicAttachPath", nicAttachPath);
    map.put("affidavitAttachPath", affidavitAttachPath);
    map.put("newNicAttachPath", newNicAttachPath);
    map.put("applicationClearanceStatus", applicationClearanceStatus);
    map.put("applicationReviewStatus", applicationReviewStatus);
    map.put("updatedDateTime", updatedDateTime);
    map.put("polStatus", polStatus);
    map.put("cidStatus", cidStatus);
    map.put("tidStatus", tidStatus);
    map.put("sisStatus", sisStatus);
    map.put("nicStatus", nicStatus);
    map.put("imiStatus", imiStatus);
    map.put("coApproved", coApproved);
    map.put("oicApproved", oicApproved);
    map.put("aspApproved", aspApproved);
    map.put("dhaApproved", dhaApproved);
    map.put("digApproved", digApproved);
    map.put("certificateSerialNo", certificateSerialNo);
    map.put("applicationQueue", applicationQueue);
    map.put("regPostNo", regPostNo);

    map.put("certificatePostalAddress", certificatePostalAddress);
    map.put("hasCurrentUserLocked", hasCurrentUserLocked);
    map.put("nicFileType", nicFileType);
    map.put("pptFileType", pptFileType);

    map.put("canEditClearence", canEditClearence);
    map.put("canOicApprove", canOicApprove);
    map.put("canAspApprove", canAspApprove);
    map.put("canDHASign", canDHASign);
    map.put("canEnterCertificateSerial", canEnterCertificateSerial);
    map.put("canEditRegPost", canEditRegPost);
    map.put("canPrintAddress", canPrintAddress);
    map.put("canPrintCertificate", canPrintCertificate);

    map.put("clearenceStatusMap", clearenceStatusMap);

    map.put("versionId", versionId);


    return map;
  }

  @Override
  public String toString() {
    return this.toBasicMap().toString();
  }

  public long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
  }

  public String getReferenceNo() {
    return referenceNo;
  }

  public void setReferenceNo(String referenceNo) {
    this.referenceNo = referenceNo;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public String getPassportAttachPath() {
    return passportAttachPath;
  }

  public void setPassportAttachPath(String passportAttachPath) {
    this.passportAttachPath = passportAttachPath;
  }

  public String getNicAttachPath() {
    return nicAttachPath;
  }

  public void setNicAttachPath(String nicAttachPath) {
    this.nicAttachPath = nicAttachPath;
  }

  public String getApplicationClearanceStatus() {
    return applicationClearanceStatus;
  }

  public void setApplicationClearanceStatus(String applicationClearanceStatus) {
    this.applicationClearanceStatus = applicationClearanceStatus;
  }

  public String getApplicationReviewStatus() {
    return applicationReviewStatus;
  }

  public void setApplicationReviewStatus(String applicationReviewStatus) {
    this.applicationReviewStatus = applicationReviewStatus;
  }


  public String getUpdatedDateTime() {
    return updatedDateTime;
  }

  public void setUpdatedDateTime(String updatedDateTime) {
    this.updatedDateTime = updatedDateTime;
  }


  public String getPolStatus() {
    return polStatus;
  }

  public void setPolStatus(String polStatus) {
    this.polStatus = polStatus;
  }

  public String getCidStatus() {
    return cidStatus;
  }

  public void setCidStatus(String cidStatus) {
    this.cidStatus = cidStatus;
  }

  public String getTidStatus() {
    return tidStatus;
  }

  public void setTidStatus(String tidStatus) {
    this.tidStatus = tidStatus;
  }

  public String getSisStatus() {
    return sisStatus;
  }

  public void setSisStatus(String sisStatus) {
    this.sisStatus = sisStatus;
  }

  public String getNicStatus() {
    return nicStatus;
  }

  public void setNicStatus(String nicStatus) {
    this.nicStatus = nicStatus;
  }

  public String getImiStatus() {
    return imiStatus;
  }

  public void setImiStatus(String imiStatus) {
    this.imiStatus = imiStatus;
  }

  public String getCoApproved() {
    return coApproved;
  }

  public void setCoApproved(String coApproved) {
    this.coApproved = coApproved;
  }

  public String getOicApproved() {
    return oicApproved;
  }

  public void setOicApproved(String oicApproved) {
    this.oicApproved = oicApproved;
  }

  public String getAspApproved() {
    return aspApproved;
  }

  public void setAspApproved(String aspApproved) {
    this.aspApproved = aspApproved;
  }

  public String getDhaApproved() {
    return dhaApproved;
  }

  public void setDhaApproved(String dhaApproved) {
    this.dhaApproved = dhaApproved;
  }

  public String getCertificateSerialNo() {
    return certificateSerialNo;
  }

  public void setCertificateSerialNo(String certificateSerialNo) {
    this.certificateSerialNo = certificateSerialNo;
  }

  public String getApplicationQueue() {
    return applicationQueue;
  }

  public void setApplicationQueue(String applicationQueue) {
    this.applicationQueue = applicationQueue;
  }

  public String getRegPostNo() {
    return regPostNo;
  }

  public void setRegPostNo(String regPostNo) {
    this.regPostNo = regPostNo;
  }

  public String getCertificatePostalAddress() {
    return certificatePostalAddress;
  }

  public void setCertificatePostalAddress(String certificatePostalAddress) {
    this.certificatePostalAddress = certificatePostalAddress;
  }

  public int getHasCurrentUserLocked() {
    return hasCurrentUserLocked;
  }

  public void setHasCurrentUserLocked(int hasCurrentUserLocked) {
    this.hasCurrentUserLocked = hasCurrentUserLocked;
  }

  public String getNicFileType() {
    return nicFileType;
  }

  public void setNicFileType(String nicFileType) {
    this.nicFileType = nicFileType;
  }

  public String getPptFileType() {
    return pptFileType;
  }

  public void setPptFileType(String pptFileType) {
    this.pptFileType = pptFileType;
  }

  public int getCanEditClearence() {
    return canEditClearence;
  }

  public void setCanEditClearence(int canEditClearence) {
    this.canEditClearence = canEditClearence;
  }

  public int getCanOicApprove() {
    return canOicApprove;
  }

  public void setCanOicApprove(int canOicApprove) {
    this.canOicApprove = canOicApprove;
  }

  public int getCanAspApprove() {
    return canAspApprove;
  }

  public void setCanAspApprove(int canAspApprove) {
    this.canAspApprove = canAspApprove;
  }

  public int getCanDHASign() {
    return canDHASign;
  }

  public void setCanDHASign(int canDHASign) {
    this.canDHASign = canDHASign;
  }

  public int getCanEnterCertificateSerial() {
    return canEnterCertificateSerial;
  }

  public void setCanEnterCertificateSerial(int canEnterCertificateSerial) {
    this.canEnterCertificateSerial = canEnterCertificateSerial;
  }

  public int getCanEditRegPost() {
    return canEditRegPost;
  }

  public void setCanEditRegPost(int canEditRegPost) {
    this.canEditRegPost = canEditRegPost;
  }

  public int getCanPrintAddress() {
    return canPrintAddress;
  }

  public void setCanPrintAddress(int canPrintAddress) {
    this.canPrintAddress = canPrintAddress;
  }


  public Map<String, PoliceEnumConstant.ApplicationClearenceStatus> getClearenceStatusMap() {
    return clearenceStatusMap;
  }

  public void setClearenceStatusMap(Map<String, PoliceEnumConstant.ApplicationClearenceStatus> clearenceStatusMap) {
    this.clearenceStatusMap = clearenceStatusMap;
  }

  public int getCanPrintCertificate() {
    return canPrintCertificate;
  }

  public void setCanPrintCertificate(int canPrintCertificate) {
    this.canPrintCertificate = canPrintCertificate;
  }

  public String getConstructedComment() {
    return constructedComment;
  }

  public void setConstructedComment(String constructedComment) {
    this.constructedComment = constructedComment;
  }

  public int getCommentAvailable() {
    return commentAvailable;
  }

  public void setCommentAvailable(int commentAvailable) {
    this.commentAvailable = commentAvailable;
  }

  public int getVersionId() {
    return versionId;
  }

  public void setVersionId(int versionId) {
    this.versionId = versionId;
  }

  public String getBirthCertificatePath() {
    return birthCertificatePath;
  }

  public void setBirthCertificatePath(String birthCertificatePath) {
    this.birthCertificatePath = birthCertificatePath;
  }

  public String getLetterOfReferencePath() {
    return letterOfReferencePath;
  }

  public void setLetterOfReferencePath(String letterOfReferencePath) {
    this.letterOfReferencePath = letterOfReferencePath;
  }

  public String getBirthCertificateFileType() {
    return birthCertificateFileType;
  }

  public void setBirthCertificateFileType(String birthCertificateFileType) {
    this.birthCertificateFileType = birthCertificateFileType;
  }

  public String getLetterOfReferenceFileType() {
    return letterOfReferenceFileType;
  }

  public void setLetterOfReferenceFileType(String letterOfReferenceFileType) {
    this.letterOfReferenceFileType = letterOfReferenceFileType;
  }

  public String getNicNameIssueEmail() {
    return nicNameIssueEmail;
  }

  public void setNicNameIssueEmail(String nicNameIssueEmail) {
    this.nicNameIssueEmail = nicNameIssueEmail;
  }

  public String getImiNameIssueEmail() {
    return imiNameIssueEmail;
  }

  public void setImiNameIssueEmail(String imiNameIssueEmail) {
    this.imiNameIssueEmail = imiNameIssueEmail;
  }

  public String getPolStatusText() {
    return polStatusText;
  }

  public void setPolStatusText(String polStatusText) {
    this.polStatusText = polStatusText;
  }

  public String getCidStatusText() {
    return cidStatusText;
  }

  public void setCidStatusText(String cidStatusText) {
    this.cidStatusText = cidStatusText;
  }

  public String getTidStatusText() {
    return tidStatusText;
  }

  public void setTidStatusText(String tidStatusText) {
    this.tidStatusText = tidStatusText;
  }

  public String getSisStatusText() {
    return sisStatusText;
  }

  public void setSisStatusText(String sisStatusText) {
    this.sisStatusText = sisStatusText;
  }

  public String getNicStatusText() {
    return nicStatusText;
  }

  public void setNicStatusText(String nicStatusText) {
    this.nicStatusText = nicStatusText;
  }

  public String getImiStatusText() {
    return imiStatusText;
  }

  public void setImiStatusText(String imiStatusText) {
    this.imiStatusText = imiStatusText;
  }

  public int getCanEditNicDetails() {
    return canEditNicDetails;
  }

  public void setCanEditNicDetails(int canEditNicDetails) {
    this.canEditNicDetails = canEditNicDetails;
  }

  public int getNotificationEmailSentStatus() {
    return notificationEmailSentStatus;
  }

  public void setNotificationEmailSentStatus(int notificationEmailSentStatus) {
    this.notificationEmailSentStatus = notificationEmailSentStatus;
  }

  public int getHasAnyBlacklitedRecord() {
    return hasAnyBlacklitedRecord;
  }

  public void setHasAnyBlacklitedRecord(int hasAnyBlacklitedRecord) {
    this.hasAnyBlacklitedRecord = hasAnyBlacklitedRecord;
  }

  public int getHasAnyAdverseRecord() {
    return hasAnyAdverseRecord;
  }

  public void setHasAnyAdverseRecord(int hasAnyAdverseRecord) {
    this.hasAnyAdverseRecord = hasAnyAdverseRecord;
  }

  public String getDigApproved() {
    return digApproved;
  }

  public void setDigApproved(String digApproved) {
    this.digApproved = digApproved;
  }

  public String getCertificateType() {
    return certificateType;
  }

  public void setCertificateType(String certificateType) {
    this.certificateType = certificateType;
  }

  public int getCanDigApprove() {
    return canDigApprove;
  }

  public void setCanDigApprove(int canDigApprove) {
    this.canDigApprove = canDigApprove;
  }


  public String getCertificateLetterContent() {
    return certificateLetterContent;
  }

  public void setCertificateLetterContent(String certificateLetterContent) {
    this.certificateLetterContent = certificateLetterContent;
  }

  public long getCertificateLetterContentCommentId() {
    return certificateLetterContentCommentId;
  }

  public void setCertificateLetterContentCommentId(long certificateLetterContentCommentId) {
    this.certificateLetterContentCommentId = certificateLetterContentCommentId;
  }

  public String getPassportBackAttachPath() {
    return passportBackAttachPath;
  }

  public void setPassportBackAttachPath(String passportBackAttachPath) {
    this.passportBackAttachPath = passportBackAttachPath;
  }

  public String getNicBackAttachPath() {
    return nicBackAttachPath;
  }

  public void setNicBackAttachPath(String nicBackAttachPath) {
    this.nicBackAttachPath = nicBackAttachPath;
  }

  public String getBirthCertificateBackPath() {
    return birthCertificateBackPath;
  }

  public void setBirthCertificateBackPath(String birthCertificateBackPath) {
    this.birthCertificateBackPath = birthCertificateBackPath;
  }

  public String getNicBackFileType() {
    return nicBackFileType;
  }

  public void setNicBackFileType(String nicBackFileType) {
    this.nicBackFileType = nicBackFileType;
  }

  public String getPptBackFileType() {
    return pptBackFileType;
  }

  public void setPptBackFileType(String pptBackFileType) {
    this.pptBackFileType = pptBackFileType;
  }

  public String getBirthCertificateFileBackType() {
    return birthCertificateFileBackType;
  }

  public void setBirthCertificateFileBackType(String birthCertificateFileBackType) {
    this.birthCertificateFileBackType = birthCertificateFileBackType;
  }

  public int getHasGreenChanneled() {
    return hasGreenChanneled;
  }

  public void setHasGreenChanneled(int hasGreenChanneled) {
    this.hasGreenChanneled = hasGreenChanneled;
  }


  public int getCertificatePrintedStatus() {
    return certificatePrintedStatus;
  }


  public void setCertificatePrintedStatus(int certificatePrintedStatus) {
    this.certificatePrintedStatus = certificatePrintedStatus;
  }

  public String getNewNic() {
    return newNic;
  }

  public void setNewNic(String newNic) {
    this.newNic = newNic;
  }

  public String getCurrentNicNo() {
    return currentNicNo;
  }

  public void setCurrentNicNo(String currentNicNo) {
    this.currentNicNo = currentNicNo;
  }

  public String getNewNicAttachPath() {
    return newNicAttachPath;
  }

  public void setNewNicAttachPath(String newNicAttachPath) {
    this.newNicAttachPath = newNicAttachPath;
  }

  public String getNewNicBackAttachPath() {
    return newNicBackAttachPath;
  }

  public void setNewNicBackAttachPath(String newNicBackAttachPath) {
    this.newNicBackAttachPath = newNicBackAttachPath;
  }

  public String getAffidavitAttachPath() {
    return affidavitAttachPath;
  }

  public void setAffidavitAttachPath(String affidavitAttachPath) {
    this.affidavitAttachPath = affidavitAttachPath;
  }


  public Date getUpdatedDateTimeObj() {
    return updatedDateTimeObj;
  }


  public void setUpdatedDateTimeObj(Date updatedDateTimeObj) {
    this.updatedDateTimeObj = updatedDateTimeObj;
  }


  @Override
  public int compareTo(CertificateClearenceGridVO o) {
    if (!(o == null || o.getUpdatedDateTimeObj() == null || this.getUpdatedDateTimeObj() == null)) {
      return o.getUpdatedDateTimeObj().compareTo(this.getUpdatedDateTimeObj());
    }
    return 0;
  }
}
