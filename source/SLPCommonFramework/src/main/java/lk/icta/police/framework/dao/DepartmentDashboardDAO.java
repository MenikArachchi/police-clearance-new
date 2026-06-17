package lk.icta.police.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;

import org.apache.log4j.Logger;

public class DepartmentDashboardDAO {

  private static DepartmentDashboardDAO instance = null;
  private static final Logger LOGGER = Logger.getLogger(DepartmentDashboardDAO.class);

  public static DepartmentDashboardDAO getInstance() {
    synchronized (DepartmentDashboardDAO.class) {
      if (instance == null) {
        instance = new DepartmentDashboardDAO();
      }
      return instance;
    }
  }

  private DepartmentDashboardDAO() {

  }

  public int countApplicationsToBeVerified(Connection conn) {
    int countApplicationsToBeVerified = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_APPLICATION_TO_BE_VERIFIED_COUNT);
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_APPLICATION_TO_BE_VERIFIED_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationReviewStatus.NW.toString());

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countApplicationsToBeVerified = rst.getInt("NW_APPLICATION_COUNT");
        }
      }
      // LOGGER.info("NW_APPLICATION_COUNT --> " + countApplicationsToBeVerified);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }

    return countApplicationsToBeVerified;

  }

  public int countResubmissionPending(Connection conn) {
    int countResubmissionPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_RE_SUBMISSION_PENDING_COUNT);
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_RE_SUBMISSION_PENDING_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationReviewStatus.RC.toString());

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countResubmissionPending = rst.getInt("RC_APPLICATION_COUNT");
        }
      }
      // LOGGER.info("RC_APPLICATION_COUNT --> " + countResubmissionPending);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }

    return countResubmissionPending;

  }

  public int countRevisionUpdatesPending(Connection conn) {
    int countRevisionUpdatesPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_REVISION_UPDATES_PENDING_COUNT);
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_REVISION_UPDATES_PENDING_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationReviewStatus.RV.toString());

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countRevisionUpdatesPending = rst.getInt("RV_APPLICATION_COUNT");
        }
      }
      // LOGGER.info("RV_APPLICATION_COUNT --> " + countRevisionUpdatesPending);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }

    return countRevisionUpdatesPending;

  }

  public int countExternalClearancePending(Connection conn) {
    int countExternalClearancePending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_EXTERNAL_CLEARANCE_PENDING_COUNT);
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countExternalClearancePending = rst.getInt("EX_CLR_PEND_COUNT");
        }
      }
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countExternalClearancePending;
  }

  public int countExternalClearancePendingByDepartment(Connection conn, String department) {
    int countExternalClearancePending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_EXTERNAL_CLEARANCE_PENDING_COUNT_BY_DEPARTMENT);
      pstm.setString(1, department);
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countExternalClearancePending = rst.getInt("EX_CLR_PEND_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countExternalClearancePending;
  }

  public int countPoliceClearancePending(Connection conn) {
    int countPoliceClearancePending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_EXTERNAL_CLEARANCE_PENDING_COUNT_BY_DEPARTMENT);
      pstm.setString(1, PoliceEnumConstant.UserDepartment.PHQ.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countPoliceClearancePending = rst.getInt("EX_CLR_PEND_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }

    return countPoliceClearancePending;

  }

  public int countOICApprovalPending(Connection conn) {
    int countOICApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_OIC_APPROVAL_PENDING_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationQueue.OI.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countOICApprovalPending = rst.getInt("OIC_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countOICApprovalPending;
  }

  public int countOICApprovalPendingGreenChannal(Connection con) {
    int countOICApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY.SELECT_OIC_APPROVAL_PENDING_COUNT_GREEN_CHANNAL);
      pstm.setString(1, PoliceEnumConstant.ApplicationQueue.OI.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countOICApprovalPending = rst.getInt("OIC_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countOICApprovalPending;
  }

  public int countASPApprovalPending(Connection conn) {
    int countASPApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ASP_APPROVAL_PENDING_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationQueue.AS.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countASPApprovalPending = rst.getInt("ASP_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countASPApprovalPending;
  }

  public int countASPApprovalPendingGreenChannal(Connection conn) {
    int countASPApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_ASP_APPROVAL_PENDING_COUNT_GREEN_CHANNAL);
      pstm.setString(1, PoliceEnumConstant.ApplicationQueue.AS.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countASPApprovalPending = rst.getInt("ASP_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countASPApprovalPending;
  }

  public int countCertificatePrintingPending(Connection conn) {
    int countCertificatePrintingPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_CERTIFICATE_PRINTING_PENDING_COUNT);
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_CERTIFICATE_PRINTING_PENDING_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationClearenceStatus.IS.toString());
      pstm.setString(2, PoliceEnumConstant.ApplicationClearenceStatus.GC.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countCertificatePrintingPending = rst.getInt("CERTIFICATE_PRINTING_PENDING_COUNT");
        }
      }
      // LOGGER.info("CERTIFICATE_PRINTING_PENDING_COUNT --> " + countCertificatePrintingPending);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }

    return countCertificatePrintingPending;

  }

  public int countCertificateToBePosted(Connection conn) {
    int countCertificateToBePosted = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // LOGGER.info("QUERY -> " + DBConstant.QUERY.SELECT_CERTIFICATE_TOBE_POSTED_COUNT);
      pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_CERTIFICATE_TOBE_POSTED_COUNT);
      pstm.setString(1, PoliceEnumConstant.ApplicationClearenceStatus.IS.toString());
      pstm.setString(2, PoliceEnumConstant.ApplicationClearenceStatus.GC.toString());

      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countCertificateToBePosted = rst.getInt("CERTIFICATE_TOBE_POSTED_COUNT");
        }
      }
      // LOGGER.info("CERTIFICATE_TOBE_POSTED_COUNT --> " + countCertificateToBePosted);
      rst.close();
	  pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }

    return countCertificateToBePosted;

  }

  public int countDHAApprovalPending(Connection con) {
    int countDHAApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_DHA_APPROVAL_PENDING_COUNT);
      // System.out.println("DBConstant.QUERY.SELECT_DHA_APPROVAL_PENDING_COUNT :" +
      // DBConstant.QUERY.SELECT_DHA_APPROVAL_PENDING_COUNT);
      // pstm.setString(1, PoliceEnumConstant.ApplicationQueue.DH.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countDHAApprovalPending = rst.getInt("DHA_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countDHAApprovalPending;
  }

  public int countDHAApprovalPendingGreenChannal(Connection con) {
    int countDHAApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_DHA_APPROVAL_PENDING_COUNT_GREEN_CHANNAL);
      // pstm.setString(1, PoliceEnumConstant.ApplicationQueue.DH.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countDHAApprovalPending = rst.getInt("DHA_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countDHAApprovalPending;
  }

  public int countDIGApprovalPending(Connection con) {
    int countDIGApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      // System.out.println("DBConstant.QUERY.SELECT_DIG_APPROVAL_PENDING_COUNT :" +
      // DBConstant.QUERY.SELECT_DIG_APPROVAL_PENDING_COUNT);
      pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_DIG_APPROVAL_PENDING_COUNT);
      // pstm.setString(1, PoliceEnumConstant.ApplicationQueue.DI.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countDIGApprovalPending = rst.getInt("DIG_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countDIGApprovalPending;
  }

  public int countDIGApprovalPendingGreenChannal(Connection con) {
    int countDIGApprovalPending = 0;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    try {
      pstm = con.prepareStatement(DBConstant.QUERY_NEW.SELECT_DIG_APPROVAL_PENDING_COUNT_GREEN_CHANNAL);
      // pstm.setString(1, PoliceEnumConstant.ApplicationQueue.DI.toString());
      rst = pstm.executeQuery();
      if (rst != null) {
        while (rst.next()) {
          countDIGApprovalPending = rst.getInt("DIG_APPROVAL_PENDING_COUNT");
        }
      }
	  rst.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseManager.close(rst);
      DatabaseManager.close(pstm);
    }
    return countDIGApprovalPending;
  }



}
