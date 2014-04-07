package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangchigi.web.User;

public class UserRepository implements Repository {
	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;

	public UserRepository() throws ClassNotFoundException, SQLException {
		super();
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

	@Override
	public User findByEmail(String email) {
		PreparedStatement pstmt;
		ResultSet rs;
		User user = null;
		System.out.println("UserRepository > findByEmail");
		System.out.println("email: " + email);
		String sql = "SELECT * FROM `user` WHERE (email = ?)";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("email: " + rs.getString("email"));
				user = new User(rs.getString("email"),
								rs.getString("nickname"),
								rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void add(User user) {
		PreparedStatement pstmt;

		String sql = "INSERT INTO `user` (`email`, `nickname`, `password`) "
				+ "VALUES (?, ?, ?)";
		System.out.println("UserRepository > add: " + user.toString());
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getPassword());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
