package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.DuplicateEmailException;
import org.yangchigi.repository.DuplicateNicknameException;
import org.yangchigi.repository.UserRepository;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.UserServlet");
	private UserRepository repository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		try {
			repository = new UserRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("UserRepository 초기화 실패");
		}
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
		String uri = request.getRequestURI();
		boolean caught = false;
		
		try {
			if ("/user/signup".equals(uri)) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");
				
				boolean isValidEmail = email.matches("^(\\w+@\\w+\\.\\w{2,})$");
				boolean isValidPassword = password.length() > 0 && password.length() <= 20;
				boolean isValidNickname = nickname.length() > 0 && nickname.length() <= 10;
				
				if (isValidEmail && isValidPassword) {
					User user = new User(request.getParameter("email"),
							request.getParameter("nickname"),
							password, "");
					try {
						repository.add(user);
						caught = false;
					} catch (DuplicateEmailException e) {
						response.getWriter().write("duplicate email");
						caught = true;
					} catch (DuplicateNicknameException e) {
						response.getWriter().write("duplicate nickname");
						caught = true;
					} finally {
						if (!caught) response.getWriter().write("success");
					}
				}
			} else if ("/user/login".equals(uri)) {
				try {
					User user = repository.findByEmail(request
							.getParameter("email"));
					logger.info("user.getPassword(): {}", user.getPassword());
						logger.info("login success");
						HttpSession session = request.getSession();
						session.setAttribute("user", user.getEmail());
						response.getWriter().write("success");
				} catch(Exception e) {
					logger.info("login fail");
					response.getWriter().write("fail");
				}
			} else if ("/user/logout".equals(uri)) {
				HttpSession session = request.getSession();
				session.removeAttribute("user");
				response.getWriter().write("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}
}
