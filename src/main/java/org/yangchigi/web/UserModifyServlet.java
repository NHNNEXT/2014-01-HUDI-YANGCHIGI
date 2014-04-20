package org.yangchigi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.CommentRepository;
import org.yangchigi.repository.UserRepository;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger("org.yangchigi.web.UserModifyServlet");
	private UserRepository userRepository;
	
	public UserModifyServlet() {
		try {
			userRepository = new UserRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.warn("UserRepository 초기화 실패");
		}
	}

	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		 String uri = request.getRequestURI();
		 if("/usermodify".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			logger.info("userEmail: {}", userEmail);
			System.out.println("userEmail: " + userEmail);
			User user = userRepository.findByEmail(userEmail);// 세션에서 로그인된 사용자 받아옴
			String nickname = user.getNickname();
			String thumbnailName = user.getThumbnail();
			
			request.setAttribute("nickname", nickname);
			request.setAttribute("thumbnailName", thumbnailName);
			
			request.getRequestDispatcher("/userModify.jsp").forward(request, response);
		 }
	
	 }

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// System.out.println("UserModifyServlet > doPost call");
		// String msg = req.getParameter("msg");
		// String pwd = req.getParameter("pwd");
		//
		// res.setContentType("text/html;charset=euc-kr");
		// PrintWriter out =res.getWriter();
		// out.println("<html>");
		// out.println("<p>haha</p>");
		// out.println("<p>param msg: </b>" + msg + "</p>");
		// out.println("<p>param pwd: </b>" + pwd + "</p>");
		// out.println("</html>");
		String uri = request.getRequestURI();

		//ajax 처리
		if ("/usermodify/uploadThumbnail".equals(uri)) {
			//TodayServlet 베끼기
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			logger.info("userEmail: {}", userEmail);
			System.out.println("userEmail: " + userEmail);
			User user = userRepository.findByEmail(userEmail);//세션에서 로그인된 사용자 받아옴
			String nickname = user.getNickname();

			//이미지받아오는코딩하기
			request.setCharacterEncoding("euc-kr");
			String realFolder = "";
			String thumbnailName = "";
			int maxSize = 1024 * 1024 * 5;
			String encType = "euc-kr";
			String savefile = "img";
			ServletContext scontext = getServletContext();
			realFolder = "/Users/yurim/Documents/workspace2/2014-01-HUDI-YANGCHIGI/webapp/img";
			
			try {
				MultipartRequest multi = new MultipartRequest(request, realFolder,
						maxSize, encType, new DefaultFileRenamePolicy());
				Enumeration<?> files = multi.getFileNames();
				String file1 = (String) files.nextElement();
				thumbnailName = multi.getFilesystemName(file1);
				
				System.out.println(file1 + "," + thumbnailName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("thumbnailName", thumbnailName);
			request.setAttribute("nickname", nickname);
			
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
		}
			
	}

}