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
import org.yangchigi.support.FileUploader;
import org.yangchigi.support.MyCalendar;

public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger("org.yangchigi.web.WriteArticleServlet");
	private IdeaRepository repository;

	public WriteArticleServlet() {
		try {
			this.repository = new IdeaRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("IdeaRepository 초기화 실패");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> contentList = new ArrayList<String>();
		
		String date = MyCalendar.getCurrentTime();
		String content = null;
		String imgName = null;
		contentList = FileUploader.upload(request);

		// AJAX
		if (contentList == null) {
			content = request.getParameter("content");
			imgName = request.getParameter("img");
			response.getWriter().write(date);
			uploadArticle(content, imgName);
		} else {
			// NOT AJAX
			if (contentList.isEmpty())
				System.out.println("empty");
			else {
				content = contentList.get(0);
				imgName = contentList.get(1);
			}

			response.getWriter().write(date);
			uploadArticle(content, imgName);

			response.sendRedirect("/mypage");
		}
	}

	private void uploadArticle(String content, String imgName) {
		Idea today = new Idea(content, "date", "time", imgName, 1);
		repository.add(today);
	}
}
