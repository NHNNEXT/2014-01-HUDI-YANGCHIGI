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
import org.junit.Test;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("today.xml")
public class TodayRepositoryTest {

	@Test
	public void 투데이_조회() {
		TodayRepository todayRepository = new TodayRepository();
		Today today = todayRepository.findByDateAndUserId("2014-4-18", 3);
		assertThat(today, is(notNullValue()));
	}
}
