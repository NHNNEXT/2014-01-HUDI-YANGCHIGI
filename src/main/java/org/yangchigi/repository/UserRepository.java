package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yangchigi.web.User;

public class UserRepository implements Repository<User> {
	private static final Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.UserRepository");
	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;

	public UserRepository() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	public Connection getConn() throws SQLException {
		return this.conn;
	}

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
				logger.info("email: " + rs.getString("email"));
				user = new User(rs.getString("email"),
						rs.getString("nickname"), rs.getString("password"),
						rs.getString("thumbnail"));
				user.setId(rs.getInt("id"));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void add(User user) {
		logger.info("UserRepository > add: " + user.toString());
		PreparedStatement pstmt;

		String sql = "INSERT INTO `user` (`email`, `nickname`, `password`, `thumbnail`) "
				+ "VALUES (?, ?, ?, ?)";
		System.out.println("UserRepository > add: " + user.toString());

		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getThumbnail());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User findById(String id) {
		return null;
	}
}
