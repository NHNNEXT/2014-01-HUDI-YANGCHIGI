package org.yangchigi.web;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.yangchigi.repository.TodayRepository;

public class TodayServletTest {
	private HttpServletRequest mockedRequest;
	private HttpServletResponse mockedResponse;
	private HttpSession mockedSession;
	private TodayServlet todayServlet;
	private TodayRepository mockedRepository;
	private RequestDispatcher mockedRequestDispatcher;

	@Before
	public void setUp() throws IOException {
		mockedRequest = mock(HttpServletRequest.class);
		mockedResponse = mock(HttpServletResponse.class);
		mockedRepository = mock(TodayRepository.class);
		mockedSession = mock(HttpSession.class);
		mockedRequestDispatcher = mock(RequestDispatcher.class);
		
		todayServlet = new TodayServlet();
		when(mockedRequest.getSession()).thenReturn(mockedSession);
		todayServlet.setRepository(mockedRepository);
	}
	
	@Test
	public void 투데이_페이지_보기() throws ServletException, IOException {
		// given
		Today today = new Today("2014-4-18", 17, 3);
		today.setId(1);
		when(mockedRequest.getRequestURI()).thenReturn("/today/1");
		when(mockedRequest.getParameter("date")).thenReturn("2014-4-18");
		when(mockedRequest.getRequestDispatcher(anyString())).thenReturn(mockedRequestDispatcher);
		when(mockedSession.getAttribute("user")).thenReturn("hook3748@gmail.com");
		when(mockedRepository.findById(1)).thenReturn(today);
		
		// when
		todayServlet.doGet(mockedRequest, mockedResponse);
		
		// then
		verify(mockedRequest, times(1)).getRequestURI();
		verify(mockedRepository).findById(1);
		verify(mockedRequest, times(1)).setAttribute(anyString(), isA(List.class));
		verify(mockedRequest, times(1)).setAttribute(anyString(), isA(Today.class));
	}
	
	@Test
	public void 투데이_페이지_보기2() throws ServletException, IOException {
		// given
		Today today = new Today("2014-4-17", 17, 3);
		today.setId(2);
		when(mockedRequest.getRequestURI()).thenReturn("/today/2");
		when(mockedRequest.getRequestDispatcher(anyString())).thenReturn(mockedRequestDispatcher);
		when(mockedRepository.findById(2)).thenReturn(today);
		
		// when
		todayServlet.doGet(mockedRequest, mockedResponse);
		
		// then
		verify(mockedRequest, times(1)).getRequestURI();
		verify(mockedRepository).findById(2);
		verify(mockedRequest, times(1)).setAttribute(anyString(), isA(List.class));
		verify(mockedRequest, times(1)).setAttribute(anyString(), isA(Today.class));
	}
}