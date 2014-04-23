package org.yangchigi.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.web.User;

public class UserTest {
	private static final Logger logger = LoggerFactory.getLogger("org.yangchigi.web.UserTest");
	
	@Test
	public void getter_테스트() {
		User user = new User("hook3748@gmail.com", "hogu", "123456", "");
		assertThat(user.getEmail(), is("hook3748@gmail.com"));
	}
	
	@Test
	public void 로그백_테스트() {
		logger.debug("Hello world!");
	}
}
