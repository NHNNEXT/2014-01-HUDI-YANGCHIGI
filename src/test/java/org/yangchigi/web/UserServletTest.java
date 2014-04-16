package org.yangchigi.web;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.yangchigi.repository.Repository;
import org.yangchigi.repository.UserRepository;

public class UserServletTest {
	private HttpServletRequest mockedRequest;
	private HttpServletResponse mockedResponse;
	private HttpSession mockedSession;
	private Repository<User> mockedRepository;
	private UserServlet userServlet;
	private PrintWriter printWriter;
	private User user;

	@Before
	public void setUp() throws IOException {
		mockedRequest = mock(HttpServletRequest.class);
		mockedResponse = mock(HttpServletResponse.class);
		mockedRepository = mock(UserRepository.class);
		mockedSession = mock(HttpSession.class);
		
		userServlet = new UserServlet();
		printWriter = new PrintWriter(System.out);
		user = new User("hook3748@gmail.com", "hogu", "123456", "");

		when(mockedRequest.getSession()).thenReturn(mockedSession);
		when(mockedResponse.getWriter()).thenReturn(printWriter);
		
		userServlet.setRepository(mockedRepository);
	}
	
	@Test
	public void 회원가입() throws ServletException, IOException {
		// create mock

		// given
		when(mockedRequest.getRequestURI()).thenReturn("/user/signup");
		when(mockedRequest.getParameter("email")).thenReturn(
				"hook3748@gmail.com");
		when(mockedRequest.getParameter("nickname")).thenReturn("hogu");
		when(mockedRequest.getParameter("password")).thenReturn("123456");
		when(mockedRequest.getParameter("thumbnail")).thenReturn("");

		// when
		userServlet.doPost(mockedRequest, mockedResponse);

		// then
		verify(mockedRequest).getParameter("email");
		verify(mockedRequest).getParameter("nickname");
		verify(mockedRequest).getParameter("password");
		verify(mockedRequest, never()).getParameter("thumbnail");
		verify(mockedRepository).add(isA(User.class));
	}

	@Test
	public void 로그인_성공() throws IOException, ServletException {
		// create mock
		
		// given
		when(mockedRequest.getRequestURI()).thenReturn("/user/login");
		when(mockedRequest.getParameter("password")).thenReturn("123456");
		when(mockedRequest.getParameter("email")).thenReturn("hook3748@gmail.com");
		when(mockedRepository.findByEmail("hook3748@gmail.com")).thenReturn(user);

		// when
		userServlet.doPost(mockedRequest, mockedResponse);

		// then
		verify(mockedRequest).getParameter("password");
		verify(mockedRepository).findByEmail("hook3748@gmail.com");
		verify(mockedSession).setAttribute(anyString(), anyString());
	}
	
	@Test
	public void 로그인_실패() throws IOException, ServletException {
		// create mock
		
		// given
		when(mockedRequest.getRequestURI()).thenReturn("/user/login");
		when(mockedRequest.getParameter("password")).thenReturn("1234567");
		when(mockedRequest.getParameter("email")).thenReturn("hook3748@gmail.com");
		when(mockedRepository.findByEmail("hook3748@gmail.com")).thenReturn(user);
		
		// when
		userServlet.doPost(mockedRequest, mockedResponse);
		
		// then
		verify(mockedRequest, times(1)).getRequestURI();
		verify(mockedRequest).getParameter("password");
		verify(mockedRepository).findByEmail("hook3748@gmail.com");
		verify(mockedSession, never()).setAttribute((String) any(), any());
	}
	
	@Test
	public void 로그아웃() throws ServletException, IOException {
		// create mock
		
		// given
		when(mockedRequest.getRequestURI()).thenReturn("/user/logout");
		
		// when
		userServlet.doPost(mockedRequest, mockedResponse);
		
		// then
		verify(mockedRequest, times(1)).getRequestURI();
		verify(mockedSession).removeAttribute(anyString());
	}
}