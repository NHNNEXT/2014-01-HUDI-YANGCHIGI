package org.yangchigi.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyCalendar {

	public static String getCurrentDate() {
//		java.util.Calendar cal = java.util.Calendar.getInstance();
//		String year = String.valueOf(cal.get(Calendar.YEAR));
//		String month = String.valueOf(cal.get ( Calendar.MONTH ) + 1) ;
//		String date = String.valueOf(cal.get ( Calendar.DATE )) ;
//		
//		if(month.length() == 1) month = "0" + month;
//		if(date.length() == 1) date = "0" + date;
//		
//		String curDate = year + "-" + month + "-" + date;
//		return curDate;
		Date today = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(today);		
	}

	public static String getCurrentTime() {
//		java.util.Calendar cal = java.util.Calendar.getInstance();
//		String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
//		String minute = String.valueOf(cal.get(Calendar.MINUTE));
//		
//		if(hour.length() == 1) hour = "0" + hour;
//		if(minute.length() == 1) minute = "0" + minute;
//		
//		String curTime = hour + ":" + minute;
//		return curTime;
//		
		Date today = new Date();
		return new SimpleDateFormat("HH:mm:ss").format(today);
	}
	
	
}
