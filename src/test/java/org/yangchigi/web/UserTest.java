package org.yangchigi.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class UserTest {
	
	@Test
	public void getter_테스트() {
		User user = new User("hook3748@gmail.com", "hogu", "123456", "");
		assertThat(user.getEmail(), is("hook3748@gmail.com"));
	}
	
	@Test
	public void 로그백_테스트() {
		Logger logger = LoggerFactory.getLogger("org.yangchigi.web.UserTest");
		logger.debug("Hello world!");
		
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}
}
