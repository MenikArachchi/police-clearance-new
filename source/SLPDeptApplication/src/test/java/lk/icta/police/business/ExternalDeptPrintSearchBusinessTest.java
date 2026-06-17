package lk.icta.police.business;

import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.ExternalDeptPrintSearchCriteriaVO;

import org.junit.Test;

public class ExternalDeptPrintSearchBusinessTest {



  @Test
  public void testSearchApplication() {
    ExternalDeptPrintSearchCriteriaVO searchCriteriaVO =
        new ExternalDeptPrintSearchCriteriaVO(null, null, null, null, null, null, null);
    searchCriteriaVO.setStartFrom(0);
    searchCriteriaVO.setLimit(35);
    searchCriteriaVO.setClearanceStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
    int userRole = PoliceEnumConstant.UserDepartment.NIC.getCode();
    long location = 2;
    int userId = 2;
    searchCriteriaVO.updateSearchCriteriaVO(userRole, location);
    List<ApplicationVO> applications =
        ExternalDeptPrintSearchBusiness.getInstance().searchApplication(searchCriteriaVO, userRole, location, userId);
    System.out.println("applications " + applications);
  }
  // @Test
  // public void testGetApplicationListByIds() {
  // List<Long> applicationIdList=new ArrayList<Long>();
  // applicationIdList.add(1l);
  // applicationIdList.add(2l);
  // applicationIdList.add(3l);
  // int userRole=3;
  // long location=2;
  // int userId=2;
  // List<ApplicationVO>
  // applications=ExternalDeptPrintSearchBusiness.getInstance().getApplicationListByIds(applicationIdList,
  // userRole, location, userId);
  // System.out.println("applications :" + applications);
  //
  // }

}
