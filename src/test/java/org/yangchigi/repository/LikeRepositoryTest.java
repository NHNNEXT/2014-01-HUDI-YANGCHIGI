package org.yangchigi.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.yangchigi.dto.Like;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("like.xml")
public class LikeRepositoryTest {

	Logger logger = LoggerFactory.getLogger("org.yangchigi.web.IdeaRepositoryTest");
	private LikeRepository likeRepository;
	
	@Before
	public void setUp() throws Exception {
		likeRepository = new LikeRepository();
	}

	@Test
	public void 데이터_있을때_SELECT_FROM_like_BY_userId_AND_todayId() {
		int userId = 3;
		int todayId = 1;
		Like like = likeRepository.findByUserIdAndTodayId(userId, todayId);
		assertThat(like.getUserId(), is(3));
	}
	
	@Test
	public void 데이터_없을때_SELECT_FROM_like_BY_userId_AND_todayId() {
		int userId = 3;
		int todayId = 10;
		Like like = likeRepository.findByUserIdAndTodayId(userId, todayId);
		assertThat(like, is(nullValue()));
	}
	
	@Test
	@ExpectedDataSet("expected_like.xml")
	public void INSERT() {
		Like like = new Like(3, 4);
		likeRepository.add(like);
	}
	
	@Test
	@ExpectedDataSet("expected_deleted_like.xml")
	public void DELETE() {
		Like like = new Like(3, 1);
		likeRepository.delete(like);
	}
}
