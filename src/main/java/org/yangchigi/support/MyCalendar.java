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

		return curDate;
	}

	public static String getCurrentTime() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(cal.get(Calendar.MINUTE));
		if(hour.length() == 1) hour = "0" + hour;
		if(minute.length() == 1) minute = "0" + minute;
		
		String curTime = hour + ":" + minute;
		return curTime;
	}
	
	private static String[] getSplitedDateTime(String dateTime) {
		return dateTime.split(" ");
	}

	public static String getDate(String dateTime) {
		return getSplitedDateTime(dateTime)[0];
	}

	public static String getTime(String dateTime) {
		return getSplitedDateTime(dateTime)[1];
	}
}