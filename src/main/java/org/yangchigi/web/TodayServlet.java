package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.CommentRepository;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.repository.UserRepository;


public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.TodayServlet");
	private UserRepository userRepository;
	
	public TodayServlet() {
		try {
			userRepository = new UserRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("IdeaRepository 초기화 실패");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();

	
		if ("/today".equals(uri)) {
			CommentRepository repository;
			try {
				repository = new CommentRepository();
				
				
				req.setAttribute("commList", repository.findListByEmail());
				req.getRequestDispatcher("/today.jsp").forward(req, resp);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		if ("/today/writecomment".equals(uri)) {
			try {
				String userEmail = (String) request.getSession().getAttribute(
						"user");
				logger.info("userEmail: {}", userEmail);
				System.out.println("userEmail: " + userEmail);
				User user = userRepository.findByEmail(userEmail);
				
				
				CommentRepository repository = new CommentRepository();
				String content = request.getParameter("content");
				
				
				
//				Comment comment = new Comment(content, user.getId(), today.getId());
				Comment comment = new Comment(content, 1, 1);
				
				repository.add(comment);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

}
