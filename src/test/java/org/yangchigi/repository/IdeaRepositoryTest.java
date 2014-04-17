package org.yangchigi.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.yangchigi.web.Idea;
import org.yangchigi.web.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RunWith(UnitilsJUnit4TestClassRunner.class)
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("idea.xml")
public class IdeaRepositoryTest {
	Logger logger = LoggerFactory.getLogger("org.yangchigi.web.IdeaRepositoryTest");
	private IdeaRepository ideaRepository;
	
	@Before
	public void setUp() throws Exception {
		ideaRepository = new IdeaRepository();
	}
	
	// 최근 시간은 계속 바뀌므로 테스트 하기 힘듦
//	@Test
//	@ExpectedDataSet("expected_idea.xml")
//	public void INSERT_INTO_idea() {
//		Idea idea = new Idea("hi", "20:30:00", "2014-4-16", "foo.jpg", 3);
//		ideaRepository.add(idea);
//	}
	
	@Test
	public void SELECT_FROM_idea_by_user_id_AND_date() {
		List<Idea> ideaList = new ArrayList<Idea>();
		User user = new User("hook3748@gmail", "hogu", "123456", "");
		user.setId(3);
		ideaList = ideaRepository.findByUserIdAndDate(user.getId(), "2014-3-1");
		assertThat(ideaList.size(), is(3));
	}
}
