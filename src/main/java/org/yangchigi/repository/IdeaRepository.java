package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yangchigi.support.MyCalendar;
import org.yangchigi.web.Idea;

public class IdeaRepository implements Repository<Idea> {

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

	public ArrayList<Idea> findListByEmail() {
		PreparedStatement pstmt;
		ResultSet rs = null;
		Idea today = null;
		ArrayList<Idea> todayList = new ArrayList<Idea>();

		// email 에 의한 select 구현 필
		String sql = "SELECT * FROM today";
		try {
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				today = new Idea(rs.getString("content"), rs.getString("time"),
						rs.getString("date"), rs.getString("imgName"),
						rs.getBoolean("is_private"), rs.getInt("user_id"));
				todayList.add(today);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return todayList;
	}

	public void add(Idea idea) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO `idea` (`content`, `date`, `time`, `img_name`, `user_id`) "
				+ "VALUES (?, ?, ?, ?, ?)";
		String dateTime = MyCalendar.getCurrentDateTime();
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, idea.getContent());
			pstmt.setString(2, MyCalendar.getDate(dateTime));
			pstmt.setString(3, MyCalendar.getTime(dateTime));
			pstmt.setString(4, idea.getImgName());
			pstmt.setInt(5, 3);
			System.out.println(pstmt.toString());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Idea findById(String id) {
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
				idea = new Idea(rs.getString("content"), rs.getString("time"),
						rs.getString("date"), rs.getString("img_name"),
						rs.getBoolean("is_private"), rs.getInt("user_id"));
				ideaList.add(idea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ideaList;
	}
}