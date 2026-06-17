package lk.icta.police.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lk.icta.police.business.ApplicationBusiness;
import lk.icta.police.business.CertificateIssuanceBusiness;
import lk.icta.police.business.ReviewApplicationExternalBusiness;
import lk.icta.police.framework.constant.PoliceConstant;

import org.apache.log4j.Logger;

public class CustomHttpSessionListener implements HttpSessionListener {
	
	private static final Logger LOGGER = Logger.getLogger(CustomHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		LOGGER.info("SESSION IS CREATED");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LOGGER.info("SESSION IS DESTROYING :: CHECK AND UNLOCK ANY APPLICATION RECORDS");
		HttpSession session=event.getSession();
		
		int userRole=0;
		if(!(session.getAttribute(PoliceConstant.SESSION_USER_ROLE)==null)){
			userRole=(Integer) session.getAttribute(PoliceConstant.SESSION_USER_ROLE);
		}
		
		long locationId=0;
		if(!(session.getAttribute(PoliceConstant.SESSION_USER_LOCATION)==null)){
			locationId=(Long) session.getAttribute(PoliceConstant.SESSION_USER_LOCATION);
		}
		
		if(!(session==null || session.getAttribute(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP)==null)){
			Map<Long,Integer> recordLockedMap=(Map<Long, Integer>) session.getAttribute(PoliceConstant.SESSION_LOCKED_APPLICATION_ID_MAP);
			LOGGER.info("recordLockedMap IN SESSION :" + recordLockedMap);
			if(!(recordLockedMap==null || recordLockedMap.isEmpty())){
				for (Entry<Long, Integer> entry : recordLockedMap.entrySet()) {
					long lockedApplicationId=entry.getKey();					
					if(lockedApplicationId>0){
						LOGGER.info("TRYING TO UNLOCK :" + lockedApplicationId);
						String status=ReviewApplicationExternalBusiness.getInstance().unlockCooRecord(lockedApplicationId, userRole, locationId);
						LOGGER.info("UNLOCKED_STATUS :" + status);
					}
				}
			}
		}
		
		
		if(!(session==null || session.getAttribute(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP)==null)){
			Map<Long,Integer> recordLockedMap=(Map<Long, Integer>) session.getAttribute(PoliceConstant.SESSION_LOCKED_CLEARENCE_APPLICATION_ID_MAP);
			LOGGER.info("recordLockedMap IN SESSION :" + recordLockedMap);
			if(!(recordLockedMap==null || recordLockedMap.isEmpty())){
				for (Entry<Long, Integer> entry : recordLockedMap.entrySet()) {
					long lockedApplicationId=entry.getKey();					
					if(lockedApplicationId>0){
						LOGGER.info("TRYING TO UNLOCK :" + lockedApplicationId);
						String status=CertificateIssuanceBusiness.getInstance().unlockClearenceRecord(lockedApplicationId, entry.getValue());
						LOGGER.info("UNLOCKED_STATUS :" + status);
					}
				}
			}
		}
		
		if(!(session==null || session.getAttribute(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP)==null)){
			Map<Long,Integer> recordLockedMap=(Map<Long, Integer>) session.getAttribute(PoliceConstant.SESSION_LOCKED_APPLICATION_VERIFICATION_ID_MAP);
			LOGGER.info("recordLockedApplicationVerificationMap IN SESSION :" + recordLockedMap);
			if(!(recordLockedMap==null || recordLockedMap.isEmpty())){
				for (Entry<Long, Integer> entry : recordLockedMap.entrySet()) {
					long lockedApplicationId=entry.getKey();					
					if(lockedApplicationId>0){
						LOGGER.info("TRYING TO UNLOCK :" + lockedApplicationId);
						String status=ApplicationBusiness.getInstance().unlockPHQRecord(lockedApplicationId,null);
						LOGGER.info("UNLOCKED_STATUS :" + status);
					}
				}
			}
		}
	}
	
	

}
