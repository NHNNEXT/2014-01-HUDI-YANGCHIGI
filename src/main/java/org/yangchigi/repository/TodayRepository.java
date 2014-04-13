package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.yangchigi.support.MyCalendar;
import org.yangchigi.web.Today;

public class TodayRepository implements Repository <Today> {

	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;

	public TodayRepository() throws ClassNotFoundException, SQLException {
		super();
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	@Override
	public ArrayList<Today> findListByEmail() {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Today today = null;
		ArrayList<Today> todayList = new ArrayList<Today>();
		
		//email 에 의한 select 구현 필
		String sql = "SELECT * FROM today";
		try {
			pstmt = this.conn.prepareStatement(sql);
//			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Today(rs.getString("contents"),
								rs.getString("date").substring(11).substring(0, 5),
								rs.getString("img"));
				todayList.add(today);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todayList;
	}

	@Override
	public void add(Today today) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO `today` (`contents`," +
				"`date`," +
				"`img`)" +
				"VALUES " +
				"(?, ?, ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, today.getContents());
			pstmt.setString(2, MyCalendar.getCurrentDateTime());
			pstmt.setString(3, today.getImg());
			System.out.println(pstmt.toString());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Today findByEmail(String string) {
		// TODO Auto-generated method stub
		return null;
	}



}
