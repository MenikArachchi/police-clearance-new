package lk.icta.police.business;

import java.util.List;

import junit.framework.Assert;
import lk.icta.police.framework.vo.AddressVO;
import lk.icta.police.framework.vo.ApplicationVO;

import org.apache.log4j.Logger;
import org.junit.Test;

public class UpdatePoliceMessageBusinessTest {

	private static final Logger LOGGER = Logger.getLogger(UpdatePoliceMessageBusinessTest.class);
	
	@Test
	public void testGetApplicationByApplicationId() {
		long applicationId = 1;
		ApplicationVO applicationVO = UpdatePoliceMessageBusiness.getInstance().getApplicationByApplicationId(applicationId);
		LOGGER.info("No of addresses for the applicatoin:"+applicationVO.getApplicantCertificateAddresses().size());
		Assert.assertTrue(applicationVO.getApplicantCertificateAddresses().size()>0);
	}
	
	@Test
	public void testGetAddressListForUpdate(){
		long applicationId = 3;
		List<AddressVO> addressList = UpdatePoliceMessageBusiness.getInstance().getAddressListForUpdate(applicationId);
		LOGGER.info("No of addresses for the update:"+addressList.size());
		Assert.assertTrue(addressList.size()>0);
	}

}
