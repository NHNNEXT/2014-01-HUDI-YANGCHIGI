package org.yangchigi.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.*;
import org.yangchigi.web.User;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet
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
	}
//
//	@After
//	public void tearDown() throws Exception {
//		this.conn.rollback();
//		this.conn.close();
//	}
	
	@Test
	public void 유저_조회() {
		User user = (User) repository.findByEmail("hook3748@gmail.com");
		assertPropertyLenientEquals("email", "hook3748@gmail.com", user);
	}
}
