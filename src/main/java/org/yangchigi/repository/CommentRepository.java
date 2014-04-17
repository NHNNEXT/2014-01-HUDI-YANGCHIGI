package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.yangchigi.support.MyCalendar;
import org.yangchigi.web.Comment;
import org.yangchigi.web.Idea;

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
	public Comment findByEmail(String string) {
		
		
		return null;
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
//			pstmt.setInt(2, comm.getUser_id());
//			pstmt.setInt(3, comm.getToday_id());
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 1);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
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
				
				// time이다
				comm = new Comment(rs.getString("content"));
				commList.add(comm);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commList;
	}

}
