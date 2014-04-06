package org.yangchigi.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yangchigi.repository.Repository;
import org.yangchigi.repository.UserRepository;
import org.yangchigi.web.User;

public class UserRepositoryTest {
	private final String addr = "jdbc:mysql://localhost/seize";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "yangchigi";
	private final String pw = "yangchigi";

	private Connection conn;
	private Repository repository;

	@Before
	public void setUp() throws Exception {
		repository = new UserRepository();
		conn = ((UserRepository)repository).getConn();
		assertThat(conn, is(notNullValue()));
		// 트랜잭션 rollback 을 위해 false 
		conn.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		this.conn.rollback();
		this.conn.close();
	}
	
	@Test
	public void 회원가입_유저_DB_추가() {
		User user = new User("hook3748@gamil.com", "hogu", "123456");
		repository.add(user);
		assertEquals(user, is(repository.findByEmail("hook3748@gmail.com")));
	}
}
