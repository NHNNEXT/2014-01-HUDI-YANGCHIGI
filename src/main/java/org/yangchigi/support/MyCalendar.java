package org.yangchigi.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyCalendar {
	public static String getCurrentDate() {
		Date today = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(today);		
	}
	
	public static String getCurrentTime() {
		Date today = new Date();
		return new SimpleDateFormat("HH:mm:ss").format(today);
	}
	public static String getCurrentTimeWithoutSec() {
		Date today = new Date();
		return new SimpleDateFormat("HH:mm").format(today);
	}

}
