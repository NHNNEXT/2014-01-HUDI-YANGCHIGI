package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.yangchigi.dto.Comment;

public class CommentRepository implements Repository<Comment>{
	private Connection conn;
	
	public CommentRepository() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	@Override
	public void add(Comment comm){
		PreparedStatement pstmt;

		String sql = "INSERT INTO comment (content," +
				"user_id," +
				"today_id)" +
				"VALUES " +
				"(?, ?, ?)";
		try {
			this.conn = DriverManager.getConnection(addr, user, pw);
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, comm.getContent());
			pstmt.setInt(2, comm.getUserId());
			pstmt.setInt(3, comm.getTodayId());
			pstmt.execute();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Comment> findListByTodayId(int todayId){
		PreparedStatement pstmt;
		ResultSet rs = null;
		Comment comm = null;
		ArrayList<Comment> commList = new ArrayList<Comment>();
		
		String sql = "SELECT * FROM comment WHERE today_id = ?";
		try {
			this.conn = DriverManager.getConnection(addr, user, pw);
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, todayId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comm = new Comment(rs.getString("content"),rs.getInt("user_id"), rs.getInt("today_id"));
				commList.add(comm);				
				System.out.println(comm.toString());
			}
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commList;
	}

	@Override
	public Comment findById(int id) {
		return null;
	}

}