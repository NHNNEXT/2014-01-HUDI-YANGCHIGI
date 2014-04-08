package org.yangchigi.support;

import java.util.Calendar;

public class MyCalendar {

	public static String getCurrentDateTime() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get ( Calendar.MONTH ) + 1 ;
		int date = cal.get ( Calendar.DATE ) ;
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		String curDate = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
		System.out.println(curDate);
		
		return curDate;
	}
}
