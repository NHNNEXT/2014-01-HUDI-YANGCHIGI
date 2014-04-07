package org.yangchigi.web;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class UserTest {

	@Test
	public void getter_테스트() {
		User user = new User("hook3748@gmail.com", "hogu", "123456");
		assertThat(user.getEmail(), is("hook3748@gmail.com"));
	}
}
