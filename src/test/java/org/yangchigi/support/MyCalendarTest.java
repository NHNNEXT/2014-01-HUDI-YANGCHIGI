package org.yangchigi.support;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCalendarTest {
	Logger logger = LoggerFactory.getLogger("org.yangchigi.web.UserTest");

	@Test
	public void getCurrentTime_출력결과() {
		logger.info("MyCalendar.getCurrentTime(): {}",
				MyCalendar.getCurrentTime());
	}
	
	@Test
	public void getCurrentDateTime_출력결과() {
		logger.info("MyCalendar.getCurrentDateTime(): {}",
				MyCalendar.getCurrentDateTime());
	}
	
	@Test
	public void getCurrentDateTime에서_Date_와_Time_나누기() {
		String dateTime = "2014-4-16 21:31:43";
		assertThat(MyCalendar.getDate(dateTime), is("2014-4-16"));
		assertThat(MyCalendar.getTime(dateTime), is("21:31:43"));
	}
}