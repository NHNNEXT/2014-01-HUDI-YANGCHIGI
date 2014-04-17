package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.repository.UserRepository;
import org.yangchigi.support.FileUploader;
import org.yangchigi.support.MyCalendar;

public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.WriteArticleServlet");
	private IdeaRepository ideaRepository;
	private UserRepository userRepository;

	public WriteArticleServlet() {
		try {
			this.ideaRepository = new IdeaRepository();
			this.userRepository = new UserRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("IdeaRepository 초기화 실패");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> contentList = new ArrayList<String>();

		String dateTime = MyCalendar.getCurrentDateTime();
		String date = MyCalendar.getDate(dateTime);
		String time = MyCalendar.getTime(dateTime);
		String content = null;
		String imgName = null;
		boolean isPrivate = false;
		// 유저 이메일을 받아옴.
		String userEmail = (String) request.getSession().getAttribute("user");
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

			response.getWriter().write(time);
			uploadArticle(content, date, time, imgName, isPrivate, user);
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

	private void uploadArticle(String content, String date, String time,
			String imgName, boolean isPrivate, User user) {
		logger.debug("user.getId(): " + user.getId());
		Idea idea = new Idea(content, date, time, imgName, isPrivate,
				user.getId());
		ideaRepository.add(idea);
	}
}