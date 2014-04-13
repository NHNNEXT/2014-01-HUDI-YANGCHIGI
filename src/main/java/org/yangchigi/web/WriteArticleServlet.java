package org.yangchigi.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.yangchigi.repository.Repository;
import org.yangchigi.repository.TodayRepository;
import org.yangchigi.support.FileUploader;
import org.yangchigi.support.MyCalendar;

public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Repository repository;
    
   

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 String contents = req.getParameter("contents");
		 String img = req.getParameter("img");
		 String date = MyCalendar.getCurrentTime();

		 if(!FileUploader.upload(req)){
			 // 에러 처리
		 }
		
		 resp.getWriter().write(date);
		 uploadArticle(contents, img);
		 
		 req.getRequestDispatcher("/mypage.jsp").forward(req, resp);
		
    }
	
	private void uploadArticle(String contents, String img) {
		Repository repository;
		try {
			repository = new TodayRepository();
			Today today = new Today(contents, MyCalendar.getCurrentDateTime(), img);
			repository.add(today);
			repository.findByEmail(contents);
		} catch (Exception e) {}
	}
}
