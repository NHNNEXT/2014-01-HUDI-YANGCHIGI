package org.yangchigi.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.repository.Repository;
import org.yangchigi.repository.IdeaRepository;
import org.yangchigi.support.FileUploader;
import org.yangchigi.support.MyCalendar;

public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		ArrayList<String> contentList = new ArrayList<String>();
		String date = MyCalendar.getCurrentTime();
		String contents = null;
		 String img = null;
		 contentList = FileUploader.upload(req);
		 // AJAX
		 if(contentList == null){
			 contents = req.getParameter("content");
			 img = req.getParameter("img");
			 resp.getWriter().write(date);
			 uploadArticle(contents, img);
		 }
		 else {
			// NOT AJAX		 
			 if(contentList.isEmpty())
				 System.out.println("empty");
			 else{
				 contents = contentList.get(0);
				 img = contentList.get(1);
			 }
			 
			 resp.getWriter().write(date);
			 uploadArticle(contents, img);
			 
			 resp.sendRedirect("/mypage");
		 }
		
    }
	
	private void uploadArticle(String contents, String img) {
		
		try {
			IdeaRepository repository = new IdeaRepository();
			Idea today = new Idea(contents, MyCalendar.getCurrentTime(), img, 1);
			repository.add(today);
		} catch (Exception e) {}
	}
}
