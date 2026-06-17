package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.BlackListDAO;
import lk.icta.police.framework.dao.CertificateIssuanceDAO;
import lk.icta.police.framework.dao.CommentDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.BlackListVO;
import lk.icta.police.framework.vo.CertificateClearenceGridVO;
import lk.icta.police.framework.vo.CertificateIssuanceSearchCriteriaVO;
import lk.icta.police.framework.vo.CommentsVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Nadeeshani Senevirathna
 * 
 */
public class DhaClearenceBusiness {

  private static final Logger LOGGER = Logger.getLogger(DhaClearenceBusiness.class);
  private static DhaClearenceBusiness instance = null;

  /**
   * Singleton Implementation
   * 
   */
  public static DhaClearenceBusiness getInstance() {
    synchronized (DhaClearenceBusiness.class) {
      if (instance == null) {
        instance = new DhaClearenceBusiness();
      }
      return instance;
    }
  }

  private DhaClearenceBusiness() {

  }


  public LinkedList<CertificateClearenceGridVO> searchApplications(
      CertificateIssuanceSearchCriteriaVO searchCriteriaVO, int userId) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = null;
    LinkedList<CertificateClearenceGridVO> certificateClearenceGridVOs = new LinkedList<CertificateClearenceGridVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      if (!(searchCriteriaVO == null)) {

        int limit = searchCriteriaVO.getLimit();

        long maximumAppId =
            CertificateIssuanceDAO.getInstance().maxApplicationIdForCertificateIssuance(searchCriteriaVO, con);

        System.out.println("searchCriteriaVO.getMaxId() " + searchCriteriaVO.getMaxId());
        System.out.println("maximimAppId " + maximumAppId);

        if (!(maximumAppId <= 0)) {
          // since next query is less than
          maximumAppId = maximumAppId + 1;

          if (searchCriteriaVO.getMaxId() == 0) {
            searchCriteriaVO.setMaxId(maximumAppId);
          }

          List<Long> applicationIds = new ArrayList<Long>();

          List<ApplicationVO> adverseRecords = ApplicationDAO.getInstance().getAllPreviousAdverseRecords(con);
          System.out.println("adverseRecords :" + adverseRecords.size());
          List<BlackListVO> blackListRecords = BlackListDAO.getInstance().getAllBlacklistRecords(con);
          System.out.println("blackListVOs :" + blackListRecords.size());
          System.out.println("searchCriteriaVO.isPrintPendingOnly() " + searchCriteriaVO.isPrintPendingOnly());
          mainwhile: while ((certificateClearenceGridVOs.size() < limit)) {

            returnedApplicationVOs =
                CertificateIssuanceDAO.getInstance().searchApplicationForCertificateIssuance(searchCriteriaVO, con,
                    PoliceConstant.CLEARANCE_APP_SEARCH_LIMITS);
            System.out.println("returnedApplicationVOs :" + returnedApplicationVOs.size());
            if (!(returnedApplicationVOs == null || returnedApplicationVOs.isEmpty())) {
              for (ApplicationVO applicationVO : returnedApplicationVOs) {

                if (certificateClearenceGridVOs.size() >= limit) {
                  System.out.println("break mainwhile max id :" + searchCriteriaVO.getMaxId());
                  break mainwhile;
                }

                // check if theres any other adverse records
                long adverseRecordCount =
                    CertificateIssuanceBusiness.getInstance().getCountOfPreviousAdverseRecords(adverseRecords,
                        applicationVO.getNic(), applicationVO.getPassport(), applicationVO.getApplicationId());
                // check if theres any blacklisted records
                long blackListCount =
                    CertificateIssuanceBusiness.getInstance().countBlackListRecordsByNICOrPPT(blackListRecords,
                        applicationVO.getNic(), applicationVO.getPassport());


                boolean hasAnyCurrentAdverse =
                    CertificateIssuanceBusiness.getInstance().checkAnyCurrentAdverseAvailable(applicationVO);

                boolean doAddApplicationVO =
                    checkIfToAddApplicationVO(applicationVO, searchCriteriaVO, con, adverseRecordCount, blackListCount,
                        hasAnyCurrentAdverse);

                applicationVO.constructcertificatepostalAddress(true);
                applicationVO.allocateFileTypes();
                if (applicationVO.getPhqRecordLockStatus() == userId && applicationVO.getPhqRecordLockStatus() != 0) {
                  applicationVO.setHasCurrentUserLocked(1);
                }

                if (searchCriteriaVO.isDefaultView()) {
                  if (doAddApplicationVO && (!(applicationIds.contains(applicationVO.getApplicationId())))) {
                    applicationIds.add(applicationVO.getApplicationId());

                    CertificateClearenceGridVO clearenceGridVO =
                        new CertificateClearenceGridVO(applicationVO, searchCriteriaVO.getUserType());

                    PoliceEnumConstant.CommentType commentType = PoliceEnumConstant.CommentType.LTR;
                    List<CommentsVO> commentsVOs =
                        CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(),
                            commentType.toString(), con);
                    if (!(commentsVOs == null || commentsVOs.isEmpty() || commentsVOs.get(0) == null)) {
                      clearenceGridVO.setCertificateLetterContent(commentsVOs.get(0).getComment());
                      clearenceGridVO.setCertificateLetterContentCommentId(commentsVOs.get(0).getCommentId());
                    }

                    certificateClearenceGridVOs.add(clearenceGridVO);
                  }
                } else if (searchCriteriaVO.isPrintPendingOnly()) {

                  if (doAddApplicationVO && (!(applicationIds.contains(applicationVO.getApplicationId())))) {
                    applicationIds.add(applicationVO.getApplicationId());

                    CertificateClearenceGridVO clearenceGridVO =
                        new CertificateClearenceGridVO(applicationVO, searchCriteriaVO.getUserType());

                    PoliceEnumConstant.CommentType commentType = PoliceEnumConstant.CommentType.LTR;
                    List<CommentsVO> commentsVOs =
                        CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(),
                            commentType.toString(), con);
                    if (!(commentsVOs == null || commentsVOs.isEmpty() || commentsVOs.get(0) == null)) {
                      clearenceGridVO.setCertificateLetterContent(commentsVOs.get(0).getComment());
                      clearenceGridVO.setCertificateLetterContentCommentId(commentsVOs.get(0).getCommentId());
                    }

                    certificateClearenceGridVOs.add(clearenceGridVO);
                  }
                } else {
                  if (doAddApplicationVO && (!(applicationIds.contains(applicationVO.getApplicationId())))) {
                    applicationIds.add(applicationVO.getApplicationId());
                    CertificateClearenceGridVO clearenceGridVO =
                        new CertificateClearenceGridVO(applicationVO, searchCriteriaVO.getUserType());

                    PoliceEnumConstant.CommentType commentType = PoliceEnumConstant.CommentType.LTR;
                    List<CommentsVO> commentsVOs =
                        CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationVO.getApplicationId(),
                            commentType.toString(), con);
                    if (!(commentsVOs == null || commentsVOs.isEmpty() || commentsVOs.get(0) == null)) {
                      clearenceGridVO.setCertificateLetterContent(commentsVOs.get(0).getComment());
                      clearenceGridVO.setCertificateLetterContentCommentId(commentsVOs.get(0).getCommentId());
                    }
                    certificateClearenceGridVOs.add(clearenceGridVO);
                  }
                }

                searchCriteriaVO.setMaxId(applicationVO.getApplicationId());
              }

            } else {
              searchCriteriaVO.setLastPage(true);
              break;
            }

          }

        }



      }
    } catch (BusinessException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (SQLException e) {
      LOGGER.error(e);
      e.printStackTrace();
    } catch (Exception e) {
      LOGGER.error(e);
      e.printStackTrace();
    } finally {
      DatabaseManager.close(con);
    }
    Collections.sort(certificateClearenceGridVOs);
    return certificateClearenceGridVOs;
  }

  public boolean checkIfToAddApplicationVO(ApplicationVO applicationVO,
      CertificateIssuanceSearchCriteriaVO searchCriteriaVO, Connection con, long adverseRecordCount,
      long blackListCount, boolean hasAnyCurrentAdverse) {

    boolean hasAnyAdverse = false;

    // check if theres any other adverse records
    // LOGGER.info("adverseRecordCount :" + adverseRecordCount);
    if (adverseRecordCount > 0) {
      hasAnyAdverse = true;
    }

    // check if theres any blacklisted records
    // LOGGER.info("blackListCount :" + blackListCount);
    if (blackListCount > 0) {
      applicationVO.setHasBlackListed(1);
    }

    // check if there's any current adverse
    if (!(hasAnyAdverse)) {
      hasAnyAdverse = hasAnyCurrentAdverse;
    }


    boolean doAddApplicationVO = false;
    if(!(StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString()))){
	    if (!(hasAnyAdverse)) {    	    	
	
	      if (StringUtils.equals(applicationVO.getApplicationQueue(), PoliceEnumConstant.ApplicationQueue.DH.toString())) {
	        if (searchCriteriaVO.isPrintPendingOnly()) {
	          if (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())
	              || (StringUtils.isEmpty(applicationVO.getDhaApproved()))) {
	            if (!(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	                PoliceEnumConstant.ApplicationClearenceStatus.BL.toString()) || StringUtils.equals(
	                applicationVO.getApplicationClearanceStatus(),
	                PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString()))) {
	            	
	            	if(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	                        PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())){            		
	            		if(StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())){
	            			doAddApplicationVO = true;
	            		}            		
	            		
	            	} else{
	            		doAddApplicationVO = true;
	            	}            	
	              
	            }
	          }
	
	        } else {
	          doAddApplicationVO = true;
	        }
	      }
	
	      if (StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	          PoliceEnumConstant.ApplicationClearenceStatus.GC.toString())) {
	        if (searchCriteriaVO.isPrintPendingOnly()) {
	          if (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())
	              || (StringUtils.isEmpty(applicationVO.getDhaApproved()))) {
	            // if(StringUtils.equals(applicationVO.getAspApproved(),
	            // PoliceEnumConstant.ApprovalStatus.AP.toString())){
	            doAddApplicationVO = true;
	            // }
	          }
	        } else {
	          doAddApplicationVO = true;
	        }
	      }
	
	      if (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())
	          || StringUtils.isEmpty(applicationVO.getDhaApproved())) {
	        if (StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())) {
	          if (searchCriteriaVO.isPrintPendingOnly()) {
	            if (!(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	                PoliceEnumConstant.ApplicationClearenceStatus.BL.toString()) || StringUtils.equals(
	                applicationVO.getApplicationClearanceStatus(),
	                PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString()))) {            	
	        		doAddApplicationVO = true;
	            	
	            }
	          } else {
	            doAddApplicationVO = true;
	          }
	        }
	      }
	
	      if (!(searchCriteriaVO.isPrintPendingOnly())) {
	        if (StringUtils.isEmpty(applicationVO.getRegPostNo())) {
	        	
	        	if(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	                    PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())){            		
	        		if(StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString()) 
	        				&& (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())
	        		          || StringUtils.isEmpty(applicationVO.getDhaApproved()))){
	        			doAddApplicationVO = true;
	        		}          		
	
	        	}  
	        	
	        }
	      }
	
	
	    } else {
	
	      applicationVO.setAnyAdverseAvailable(1);
	
	      if (StringUtils.equals(applicationVO.getApplicationQueue(), PoliceEnumConstant.ApplicationQueue.DH.toString())) {
	        if (searchCriteriaVO.isPrintPendingOnly()) {
	          if (!(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	              PoliceEnumConstant.ApplicationClearenceStatus.BL.toString()) || StringUtils.equals(
	              applicationVO.getApplicationClearanceStatus(),
	              PoliceEnumConstant.ApplicationClearenceStatus.RJ.toString()))) {        	  
	        	  
	        	  if(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	                      PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())){            		
	          		if(StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())){
	          			doAddApplicationVO = true;
	          		}            		
	          		
	          	} else{
	          		doAddApplicationVO = true;
	          	}  
	          }
	        } else {
	        	if(StringUtils.equals(applicationVO.getApplicationClearanceStatus(),
	                    PoliceEnumConstant.ApplicationClearenceStatus.EI.toString())){            		
	        		if(StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())){
	        			doAddApplicationVO = true;
	        		}            		
	        		
	        	} else{
	        		doAddApplicationVO = true;
	        	}  
	        }
	      }
	
	      PoliceEnumConstant.ApplicationClearenceStatus clearenceStatusEnum =
	          PoliceEnumConstant.ApplicationClearenceStatus.PN;
	      // LOGGER.info("clearenceStatusEnum before" + clearenceStatusEnum);
	      if (StringUtils.isNotEmpty(applicationVO.getApplicationClearanceStatus())) {
	        clearenceStatusEnum =
	            PoliceEnumConstant.ApplicationClearenceStatus.fromCode(applicationVO.getApplicationClearanceStatus());
	      }
	      // LOGGER.info("clearenceStatusEnum after " + clearenceStatusEnum);
	      switch (clearenceStatusEnum) {
	        case PN:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            doAddApplicationVO = true;
	          }
	          break;
	        case NC:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            doAddApplicationVO = true;
	          }
	          break;
	        case DC:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            doAddApplicationVO = true;
	          }
	          break;
	        case GC:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            doAddApplicationVO = true;
	          } else {
	            if (StringUtils.equals(applicationVO.getDhaApproved(), PoliceEnumConstant.ApprovalStatus.PN.toString())) {
	              doAddApplicationVO = true;
	            }
	          }
	          break;
	        case EI:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {        	             		
	          		if(StringUtils.equals(applicationVO.getAspApproved(), PoliceEnumConstant.ApprovalStatus.AP.toString())){
	          			doAddApplicationVO = true;
	          		}else if (StringUtils.isEmpty(applicationVO.getAspApproved()) && StringUtils.isEmpty(applicationVO.getDhaApproved())){
	          			doAddApplicationVO = true;          			
	          		}           	 
	          }
	          break;
	        case IS:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            if (StringUtils.isNotBlank(applicationVO.getCertificateType())) {
	              if (StringUtils.equals(applicationVO.getCertificateType(),
	                  PoliceEnumConstant.CertificateType.LT.toString())) {
	                if (StringUtils.equals(applicationVO.getDigApproved(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
	                  doAddApplicationVO = true;
	                }                
	              }else if ((StringUtils.equals(applicationVO.getCertificateType(),
	                      PoliceEnumConstant.CertificateType.CT.toString())) || (StringUtils.equals(applicationVO.getCertificateType(),
	                              PoliceEnumConstant.CertificateType.CL.toString()))) {
	//                  if (StringUtils.equals(applicationVO.getDigApproved(), PoliceEnumConstant.ApprovalStatus.RJ.toString())) {
	            		  doAddApplicationVO = true;
	//                  }
	                }
	            }
	          }
	          break;
	        case CP:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            doAddApplicationVO = true;
	          }
	          break;
	        case RJ:
	          if (!(searchCriteriaVO.isPrintPendingOnly())) {
	            doAddApplicationVO = true;
	          }
	          break;
	        default:
	          System.out.println("NEW clearenceStatusEnum :" + clearenceStatusEnum);
	          break;
	      }
	    }

  }

    return doAddApplicationVO;
  }

  public List<CommentsVO> getCommentsByCommentTypeAndApplicationId(long applicationId, String commentType) {
      Connection conn = null;
      try {
          conn = DatabaseManager.getPOLDBConnection();
          conn.setAutoCommit(false);
          List<CommentsVO> commentsVOs = CommentDAO.getInstance().getCommentListByTypeAndApplicationId(applicationId, commentType, conn);
          return commentsVOs;
//          if (!(commentsVOs == null || commentsVOs.isEmpty() || commentsVOs.get(0) == null)) {
//              return commentsVOs.get(0);
//          }
      } catch (BusinessException e) {
          LOGGER.error(e);
          e.printStackTrace();
      } catch (SQLException e) {
          LOGGER.error(e);
          e.printStackTrace();
      } catch (Exception e) {
          LOGGER.error(e);
          e.printStackTrace();
      } finally {
          DatabaseManager.close(conn);
      }
      return null;
  }

}
