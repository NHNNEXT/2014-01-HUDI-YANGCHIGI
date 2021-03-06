package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangchigi.dto.Idea;
import org.yangchigi.support.MyString;

public class IdeaRepository implements Repository<Idea> {

	public IdeaRepository() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Idea> findListByEmail() throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Idea idea = null;
		ArrayList<Idea> ideaList = new ArrayList<Idea>();

		Connection conn = DriverManager.getConnection(addr, user, pw);
		
		
		String sql = "SELECT * FROM idea";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idea = new Idea(rs.getInt("id"), MyString.replace(rs.getString("content")), MyString.replace(rs.getString("time")),
						MyString.replace(rs.getString("date")), MyString.replace(rs.getString("imgName")),
						rs.getBoolean("is_private"), rs.getInt("user_id"));
				ideaList.add(idea);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ideaList;
	}

	public void add(Idea idea) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO `idea` (`content`, `date`, `time`, `img_name`, `is_private`, `user_id`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idea.getContent());
			pstmt.setString(2, idea.getDate());
			pstmt.setString(3, idea.getTime());
			pstmt.setString(4, idea.getImgName());
			pstmt.setBoolean(5, idea.getIsPrivate());
			pstmt.setInt(6, idea.getUserId());
			pstmt.execute();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Idea findById(int id) {
		return null;
	}

	public List<Idea> findByUserIdAndDate(int userId, String date) {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Idea idea = null;
		List<Idea> ideaList = new ArrayList<Idea>();

		String sql = "SELECT * FROM `idea` WHERE (user_id = ? AND date = ?)";
		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				idea = new Idea(rs.getInt("id"), MyString.replace(rs.getString("content")), MyString.replace(rs.getString("date")),
						MyString.replace(rs.getString("time").substring(0, 5)), MyString.replace(rs.getString("img_name")),
						rs.getBoolean("is_private"), rs.getInt("user_id"));
				ideaList.add(idea);
			}
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ideaList;
	}

	public void destroy(int ideaId) {
		System.out.println("ideaId: " + ideaId);
		PreparedStatement pstmt;
		String sql = "DELETE FROM `idea` WHERE id = ?";
		try {
			Connection conn = DriverManager.getConnection(addr, user, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ideaId);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
