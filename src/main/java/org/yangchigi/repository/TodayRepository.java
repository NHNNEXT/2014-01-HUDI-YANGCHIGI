package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangchigi.dto.Today;
import org.yangchigi.support.MyString;

public class TodayRepository implements Repository<Today> {

	public TodayRepository() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Today findByDateAndUserId(String date, int userId) {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Today today = null;

		String sql = "SELECT * FROM `today` WHERE (user_id = ? AND date = ?)";
		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(MyString.replace(rs.getString("date")), rs.getInt("like"),
						rs.getInt("user_id"));
				today.setId(rs.getInt("id"));
			}
			
			pstmt.close();
			rs.close();
			conn.close();
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
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(MyString.replace(rs.getString("date")), rs.getInt("like"),
						rs.getInt("user_id"));
				today.setId(rs.getInt("id"));
			}
			
			pstmt.close();
			rs.close();
			conn.close();
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
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, today.getDate());
			pstmt.setInt(2, today.getLike());
			pstmt.setInt(3, today.getUserId());
			pstmt.execute();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Today today) {
		PreparedStatement pstmt;

		String sql = "UPDATE `today` SET `date` = ?, `like` = ?, `user_id` = ? "
				+ "WHERE `id` = ?";
		
		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, today.getDate());
			pstmt.setInt(2, today.getLike());
			pstmt.setInt(3, today.getUserId());
			pstmt.setInt(4, today.getId());
			pstmt.execute();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Today> findAll() {
		List<Today> todayList = new ArrayList<Today>();
		PreparedStatement pstmt;
		ResultSet rs = null;
		Today today = null;

		String sql = "SELECT * FROM `today`";
		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(MyString.replace(rs.getString("date")), rs.getInt("like"),
						rs.getInt("user_id"));
				today.setId(rs.getInt("id"));
				todayList.add(today);
			}
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todayList;
	}
	
	public List<Today> findListByUserId(int userid) {
		List<Today> todayList = new ArrayList<Today>();
		PreparedStatement pstmt;
		ResultSet rs = null;
		Today today = null;

		String sql = "SELECT * FROM `today` WHERE user_id = ?";
		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(MyString.replace(rs.getString("date")), rs.getInt("like"),
						rs.getInt("user_id"));
				today.setId(rs.getInt("id"));
				todayList.add(today);
			}
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todayList;
	}
	
}
