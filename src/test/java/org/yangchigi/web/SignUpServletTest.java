package org.yangchigi.web;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.yangchigi.repository.Repository;
import org.yangchigi.repository.UserRepository;

import static org.mockito.Mockito.*;

public class SignUpServletTest {

	@Test
	public void 회원가입_POST_DATA_DB에_저() {
		// create Mock
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		
		// when
		when(mockedRequest.getParameter("email")).thenReturn("hook3748@gmail.com");
		when(mockedRequest.getParameter("nickname")).thenReturn("hogu");
		when(mockedRequest.getParameter("password")).thenReturn("123456");
	}
}
