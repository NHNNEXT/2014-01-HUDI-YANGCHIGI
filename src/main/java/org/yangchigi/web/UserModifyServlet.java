package org.yangchigi.web;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.yangchigi.dto.User;
import org.yangchigi.repository.SingletonRepository;
import org.yangchigi.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "UserModifyServlet", urlPatterns = { "/usermodify/*" })
@MultipartConfig(location = "/Users/yurim/Documents/workspace2/2014-01-HUDI-YANGCHIGI/webapp/image", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024, maxRequestSize = 1024 * 1024 * 20)
//@MultipartConfig(location = "/Users/jehyeok/yangchigi/2014-01-HUDI-YANGCHIGI/webapp/img", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024, maxRequestSize = 1024 * 1024 * 20)
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.UserModifyServlet");
	private UserRepository userRepository;

	public UserModifyServlet() {
		userRepository = SingletonRepository.getUserRepository();
		logger.warn("UserRepository 초기화 실패");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		if ("/usermodify".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			logger.info("userEmail: {}", userEmail);
			User user = userRepository.findByEmail(userEmail);// 세션에서 로그인된 사용자
																// 받아옴
			String nickname = user.getNickname();
			String thumbnailName = user.getThumbnail();

			request.setAttribute("nickname", nickname);
			request.setAttribute("thumbnailName", thumbnailName);

			request.getRequestDispatcher("/userModify.jsp").forward(request,
					response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		HashMap<String, String> contentsMap;

		// ajax 처리
		if ("/usermodify/upload".equals(uri)) {
			contentsMap = getContentsListAndUpload(request);
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			logger.info("userEmail: {}", userEmail);
			User user = userRepository.findByEmail(userEmail);// 세션에서 로그인된 사용자
																// 받아옴

			String nickname = contentsMap.get("nickname");
			String imgName = null;
			System.out.println(contentsMap.toString());
			if (contentsMap.containsKey("imgName"))
				imgName = contentsMap.get("imgName");
	
			userRepository.modifyNickname(user, nickname);// DB 수정
			userRepository.modifyThumbnail(user, imgName);
			request.getRequestDispatcher("/userModify.jsp").forward(request,
					response);
		}
	}

	private HashMap<String, String> getContentsListAndUpload(
			HttpServletRequest request) {
		Part filePart = null;
		HashMap<String, String> contentsMap = new HashMap<String, String>();
		String fileName = null;
		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("nickname")) {
					String paramValue = getStringFromStream(part
							.getInputStream());
					contentsMap.put("nickname", paramValue.trim());
				}
				if (part.getName().equals("imgName")) {
					filePart = part;
					for (String headerName : part.getHeaderNames()) {
						if (part.getHeader(headerName).contains("filename=")) {
							String filePartHeader = part.getHeader(headerName);
							fileName = filePartHeader.split("filename=\"")[1];
							fileName = fileName.substring(0,
									fileName.length() - 1);
							contentsMap.put("imgName", fileName);
						}
					}
				}
			}

			if (fileName != null)
				filePart.write(fileName);

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		return contentsMap;

	}

	public String getStringFromStream(InputStream stream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,
				"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
