//package org.yangchigi.web;
//
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.*;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.junit.*;
//import org.yangchigi.repository.UserRepository;
//
//public class TodayServletTest {
//	private HttpServletRequest mockedRequest;
//	private HttpServletResponse mockedResponse;
//	private HttpSession mockedSession;
//	private TodayRepository todayRepository;
//	private TodayServlet todayServlet;
//
//	@Before
//	public void setUp() throws IOException {
//		mockedRequest = mock(HttpServletRequest.class);
//		mockedResponse = mock(HttpServletResponse.class);
//		mockedRepository = mock(TodayRepository.class);
//		mockedSession = mock(HttpSession.class);
//		
//		todayServlet = new TodayServlet();
//
//		when(mockedRequest.getSession()).thenReturn(mockedSession);
//		
//		TodayServlet.setRepository(mockedRepository);
//	}
//	@Test
//	public void test() {
//		
//	}
//}
