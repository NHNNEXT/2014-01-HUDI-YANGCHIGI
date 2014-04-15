package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.yangchigi.repository.Repository;
import org.yangchigi.repository.UserRepository;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Repository<User> repository;
		try {
			if ("/user/signup".equals(request.getRequestURI())) {
				// logger.debug("UserServlet > /user/signup");
				System.out.println("UserServlet > /user/signup");
				repository = new UserRepository();

				User user = new User(request.getParameter("email"),
						request.getParameter("nickname"),
						request.getParameter("password"), "");
				repository.add(user);
				response.getWriter().write("success");
			} else if ("/user/login".equals(request.getRequestURI())) {
				repository = new UserRepository();
				User user = repository.findByEmail(request
						.getParameter("email"));
				if (user.getPassword().equals(request.getParameter("password"))) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user.getEmail());
					System.out.println("login success");
					response.getWriter().write("success");
				} else {
					System.out.println("login fail");
					response.getWriter().write("fail");
				}
			} else if ("/user/logout".equals(request.getRequestURI())) {
				HttpSession session = request.getSession();
				session.removeAttribute("user");
				response.getWriter().write("success");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
