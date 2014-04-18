package org.yangchigi.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.yangchigi.web.Today;

import static org.unitils.reflectionassert.ReflectionAssert.*;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("today.xml")
public class TodayRepositoryTest {
	private static final Logger logger = LoggerFactory
			.getLogger("org.yangchigi.web.TodayRepositoryTest");
	private TodayRepository todayRepository;
	
	@Before
	public void setUp() {
		try {
			todayRepository = new TodayRepository();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("TodayRepository 초기화 실패");
		}
	}
	
	@Test
	public void 투데이_조회_SELECT_BY_Date_And_UserId() {
		Today today = todayRepository.findByDateAndUserId("2014-03-01", 3);
		assertThat(today, is(notNullValue()));
		assertThat(today.getLike(), is(11));
	}
	
	@Test
	public void 투데이_조회_SELECT_BY_Id() {
		Today today = todayRepository.findById(1);
		assertThat(today, is(notNullValue()));
		assertThat(today.getLike(), is(11));
	}
	
	@Test
	@ExpectedDataSet("expected_today.xml")
	public void 투데이_Insert() {
		int like = 30;
		int userId = 3;
		
		Today today = new Today("2014-4-20", like, userId);
		todayRepository.add(today);
	}
}
