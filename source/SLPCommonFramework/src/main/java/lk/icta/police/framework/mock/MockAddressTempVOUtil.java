package lk.icta.police.framework.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.vo.AddressTempVO;

public class MockAddressTempVOUtil {
	
	public static AddressTempVO getAddressTempVO(long addressTempId,long addressId,long policeAreaId) {
		AddressTempVO addressTempVO=new AddressTempVO();
		addressTempVO.setActiveStatus(1);
		addressTempVO.setAddress(addressTempId + " Test Address, Test Road");
		addressTempVO.setAddressId(addressId);
		addressTempVO.setAddressStatus(PoliceEnumConstant.AddressModificationStatus.CF.toString());
		addressTempVO.setAddressTempId(addressTempId);
		addressTempVO.setComment("Test comment jkhfk kwjfh wkef");
		addressTempVO.setFromDate(Calendar.getInstance().getTime());
		addressTempVO.setToDate(Calendar.getInstance().getTime());
		addressTempVO.setPoliceArea("Police area " + policeAreaId);
		addressTempVO.setPoliceAreaId(policeAreaId);
		addressTempVO.setPoliceAreaStatus(PoliceEnumConstant.AddressModificationStatus.RM.toString());
		addressTempVO.setStayPeriodStatus(PoliceEnumConstant.AddressModificationStatus.MF.toString());
		addressTempVO.setUpdatedDate(Calendar.getInstance().getTime());
		addressTempVO.setUpdatedUserId(1);
		addressTempVO.setUpdatedUserName("Test User");		
		return addressTempVO;
	}
	
	public static List<AddressTempVO> getAddressTempVOListByAddressId(int startFrom, int limit,long addressId) {
		List<AddressTempVO> addressTempVOs = new ArrayList<AddressTempVO>();
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
			
			addressTempVOs.add(getAddressTempVO(i,addressId,policeAreaId));
		}
		
		return addressTempVOs;
	}

}
