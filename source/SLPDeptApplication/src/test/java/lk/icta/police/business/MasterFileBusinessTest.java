package lk.icta.police.business;

import junit.framework.Assert;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.vo.PoliceAreaVO;

import org.apache.log4j.Logger;
import org.junit.Test;

public class MasterFileBusinessTest {

	private static final Logger LOGGER = Logger.getLogger(MasterFileBusinessTest.class);
	
	
	
	@Test
	public void testSavePoliceArea() {
		LOGGER.info("TESTING SAVE POLICE AREA ...");
		PoliceAreaVO policeAreaVO=new PoliceAreaVO(1, "MyPoliceArea1");
		String status=MasterFileBusiness.getInstance().savePoliceArea(policeAreaVO);
		Assert.assertEquals(PoliceConstant.SUCCESS, status);
	}


}
