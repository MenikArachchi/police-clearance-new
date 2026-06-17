package lk.icta.police.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.dao.ApplicationDAO;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.exception.BusinessException;
import lk.icta.police.framework.vo.ApplicationClearanceDate;
import lk.icta.police.framework.vo.ApplicationModifiedDatesVO;
import lk.icta.police.framework.vo.ApplicationVO;
import lk.icta.police.framework.vo.PoliceApplicationIssueReportVO;

import org.apache.log4j.Logger;


/**
 * @author Nadeeshani Senevirathna
 *
 */
public class PoliceReportBusiness {
	
	private static final Logger LOGGER = Logger.getLogger(PoliceReportBusiness.class);
	private static PoliceReportBusiness instance = null;
	/**
	 * Singleton Implementation
	 * 
	 */
	public static PoliceReportBusiness getInstance() {
		synchronized (PoliceReportBusiness.class) {
			if (instance == null) {
				instance = new PoliceReportBusiness();
			}
			return instance;
		}
	}
	
    private PoliceReportBusiness() {
		
	}
    
    
    public List<PoliceApplicationIssueReportVO> getPoliceApplicationIssuedReport(Date fromDate, Date toDate){
    	List<PoliceApplicationIssueReportVO> applicationIssueReportVOs=new ArrayList<PoliceApplicationIssueReportVO>();
    	Connection con = null;
    	
    	try {
			con = DatabaseManager.getPOLDBConnection();
			con.setAutoCommit(false);
			List<ApplicationVO> applicationVOs=ApplicationDAO.getInstance().getAllVerifiedApplications(con,fromDate,toDate);
			if(!(applicationVOs==null)){
				int index=1;
				for (ApplicationVO applicationVO : applicationVOs) {
					List<ApplicationClearanceDate> applicationClearanceDates=ApplicationBusiness.getInstance().getApplicationClearanceDateListByApplicationId(applicationVO.getApplicationId(),con);
					if(!(applicationClearanceDates==null || applicationClearanceDates.isEmpty())){
						Collections.sort(applicationClearanceDates, new Comparator<ApplicationClearanceDate>() {
							@Override
							public int compare(ApplicationClearanceDate o1,	ApplicationClearanceDate o2) {
								return o1.getDepartment().compareTo(o2.getDepartment());
							}
						});
					}
					List<ApplicationModifiedDatesVO> applicationModifiedDates=ApplicationDAO.getInstance().getApplicationModifiedDateByTypeAndApplicationId(applicationVO.getApplicationId(), PoliceEnumConstant.ApplicationModifiedDateTypes.VFM.toString(), con);
					Date verifiedDate=null;
					if(!(applicationModifiedDates==null || applicationModifiedDates.isEmpty())){
						Collections.sort(applicationModifiedDates);
						verifiedDate=applicationModifiedDates.get(0).getModifiedDate();
					}
					applicationIssueReportVOs.add(new PoliceApplicationIssueReportVO(index,verifiedDate,applicationVO,applicationClearanceDates,con));
					index ++;
				}
			}
			System.out.println(applicationIssueReportVOs);
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
    	return applicationIssueReportVOs;
    }
	
	
	

}
