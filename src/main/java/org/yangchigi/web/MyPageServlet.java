package org.yangchigi.web;

import java.io.BufferedReader;
import java.io.IOException;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.dto.Idea;
import org.yangchigi.dto.User;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.repository.SingletonRepository;
import org.yangchigi.repository.UserRepository;
import org.yangchigi.support.MyCalendar;

// server path
//@MultipartConfig(location = "/home/yangchigi/apache-tomcat-7.0.52/webapps/ROOT/image", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024, maxRequestSize = 1024 * 1024 * 20)
//@MultipartConfig(location = "/Users/jehyeok/yangchigi/2014-01-HUDI-YANGCHIGI/webapp/image", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024, maxRequestSize = 1024 * 1024 * 20)
@MultipartConfig(location = "/Users/yurim/Documents/workspace2/2014-01-HUDI-YANGCHIGI/webapp/image", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024, maxRequestSize = 1024 * 1024 * 20)
//@MultipartConfig(location = "/Users/kimminhyeok/git/2014-01-HUDI-YANGCHIGI/webapp/image", maxFileSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024, maxRequestSize = 1024 * 1024 * 20)
@WebServlet(name = "MyPageServlet", urlPatterns = { "/mypage/*" })
public class MyPageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(MyPageServlet.class.getName());
	private static final long serialVersionUID = 1L;
	private IdeaRepository ideaRepository;
	private UserRepository userRepository;

	public MyPageServlet() {
		ideaRepository = SingletonRepository.getIdeaRepository();
		userRepository = SingletonRepository.getUserRepository();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		if ("/mypage".equals(uri)) {
			String userEmail = (String) request.getSession().getAttribute(
					"user");
			User user = userRepository.findByEmail(userEmail);
			request.setAttribute(
					"ideaList",
					ideaRepository.findByUserIdAndDate(user.getId(),
							MyCalendar.getCurrentDate()));
			String nickname = user.getNickname();
			String thumbnailName = user.getThumbnail();

			//헤더때문에 추가
			request.setAttribute("nickname", nickname);
			request.setAttribute("thumbnailName", thumbnailName);
			
			request.getRequestDispatcher("/mypage.jsp").forward(request,
					response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		HashMap<String, String> contentsMap;

		if ("/mypage/write".equals(uri)) {
			contentsMap = getContentsListAndUpload(request);

			if (hasError(contentsMap))
				return;

			String content = contentsMap.get("content");
			String date = MyCalendar.getCurrentDate();
			String time = MyCalendar.getCurrentTime();
			String imgName = null;
			if (contentsMap.containsKey("imgName"))
				imgName = contentsMap.get("imgName");
			boolean isPrivate = contentsMap.containsKey("isPrivate");

			String userEmail = (String) request.getSession().getAttribute(
					"user");
			User user = userRepository.findByEmail(userEmail);

			Idea idea = new Idea(0, content, date, time, imgName, isPrivate,
					user.getId());
			ideaRepository.add(idea);

			time = MyCalendar.getCurrentTimeWithoutSec();
			response.getWriter().write(time);

		} 
		else if("/mypage/ideaDelete".equals(uri)) {
			logger.debug(request.getParameter("ideaId"));
			String ideaId = request.getParameter("ideaId");
			logger.debug("" + Integer.parseInt(ideaId));
			ideaRepository.destroy(Integer.parseInt(ideaId));
			response.getWriter().write("success");
		}
	}

	private boolean hasError(HashMap<String, String> contentsMap) {
		for (String key : contentsMap.keySet()) {
			String value = contentsMap.get(key);
			// 200자 이상일 경우 
			if(value.length() > 200) return true;
		}
		return false;
	}
	

	private HashMap<String, String> getContentsListAndUpload(
			HttpServletRequest request) {
		Part filePart = null;
		HashMap<String, String> contentsMap = new HashMap<String, String>();
		String fileName = null;
		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("content")) {
					String paramValue = getStringFromStream(part
							.getInputStream());
					contentsMap.put("content", paramValue.trim());
				}
				if (part.getName().equals("isPrivate")) {
					String paramValue = getStringFromStream(part
							.getInputStream());
					contentsMap.put("isPrivate", paramValue.trim());
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
