package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.yangchigi.web.Comment;

public class CommentRepository implements Repository<Comment>{
	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;
	
	public CommentRepository() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	@Override
	public void add(Comment comm) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO comment (content," +
				"user_id," +
				"today_id)" +
				"VALUES " +
				"(?, ?, ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, comm.getContent());
//			pstmt.setInt(2, comm.getUserId());
//			pstmt.setInt(3, comm.getTodayId());
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 1);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Comment> findListByEmail() {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Comment comm = null;
		ArrayList<Comment> commList = new ArrayList<Comment>();
		
		//email 에 의한 select 구현 필
		String sql = "SELECT * FROM comment";
		try {
			pstmt = this.conn.prepareStatement(sql);
//			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				comm = new Comment(rs.getString("content"),1, 1);
				commList.add(comm);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commList;
	}

	@Override
	public Comment findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}