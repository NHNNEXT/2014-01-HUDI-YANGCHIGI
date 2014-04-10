package org.yangchigi.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.repository.Repository;
import org.yangchigi.repository.TodayRepository;
import org.yangchigi.support.MyCalendar;


public class WriteArticleServlet extends HttpServlet {
	private Repository repository;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String contents = req.getParameter("contents");
		String img = req.getParameter("img");
		String date = MyCalendar.getCurrentTime();
		
		resp.getWriter().write(date);
		uploadArticle(contents, img);
		
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
