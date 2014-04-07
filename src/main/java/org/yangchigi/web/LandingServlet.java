package org.yangchigi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LandingServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LandingServlet call");
		
		String message = "hi yo man";
		request.setAttribute("message", message);
		
		getServletContext().getRequestDispatcher("/landing.jsp").forward(request,
				response);
	}
}
