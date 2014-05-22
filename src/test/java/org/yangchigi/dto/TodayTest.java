package org.yangchigi.dto;

import static org.junit.Assert.*;

import org.junit.Test;

public class TodayTest {

	@Test
	public void isSameUser() {
		Today today = new Today("2014-05-21", 0, 1);
		assertTrue(today.isSameUser(new User(1)));
	}
	
	@Test
	public void isNotSameUser() {
		Today today = new Today("2014-05-21", 0, 1);
		assertFalse(today.isSameUser(new User(2)));
	}
	
	@Test
	public void isNotSameUserWhenUserIsNull() {
		Today today = new Today("2014-05-21", 0, 1);
		assertFalse(today.isSameUser(null));
	}
}
