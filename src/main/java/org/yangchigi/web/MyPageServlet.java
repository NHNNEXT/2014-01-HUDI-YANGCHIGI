package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.repository.UserRepository;
import org.yangchigi.support.MyCalendar;

public class MyPageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.MyPageServlet");
	private IdeaRepository ideaRepository;
	private UserRepository userRepository;

	public MyPageServlet() {
		try {
			ideaRepository = new IdeaRepository();
			userRepository = new UserRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("IdeaRepository 초기화 실패");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userEmail = (String) request.getSession().getAttribute("user");
		System.out.println("userEmail: " + userEmail);
		User user = userRepository.findByEmail(userEmail);
		String dateTime = MyCalendar.getCurrentDateTime();
		logger.debug("date: {}", MyCalendar.getDate(dateTime));
		System.out.println("date: " + MyCalendar.getDate(dateTime));
		request.setAttribute(
				"ideaList",
				ideaRepository.findByUserIdAndDate(user.getId(),
						MyCalendar.getDate(dateTime)));
		request.getRequestDispatcher("/mypage.jsp").forward(request, response);
	}
}
