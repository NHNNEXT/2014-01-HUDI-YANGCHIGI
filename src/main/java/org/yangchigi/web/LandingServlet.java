package org.yangchigi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "LandingServlet", urlPatterns = {""}) 
public class LandingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.LandingServlet");
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LandingServlet");
		logger.info("LandingServlet");
		HttpSession session = request.getSession();
		if (null != session.getAttribute("user")) {
			response.sendRedirect("/mypage");
		} else {
			getServletContext().getRequestDispatcher("/landing.jsp").forward(
					request, response);
		}
	}
}
