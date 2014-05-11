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
		String sql = "SELECT * FROM `user` WHERE (email = ?)";
		try {
			this.conn = DriverManager.getConnection(addr, this.user, pw);

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
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void add(User user) throws Exception {
//		User user = findByEmail(user.getEmail());
//		if (user != null) {
//			throw new DuplicateEmailException();
//		}
//		
//		user = findByNickname(user.getNickname());
		
		
		logger.info("UserRepository > add: " + user.toString());
		PreparedStatement pstmt;

		String sql = "INSERT INTO `user` (`email`, `nickname`, `password`, `thumbnail`) "
				+ "VALUES (?, ?, ?, ?)";

		try {
			this.conn = DriverManager.getConnection(addr, this.user, pw);

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getThumbnail());
			pstmt.execute();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			if(e.getMessage().contains("email_UNIQUE")) throw new DuplicateEmailException("duplicate.email");
//			if(e.getMessage().contains("nickname_UNIQUE")) throw new DuplicateNicknameException("duplicate.nickname");
		}
	}

	public User findById(int id) {
		return null;
	}

	// 유져 닉네임을 받아서 변경
	public void modifyNickname(User user, String nickname) {
		logger.info("UserRepository > modifyNickname: " + user.toString());
		PreparedStatement pstmt;

		String sql = "UPDATE `user` SET nickname = ? WHERE email=?";

		try {
			this.conn = DriverManager.getConnection(addr, this.user, pw);

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setString(2, user.getEmail());
			System.out.println("nickname: " + nickname + " user.getEmail(): "
					+ user.getEmail());
			System.out.println(pstmt.toString());
			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 썸네일 이름을 받아서 변경
	public void modifyThumbnail(User user, String thumbnail) {
		logger.info("UserRepository > modifyThumbnail: " + user.toString());
		PreparedStatement pstmt;

		// String sql =
		// "UPDATE user SET thumbnail = 'haha' WHERE email='jayjinjay@gmail.com'";
		String sql = "UPDATE user SET thumbnail = ? WHERE (email=?)";

		try {
			this.conn = DriverManager.getConnection(addr, this.user, pw);

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, thumbnail);
			pstmt.setString(2, user.getEmail());
			System.out.println(pstmt.toString());
			pstmt.execute();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
