package org.yangchigi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.repository.IdeaRepository;

public class MyPageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
