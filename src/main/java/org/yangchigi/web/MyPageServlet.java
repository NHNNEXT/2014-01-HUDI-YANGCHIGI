package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.support.FileUploader;
import org.yangchigi.support.MyCalendar;

public class MyPageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();

		if ("/mypage".equals(uri)) {
			IdeaRepository repository;
			try {
				repository = new IdeaRepository();
				req.setAttribute("ideaSet", repository.findListByEmail());
				req.getRequestDispatcher("/mypage.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		if ("/mypage/write".equals(uri)) {
			ArrayList<String> contentList = new ArrayList<String>();
			String time = MyCalendar.getCurrentTime().substring(0, 5);
			String contents = null;

			String img = null;
			contentList = FileUploader.upload(req);
			// AJAX
			if (contentList == null) {
				contents = req.getParameter("content");
				img = req.getParameter("img_name");
				resp.getWriter().write(time);
				uploadArticle(contents, img);
			} else {
				// NOT AJAX
				if (contentList.isEmpty())
					System.out.println("empty");
				else {
					contents = contentList.get(0);
					img = contentList.get(1);
				}
				resp.getWriter().write(time);
				uploadArticle(contents, img);

				resp.sendRedirect("/mypage");
			}
		}
	}

	private void uploadArticle(String contents, String img) {
		try {

			IdeaRepository repository = new IdeaRepository();
			Idea today = new Idea(contents, MyCalendar.getCurrentDate(),
					MyCalendar.getCurrentTime(), img, 1);
			repository.add(today);
			repository.findByEmail(contents);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
