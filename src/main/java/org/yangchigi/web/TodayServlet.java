package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.repository.CommentRepository;


public class TodayServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();

		if ("/today".equals(uri)) {
			CommentRepository repository;
			try {
				repository = new CommentRepository();
				req.setAttribute("commList", repository.findListByEmail());
				req.getRequestDispatcher("/todaymh.jsp").forward(req, resp);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();

		if ("/today/writecomment".equals(uri)) {
			try {
				CommentRepository repository = new CommentRepository();
				String content = req.getParameter("content");
				Comment comment = new Comment(content);
				repository.add(comment);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

}
