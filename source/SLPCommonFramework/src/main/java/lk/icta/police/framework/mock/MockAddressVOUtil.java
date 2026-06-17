package lk.icta.police.framework.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.AddressVO;

public class MockAddressVOUtil {
	
	public static AddressVO getAddressVO(long addressId, long applicationId, String addressType, long policeAreaId) {
		AddressVO addressVO=new AddressVO();
		addressVO.setAddress("This is test address no " + addressId);
		addressVO.setAddressId(addressId);
		addressVO.setApplicationId(applicationId);
		addressVO.setFromCreatedDateTime(Calendar.getInstance().getTime());
		addressVO.setFromDate(Calendar.getInstance().getTime());
		addressVO.setFromMessage("from Message");
		addressVO.setFromReceiveBy("from Receive By");
		addressVO.setFromSentBy("From Sent By");
		addressVO.setFromSentDate(Calendar.getInstance().getTime());
		
		addressVO.setPoliceArea("Test police Area");
		addressVO.setPoliceAreaId(policeAreaId);
		
		addressVO.setPoliceRecordLockStatus(0);
		addressVO.setPoliceStatus(PoliceEnumConstant.ApprovalStatus.PN.toString());
		addressVO.setResponseCreatedDateTime(Calendar.getInstance().getTime());
		addressVO.setResponseMessage("Response Message");
		addressVO.setResponseSentBy("Response Sent By");
		addressVO.setResponseSentDate(Calendar.getInstance().getTime());
		addressVO.setResponseSentTo("Response Sent To");
		
		addressVO.setFromDate(Calendar.getInstance().getTime());
		addressVO.setToDate(Calendar.getInstance().getTime());
		
		addressVO.setFromDateStr("12/12/2014");
		addressVO.setToDateStr("12/12/2014");
		
		addressVO.setType(addressType);		
		return addressVO;
	}
	
	public static List<AddressVO> getApplicationVOListByAddressType(int startFrom, int limit,long applicationId, String addressType) {
		List<AddressVO> addressVOs = new ArrayList<AddressVO>();
		long policeAreaId=1;
		for (long i = startFrom; i <= limit; i++) {
			if(i==1){
				policeAreaId=1;
			}else{
				if(i%2==0){
					policeAreaId=2;
				}
				if(i%3==0){
					policeAreaId=3;
				}
			}
			
			addressVOs.add(getAddressVO(i,applicationId,addressType,policeAreaId));
		}
		
		return addressVOs;
	}

}
