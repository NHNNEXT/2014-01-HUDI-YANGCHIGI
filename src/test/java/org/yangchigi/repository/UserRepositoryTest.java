package org.yangchigi.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import static org.unitils.reflectionassert.ReflectionAssert.*;
import org.yangchigi.web.User;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("user.xml")
public class UserRepositoryTest {
	private Connection conn;
	private UserRepository repository;
	private static final Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.UserRepositoryTest");

	@Before
	public void setUp() throws Exception {
		repository = new UserRepository();
		conn = ((UserRepository) repository).getConn();
		assertThat(conn, is(notNullValue()));
	}

	@Test
	public void 유저_조회() {
		User user = repository.findByEmail("hook3748@gmail.com");
		assertPropertyLenientEquals("email", "hook3748@gmail.com", user);
		assertThat(user.getId(), is(instanceOf(int.class)));
		assertThat(user.getId(), is(not(equalTo(0))));
	}

	@Test
	@ExpectedDataSet("expected_user.xml")
	public void 유저_추가() {
		User user = new User("hook3748@naver.com", "hogang", "123456", "");
		repository.add(user);
	}
}
