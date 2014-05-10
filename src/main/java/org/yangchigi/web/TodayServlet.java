package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.CommentRepository;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.repository.LikeRepository;
import org.yangchigi.repository.TodayRepository;
import org.yangchigi.repository.UserRepository;
import org.yangchigi.support.MyCalendar;
import org.yangchigi.web.Comment;
import org.yangchigi.web.Idea;
import org.yangchigi.web.Like;
import org.yangchigi.web.Today;
import org.yangchigi.web.User;

@WebServlet(name = "TodayServlet", urlPatterns = { "/today/*" })
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.TodayServlet");
	private UserRepository userRepository;
	private TodayRepository todayRepository;
	private IdeaRepository ideaRepository;
	private LikeRepository likeRepository;
	private CommentRepository commRepository;

	public TodayServlet() {
		try {
			userRepository = new UserRepository();
			todayRepository = new TodayRepository();
			ideaRepository = new IdeaRepository();
			likeRepository = new LikeRepository();
			commRepository = new CommentRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("repository 초기화 실패");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();

		if (uri.matches("^/today/[0-9]+")) {
			// today Id 받기. /today/9 일 경우 todayId == 9
			int todayId = Integer.parseInt(uri.substring(7));
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			// 로그인한 유저 & 요청한 투데이
			User user = userRepository.findByEmail(userEmail);
			Today today = todayRepository.findById(todayId);

			// 투데이에 속한 아이디어 리스트
			List<Idea> ideaList = ideaRepository.findByUserIdAndDate(
					today.getUserId(), today.getDate());

			// 비공개 설정한 idea 필터
			if (user.getId() != today.getUserId()) {
				for (int i = 0; i < ideaList.size(); i++) {
					if (ideaList.get(i).getIsPrivate())
						ideaList.remove(i);
				}
			}
			System.out.println(ideaList.toString());
			// 사용자가 투데이 like 상태인지 확인
			Like like = likeRepository.findByUserIdAndTodayId(user.getId(),
					todayId);

			request.setAttribute("ideaList", ideaList);
			request.setAttribute("today", today);
			request.setAttribute("year", today.getDate().split("-")[0]);
			request.setAttribute("month", today.getDate().split("-")[1]);
			request.setAttribute("day", today.getDate().split("-")[2]);
			request.setAttribute("isLiked", like != null);
			request.setAttribute("commList", commRepository.findListByTodayId(todayId));
			request.getRequestDispatcher("/today.jsp").forward(request,
					response);
		} else if ("/today".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			// 로그인한 유저 & 요청한 투데이
			User user = userRepository.findByEmail(userEmail);
			
			List<Today> todayList = todayRepository.findAll();
			Map<Today, List<Idea>> todayAndIdeasMap = new HashMap<Today, List<Idea>>();
			
			Iterator<Today> todayIterator = todayList.iterator();
			while (todayIterator.hasNext()) {
				Today today = todayIterator.next();
				todayAndIdeasMap.put(today, ideaRepository.findByUserIdAndDate(user.getId(), today.getDate()));
			}
			
//			request.setAttribute("todayList", todayList);
			request.setAttribute("todayAndIdeasMap", todayAndIdeasMap);
			request.getRequestDispatcher("/todays.jsp").forward(request,
					response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		// 투데이 like column 업데이트.
		if (uri.matches("^/today/[0-9]+")) {
			// today Id 받기. /today/9 일 경우 todayId == 9
			int todayId = Integer.parseInt(uri.substring(7));
			// 현재 투데이 like 수
			int likeNum = Integer.parseInt(request.getParameter("like"));
			// 현재 보고있는 투데이
			Today today = todayRepository.findById(todayId);
			today.setLike(likeNum);

			String userEmail = (String) request.getSession().getAttribute("user");

			User user = userRepository.findByEmail(userEmail);
			Like like = likeRepository.findByUserIdAndTodayId(user.getId(),
					todayId);

			// 현재 유저가 이미 like를 누른 상태라면
			if (like != null) {
				likeRepository.delete(like);
				// 현재 유저가 like을 누르지 않은 상태라면
			} else {
				like = new Like(user.getId(), todayId);
				likeRepository.add(like);
			}

			todayRepository.update(today);
			response.getWriter().write(String.valueOf(likeNum));
		} else if (uri.matches("^/today/[0-9]+/writecomment")) {
			try {
				int todayId = Integer.parseInt(uri.split("/")[2]);
				System.out.println("todayid = " + todayId);
				String userEmail = (String) request.getSession().getAttribute(
						"user");
				User user = userRepository.findByEmail(userEmail);
				logger.info("userEmail: {}", userEmail);

				CommentRepository repository = new CommentRepository();
				String content = request.getParameter("content");

				 Comment comment = new Comment(content, user.getId(), todayId);

				repository.add(comment);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if ("/today".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			User user = userRepository.findByEmail(userEmail);
			
			Today today = new Today(MyCalendar.getCurrentDate(), 0, user.getId());
			todayRepository.add(today);
			
			response.getWriter().write("success");
		}
	}

	public void setRepository(TodayRepository todayRepository) {
		this.todayRepository = todayRepository;
	}
}
