package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangchigi.web.Today;

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
				today = new Today(rs.getString("date"), rs.getInt("like"),
						rs.getInt("user_id"));
				today.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return today;
	}

	@Override
	public Today findById(int id) {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Today today = null;

		String sql = "SELECT * FROM `today` WHERE id = ?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(rs.getString("date"), rs.getInt("like"),
						rs.getInt("user_id"));
				today.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return today;
	}

	@Override
	public void add(Today today) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO `today` (`date`, `like`, `user_id`) "
				+ "VALUES (?, ?, ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, today.getDate());
			pstmt.setInt(2, today.getLike());
			pstmt.setInt(3, today.getUserId());
			System.out.println(pstmt.toString());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Today today) {
		PreparedStatement pstmt;

		String sql = "UPDATE `today` SET `date` = ?, `like` = ?, `user_id` = ? "
				+ "WHERE `id` = ?";
		
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, today.getDate());
			pstmt.setInt(2, today.getLike());
			pstmt.setInt(3, today.getUserId());
			pstmt.setInt(4, today.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
