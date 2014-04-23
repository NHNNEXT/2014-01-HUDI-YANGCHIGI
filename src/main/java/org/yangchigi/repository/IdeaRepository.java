package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangchigi.web.Idea;

public class IdeaRepository implements Repository<Idea> {

	
	private Connection conn;

	public IdeaRepository() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	public ArrayList<Idea> findListByEmail() {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Idea idea = null;
		ArrayList<Idea> ideaList = new ArrayList<Idea>();

		String sql = "SELECT * FROM idea";
		try {
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idea = new Idea(rs.getString("content"), rs.getString("time"),
						rs.getString("date"), rs.getString("imgName"),
						rs.getBoolean("is_private"), rs.getInt("user_id"));
				ideaList.add(idea);
			}
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
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, idea.getContent());
			pstmt.setString(2, idea.getDate());
			pstmt.setString(3, idea.getTime());
			pstmt.setString(4, idea.getImgName());
			pstmt.setBoolean(5, idea.getIsPrivate());
			pstmt.setInt(6, idea.getUserId());
			pstmt.execute();
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
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idea = new Idea(rs.getString("content"), rs.getString("date"),
						rs.getString("time").substring(0, 5), rs.getString("img_name"),
						rs.getBoolean("is_private"), rs.getInt("user_id"));
				ideaList.add(idea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ideaList;
	}
}
