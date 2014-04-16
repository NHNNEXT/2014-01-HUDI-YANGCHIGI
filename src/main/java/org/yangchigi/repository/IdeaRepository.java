package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.yangchigi.support.MyCalendar;
import org.yangchigi.web.Idea;

public class IdeaRepository implements Repository <Idea> {

	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;

	public IdeaRepository() throws ClassNotFoundException, SQLException {
		super();
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	@Override
	public ArrayList<Idea> findListByEmail() {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Idea today = null;
		ArrayList<Idea> todayList = new ArrayList<Idea>();
		
		//email 에 의한 select 구현 필
		String sql = "SELECT * FROM idea";
		try {
			pstmt = this.conn.prepareStatement(sql);
//			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				// time이다
				today = new Idea(rs.getString("content"),
								rs.getString("date"),
								rs.getString("time").substring(0, 5),
								rs.getString("img_name"),
								1);
				todayList.add(today);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todayList;
	}

	@Override
	public void add(Idea today) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO idea (content," +
				"date," +
				"time," +
				"img_name," +
				"user_id)" +
				"VALUES " +
				"(?, ?, ?, ?, ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, today.getContent());
			pstmt.setString(2, MyCalendar.getCurrentDate());
			pstmt.setString(3, MyCalendar.getCurrentTime());
			pstmt.setString(4, today.getImg_name());
			pstmt.setInt(5, 1);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Idea findByEmail(String string) {
		// TODO Auto-generated method stub
		return null;
	}



}
