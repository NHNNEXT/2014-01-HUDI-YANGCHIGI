package org.yangchigi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.repository.CommentRepository;
import org.yangchigi.repository.UserRepository;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(name = "UserModifyServlet", urlPatterns = {"/usermodify/*"}) 
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
		String uri = request.getRequestURI();

		//ajax 처리
		if ("/usermodify/uploadThumbnail".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			logger.info("userEmail: {}", userEmail);
			User user = userRepository.findByEmail(userEmail);// 세션에서 로그인된 사용자 받아옴
			
			String nickname = request.getParameter("nickname");
			String thumbnailName = request.getParameter("thumbnailName");
			
			
			//user.setNickname(nickname);
			
			userRepository.modifyNickname(user, nickname);//DB 수정
			userRepository.modifyThumbnail(user, thumbnailName);

			//이미지받아오는코딩하기
			request.setCharacterEncoding("euc-kr");
			String realFolder = "";
			String imgName = "";
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
				imgName = multi.getFilesystemName(file1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
			request.getRequestDispatcher("/userModify.jsp").forward(request, response);
		}
			
	}

}
