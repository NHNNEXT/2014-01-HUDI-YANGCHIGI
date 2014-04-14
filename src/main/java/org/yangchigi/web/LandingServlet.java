package org.yangchigi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LandingServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LandingServlet");
		HttpSession session = request.getSession();
		if (null != session.getAttribute("user")) {
			getServletContext().getRequestDispatcher("/mypage.jsp").forward(
					request, response);
		} else {
			getServletContext().getRequestDispatcher("/landing.jsp").forward(
					request, response);
		}
	}
}
