package org.yangchigi.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
		sql = "INSERT INTO `employee` (`firstname`," +
									"`lastname`," +
									"`birth_date`," +
									"`cell_phone`)" +
									"VALUES " +
									"(?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Connect Success");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "Taeksoon");
			pstmt.setString(2, "Jang");
			pstmt.setString(3, "2013-04-01");
			pstmt.setString(4, "9231");
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String message = "Hello World";
		request.setAttribute("message", message);
		System.out.println("******* WRITE *******");
		getServletContext().getRequestDispatcher("/page.jsp").forward(request,
				response);
	}
}