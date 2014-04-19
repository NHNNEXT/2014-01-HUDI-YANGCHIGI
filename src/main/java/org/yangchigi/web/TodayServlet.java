package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.CommentRepository;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.repository.TodayRepository;
import org.yangchigi.repository.UserRepository;

public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.TodayServlet");
	private UserRepository userRepository;
	private TodayRepository todayRepository;
	private IdeaRepository ideaRepository;

	public TodayServlet() {
		try {
			userRepository = new UserRepository();
			todayRepository = new TodayRepository();
			ideaRepository = new IdeaRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("repository 초기화 실패");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		if ("/today".equals(uri)) {
			try {
				CommentRepository commRepository = new CommentRepository();
				request.setAttribute("commList",
						commRepository.findListByEmail());
				request.getRequestDispatcher("/today.jsp").forward(request,
						response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (uri.matches("^/today/[0-9]+")) {
			int todayId = Integer.parseInt(uri.substring(7));

			Today today = todayRepository.findById(todayId);
			List<Idea> ideaList = ideaRepository.findByUserIdAndDate(
					today.getUserId(), today.getDate());

			request.setAttribute("ideaList", ideaList);
			request.setAttribute("today", today);
			request.getRequestDispatcher("/today.jsp").forward(request,
					response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		if (uri.matches("^/today/[0-9]+")) {
			int todayId = Integer.parseInt(uri.substring(7));
			int like = Integer.parseInt(request.getParameter("like"));
			Today today = todayRepository.findById(todayId);
			today.setLike(like);
			todayRepository.update(today);
			response.getWriter().write(String.valueOf(like));
		} else if ("/today/writecomment".equals(uri)) {
			try {
				String userEmail = (String) request.getSession().getAttribute(
						"user");
				logger.info("userEmail: {}", userEmail);
				System.out.println("userEmail: " + userEmail);
				User user = userRepository.findByEmail(userEmail);

				CommentRepository repository = new CommentRepository();
				String content = request.getParameter("content");

				// Comment comment = new Comment(content, user.getId(),
				// today.getId());
				Comment comment = new Comment(content, 1, 1);

				repository.add(comment);

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setRepository(TodayRepository todayRepository) {
		this.todayRepository = todayRepository;
	}
}
