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

import org.yangchigi.support.FileUploader;
import org.yangchigi.support.MyCalendar;

public class WriteArticleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String contents = req.getParameter("contents");
		String img = req.getParameter("img");
		
		uploadArticle(contents, img);
		
	}

	private void uploadArticle(String contents, String img) {
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;
		
		String addr = "jdbc:mysql://localhost/seize";
		String user = "yangchigi";
		String pw = "yangchigi";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Driver Loading Success");
		
		sql = "INSERT INTO `today` (`contents`," +
									"`date`," +
									"`img`)" +
									"VALUES " +
									"(?, ?, ?)";
		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Connect Success");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, contents);
			pstmt.setString(2, MyCalendar.getCurrentDateTime());
			pstmt.setString(3, img);
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
