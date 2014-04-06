package org.yangchigi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.yangchigi.web.User;

public class UserRepository implements Repository {
	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";
	private Connection conn;
	
	public Connection getConn() throws SQLException {
		return this.conn;
	}
	
	public void setConn() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(addr, user, pw);
	}

	@Override
	public Object findByEmail(String string) {
		return null;
	}

	@Override
	public void add(User user) {
		PreparedStatement pstmt;
		
		String sql = "INSERT INTO `user` (`email`, `nickname`, `password`) " +
						"VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
