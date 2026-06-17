package lk.icta.police.business;

import java.util.ArrayList;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.ApplicationVO;

import org.junit.Test;

public class LockedRecordBussinessTest {

  @Test
  public void testGetLockedRecords() {
    PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.CID;
    int userId = 0;
    List<ApplicationVO> list = LockedRecordBussiness.getInstance().getLockedRecords(userDepartmentEnum, userId);
    for (ApplicationVO applicationVO : list) {
      System.out.println(applicationVO.getPhqRecordLockStatus());
    }
  }

  @Test
  public void testUnlockRecords() {
    PoliceEnumConstant.UserDepartment userDepartmentEnum = PoliceEnumConstant.UserDepartment.CID;
    List<Long> applicationIds = new ArrayList<Long>();
    applicationIds.add(10L);
    applicationIds.add(15L);
    String status = LockedRecordBussiness.getInstance().unlockRecords(userDepartmentEnum, applicationIds);
    System.out.println("status :" + status);
  }

}
