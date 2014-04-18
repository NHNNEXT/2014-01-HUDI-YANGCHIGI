package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.support.FileUploader;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		if ("/mypage".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			System.out.println("userEmail: " + userEmail);
			User user = userRepository.findByEmail(userEmail);
			logger.debug("date: {}", MyCalendar.getCurrentDate());
			request.setAttribute(
					"ideaList",
					ideaRepository.findByUserIdAndDate(user.getId(),
							MyCalendar.getCurrentDate()));
			request.getRequestDispatcher("/mypage.jsp").forward(request,
					response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		if ("/mypage/write".equals(uri)) {
			ArrayList<String> contentList = new ArrayList<String>();
			String date = MyCalendar.getCurrentDate();
			String time = MyCalendar.getCurrentTime();
			String content = null;
			String imgName = null;
			boolean isPrivate = false;
			// 유저 이메일을 받아옴.
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			logger.info("userEmail: {}", userEmail);
			System.out.println("userEmail: " + userEmail);
			User user = userRepository.findByEmail(userEmail);
			contentList = FileUploader.upload(request);

			// AJAX
			if (contentList == null) {
				content = request.getParameter("content");
				imgName = request.getParameter("imgName");
				isPrivate = request.getParameter("isPrivate") != null;
				logger.debug("isPrivate: {}", isPrivate);
				System.out.println("isPrivate: " + isPrivate);
				uploadArticle(content, date, time, imgName, isPrivate, user);
				
				time = MyCalendar.getCurrentTimeWithoutSec();
				response.getWriter().write(time);
			} else {
				// NOT AJAX
				if (contentList.isEmpty())
					System.out.println("empty");
				else {
					content = contentList.get(0);
					imgName = contentList.get(1);
				}
				response.getWriter().write(date);
				uploadArticle(content, date, time, imgName, isPrivate, user);

				response.sendRedirect("/mypage");
			}
		}
	}

	private void uploadArticle(String content, String date, String time,
			String imgName, boolean isPrivate, User user) {
		logger.debug("user.getId(): " + user.getId());
		Idea idea = new Idea(content, date, time, imgName, isPrivate,
				user.getId());
		ideaRepository.add(idea);
	}
}
