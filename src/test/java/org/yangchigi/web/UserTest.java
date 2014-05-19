package org.yangchigi.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.dto.User;

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
	
	@Test
	public void 이메일_정규식_테스트() {
		boolean isValidEmail = "hook3748@gmail.com".matches("^(\\w+@\\w+\\.\\w{2,})$");
		assertTrue(isValidEmail);
	}
	
	@Test
	public void 이메일_정규식_테스트_점_없을_때_실패() {
		boolean isValidEmail = "hook3748@gmailcom".matches("\\w+@\\w+\\.\\w{2,}$");
		assertFalse(isValidEmail);
	}
}
