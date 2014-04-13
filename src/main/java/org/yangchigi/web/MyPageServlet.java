package org.yangchigi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yangchigi.repository.Repository;
import org.yangchigi.repository.TodayRepository;

public class MyPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TodayRepository repository;
		try {
			repository = new TodayRepository();
			req.setAttribute("todaySet", repository.findByEmail(""));
			req.getRequestDispatcher("/mypage.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
