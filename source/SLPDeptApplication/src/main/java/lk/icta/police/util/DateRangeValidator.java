package lk.icta.police.util;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.icta.police.framework.vo.DateValidationVO;

import com.google.gson.Gson;

public class DateRangeValidator implements Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean isOverlapping(Date from1, Date to1, Date from2, Date to2) {
	    return !from1.after(to2) && !from2.after(to1);
	}
	
	private static boolean isOverlappingDates(DateValidationVO[] addresses) throws ParseException {
		 boolean isOverlappingDates=false;
		 outer_for:for (int j=0;j<addresses.length;j++) {
			 inner_for:for (int k=j+1;k<addresses.length;k++) {
			    	Date from1=addresses[k].getFromDateAsDate();
			    	Date to1=addresses[k].getToDateAsDate();
			    	Date from2=addresses[j].getFromDateAsDate();
			    	Date to2=addresses[j].getToDateAsDate();
			    	isOverlappingDates=isOverlapping(from1, to1, from2, to2);
			    	if(isOverlappingDates){
			    		break outer_for;
			    	}
			    }
			}
		return isOverlappingDates;
	}

	public static boolean isFutureDateIncluded(List<Date> dateList) throws ParseException {
		boolean isFutureDate=false;
		Calendar cToday = Calendar.getInstance();
		// set the calendar to start of today
		cToday.set(Calendar.HOUR_OF_DAY, 0);
		cToday.set(Calendar.MINUTE, 0);
		cToday.set(Calendar.SECOND, 0);
		cToday.set(Calendar.MILLISECOND, 0);

		// and get that as a Date
		Date today = cToday.getTime();

		Calendar cSpecifiedDate = Calendar.getInstance();
		
		
		for (Date date : dateList) {
			
			cSpecifiedDate.setTime(date);
			// set the calendar to start of today
			cSpecifiedDate.set(Calendar.HOUR_OF_DAY, 0);
			cSpecifiedDate.set(Calendar.MINUTE, 0);
			cSpecifiedDate.set(Calendar.SECOND, 0);
			cSpecifiedDate.set(Calendar.MILLISECOND, 0);

			// and get that as a Date
			Date dateSpecified = cSpecifiedDate.getTime();
			
			isFutureDate=dateSpecified.after(today);
			
			if(isFutureDate){
				break;
			}
			
		}
		return isFutureDate;
		
	}
	

	public static Map<String,Object> validateAddressdateRange(String dateValidateJsonString) {
		String addressPeriodValidMessage=null;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("VALIDITY", false);
		
		Gson gson = new Gson();
		DateValidationVO[] addresses = gson.fromJson(dateValidateJsonString,
				DateValidationVO[].class);
		for (DateValidationVO dateValidationVO : addresses) {
			System.out.println("dateValidationVO :" + dateValidationVO);
		}

		try {
			List<Date> dateList = new ArrayList<Date>();
			for (int i = 0; i < addresses.length; i++) {
				Date from = addresses[i].getFromDateAsDate();
				Date to = addresses[i].getToDateAsDate();
				dateList.add(from);
				dateList.add(to);
			}

			boolean isFutureDateIncluded = DateRangeValidator
					.isFutureDateIncluded(dateList);
			System.out.println("isFutureDateIncluded :" + isFutureDateIncluded);
			if (!(isFutureDateIncluded)) {
				boolean isOverlapping = DateRangeValidator.isOverlappingDates(addresses);
				System.out.println("isOverlapping :" + isOverlapping);
				if (!(isOverlapping)) {
						map.put("VALIDITY", true);
				} else {
					addressPeriodValidMessage = "The period of stay is not valid, its overlapping with the records already enetered!";
				}
			} else {
				addressPeriodValidMessage = "The date range cannot contain a future date!";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("MESSAGE", addressPeriodValidMessage);    
		System.out.println("map :" + map);
		return map;
	}

	
	


}
