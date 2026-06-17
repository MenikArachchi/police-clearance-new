package lk.icta.police.business;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.AddressDAO;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.dao.PoliceFormDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationStatusViewSearchCriteriaVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.PoliceFormItemVO;
import lk.icta.police.framework.vo.PoliceFormVO;

import org.apache.log4j.Logger;

public class PoliceFormBusiness implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = Logger.getLogger(PoliceFormBusiness.class);
  private static PoliceFormBusiness instance = null;

  /**
   * Singleton Implementation
   */
  public static PoliceFormBusiness getInstance() {
    synchronized (PoliceFormBusiness.class) {
      if (instance == null) {
        instance = new PoliceFormBusiness();
      }
      return instance;
    }
  }

  private PoliceFormBusiness() {

  }

  public List<ApplicationVO> searchApplication(ApplicationStatusViewSearchCriteriaVO searchCriteriaVO, int userRole,
      long location, int userId) {
    Connection con = null;
    List<ApplicationVO> returnedApplicationVOs = new ArrayList<ApplicationVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      returnedApplicationVOs = PoliceFormDAO.getInstance().searchApplicationsForPoliceForm(searchCriteriaVO, con);
      if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()) {
        List<ApplicationVO> returnedApplicationVOsModfied = new ArrayList<ApplicationVO>();
        for (ApplicationVO applicationVO : returnedApplicationVOs) {
          List<AddressVO> addressVOs =
              AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(applicationVO.getApplicationId(),
                  PoliceEnumConstant.AddressType.AC.toString(), location, con);
          if (!(addressVOs == null || addressVOs.isEmpty())) {
            returnedApplicationVOsModfied.add(applicationVO);
          }
        }
        return returnedApplicationVOsModfied;
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
    return returnedApplicationVOs;
  }


  public List<PoliceFormVO> getSelectedApplicationPoliceFormByApplicationId(long applicationId, int userRole,
      long userLocation) {
    Connection con = null;
    List<PoliceFormVO> formVOs = new ArrayList<PoliceFormVO>();
    try {
      con = DatabaseManager.getPOLDBConnection();
      con.setAutoCommit(false);
      ApplicationVO applicationVO = ApplicationDAO.getInstance().getApplicationById(applicationId, con);
      applicationVO.constructcertificatepostalAddress(true);
      if (!(applicationVO == null)) {
        String referenceNo = applicationVO.getReferenceNo();
        if (userRole == PoliceEnumConstant.UserDepartment.PHQ.getCode()) {
          List<AddressVO> addressVOs =
              CertificateIssuanceBusiness.getInstance().getAddressVOsByApplicationIdAndType(applicationId,
                  PoliceEnumConstant.AddressType.AC.toString(), con);
          Collections.sort(addressVOs);
          Map<Long, PoliceFormVO> formVoMap = new HashMap<Long, PoliceFormVO>();
          if (!(addressVOs == null || addressVOs.isEmpty())) {
            int no = 1;
            for (AddressVO addressVO : addressVOs) {
              PoliceFormVO policeFormVO = null;
              if (!(formVoMap.containsKey(addressVO.getPoliceAreaId()))) {
                no = 1;
                policeFormVO =
                    new PoliceFormVO(applicationId, referenceNo, addressVO.getPoliceAreaId(),
                        addressVO.getPoliceArea(), new ArrayList<PoliceFormItemVO>());
                formVoMap.put(addressVO.getPoliceAreaId(), policeFormVO);
              }
              PoliceFormItemVO policeFormItemVO =
                  new PoliceFormItemVO(addressVO.getAddressId(), applicationVO.getApplicantNameAsNic(),
                      applicationVO.getNic(), applicationVO.getNewNic(), applicationVO.getPassport(),
                      addressVO.getAddress(), addressVO.getFromDate(), addressVO.getToDate(), no,
                      applicationVO.getMobileNo());

              policeFormItemVO.setCurrentNic(applicationVO.getCurrentNicNo());

              formVoMap.get(addressVO.getPoliceAreaId()).getPoliceFormItemVOs().add(policeFormItemVO);
              no = no + 1;
            }
            System.out.println("formVoMap :" + formVoMap);
            for (Entry<Long, PoliceFormVO> mapEntry : formVoMap.entrySet()) {
              formVOs.add(mapEntry.getValue());
            }
          }
        } else if (userRole == PoliceEnumConstant.UserDepartment.POL.getCode()) {
          List<AddressVO> addressVOs =
              AddressDAO.getInstance().getAddressListByTypeLocationAndApplicationId(applicationId,
                  PoliceEnumConstant.AddressType.AC.toString(), userLocation, con);
          Collections.sort(addressVOs);
          PoliceFormVO policeFormVO = null;
          int no = 1;
          for (AddressVO addressVO : addressVOs) {
            if (policeFormVO == null) {
              policeFormVO =
                  new PoliceFormVO(applicationId, referenceNo, addressVO.getPoliceAreaId(), addressVO.getPoliceArea(),
                      new ArrayList<PoliceFormItemVO>());
            }
            PoliceFormItemVO policeFormItemVO =
                new PoliceFormItemVO(addressVO.getAddressId(), applicationVO.getApplicantNameAsNic(),
                    applicationVO.getNic(), applicationVO.getNewNic(), applicationVO.getPassport(),
                    addressVO.getAddress(), addressVO.getFromDate(), addressVO.getToDate(), no,
                    applicationVO.getMobileNo());
            policeFormItemVO.setCurrentNic(applicationVO.getCurrentNicNo());
            policeFormVO.getPoliceFormItemVOs().add(policeFormItemVO);
            no = no + 1;
          }
          formVOs.add(policeFormVO);
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
    return formVOs;
  }


}
