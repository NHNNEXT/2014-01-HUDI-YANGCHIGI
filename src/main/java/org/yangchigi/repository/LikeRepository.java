package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangchigi.web.Like;

public class LikeRepository implements Repository<Like>{
	private Connection conn;

	public LikeRepository() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	public Like findByUserIdAndTodayId(int userId, int todayId) {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Like like = null;

		String sql = "SELECT * FROM `like` WHERE (user_id = ? AND today_id = ?)";
		try {
			this.conn = DriverManager.getConnection(addr, user, pw);

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, todayId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				like = new Like(rs.getInt("user_id"), rs.getInt("today_id"));
				like.setId(rs.getInt("id"));
			}
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return like;
	}

	public void add(Like like) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO `like` (`user_id`, `today_id`) "
				+ "VALUES (?, ?)";
		try {
			this.conn = DriverManager.getConnection(addr, user, pw);

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, like.getUserId());
			pstmt.setInt(2, like.getTodayId());
			pstmt.execute();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Like like) {
		PreparedStatement pstmt;

		String sql = "DELETE FROM `like` WHERE (`user_id` = ? AND `today_id` = ?)";
		try {
			this.conn = DriverManager.getConnection(addr, user, pw);

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, like.getUserId());
			pstmt.setInt(2, like.getTodayId());
			pstmt.execute();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Like findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
