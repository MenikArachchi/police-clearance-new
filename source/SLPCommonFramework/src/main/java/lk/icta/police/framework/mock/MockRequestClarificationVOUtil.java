package lk.icta.police.framework.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.icta.police.framework.vo.RequestClarificationVO;

public class MockRequestClarificationVOUtil {

	public static RequestClarificationVO getRequestClarificationVO(int applicationId) {
		RequestClarificationVO requestClarificationVO = new RequestClarificationVO();
		requestClarificationVO.setNic(1);
		requestClarificationVO.setNicMessage("nicMessage_"+ applicationId);
		requestClarificationVO.setPassport(1);
		requestClarificationVO.setPassportMessage("passportMessage_"+ applicationId);
		requestClarificationVO.setVerifyName(1);
		requestClarificationVO.setNameMessage("nameMessage_"+ applicationId);
		requestClarificationVO.setVerifyDateOfBirth(1);
		requestClarificationVO.setDateOfBirthMessage("dateOfBirthMessage_"+ applicationId);
		requestClarificationVO.setReferenceNo("refNo_"+ applicationId);
		requestClarificationVO.setNicPath("nicPath_"+ applicationId);
		requestClarificationVO.setNicAcceptStatus(1);
		requestClarificationVO.setPassportPath("passportPath_"+ applicationId);
		requestClarificationVO.setPassportAcceptStatus(1);
		requestClarificationVO.setName("name_"+ applicationId);
		requestClarificationVO.setNameAcceptStatus(1);
		requestClarificationVO.setDateOfBirth(new Date());
		requestClarificationVO.setDateOfBirthAcceptStatus(1);
		requestClarificationVO.setComment("comment_"+ applicationId);
		requestClarificationVO.setRequestedUserId(3);
		requestClarificationVO.setRequestedUserName("requestedUserName_"+ applicationId);
		requestClarificationVO.setRequestedDateTime(new Date());
		requestClarificationVO.setResubmittedUserId(2);
		requestClarificationVO.setResubmittedUserName("resubmittedUserName_"+ applicationId);
		requestClarificationVO.setResubmittedDateTime(new Date());
		requestClarificationVO.setReviewedUserId(1);
		requestClarificationVO.setReviewedUserName("reviewedUserName_"+ applicationId);
		requestClarificationVO.setReviewedDateTime(new Date());
		requestClarificationVO.setApplicationId(applicationId);
		
		return requestClarificationVO;	
	} 
	
	public static List<RequestClarificationVO> getRequestClarificationVOList(int startFrom, int limit) {
		List<RequestClarificationVO> requestClarificationVOs = new ArrayList<RequestClarificationVO>();
		
		for (int i = startFrom; i <= limit; i++) {
			requestClarificationVOs.add(getRequestClarificationVO(i));
		}
		
		return requestClarificationVOs;
	}
}
