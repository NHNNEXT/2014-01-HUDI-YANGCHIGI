package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodayRepository implements Repository<Today> {
	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;
	
	public TodayRepository() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}
	
	public Today findByDateAndUserId(String date, int userId) {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Today today = null;

		String sql = "SELECT * FROM `today` WHERE (user_id = ? AND date = ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(rs.getString("date"), rs.getInt("like"), rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return today;
	}

	@Override
	public Today findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Today data) {
		// TODO Auto-generated method stub

	}
}
