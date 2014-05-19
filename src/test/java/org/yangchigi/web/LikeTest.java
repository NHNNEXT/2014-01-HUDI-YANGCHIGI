package org.yangchigi.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.yangchigi.dto.Like;

import static org.hamcrest.CoreMatchers.is;

public class LikeTest {

	@Test
	public void Like클래스_id_세팅() {
		int userId = 3;
		int todayId = 1;
		Like like = new Like(userId, todayId);
		like.setId(1);
		assertThat(like.getId(), is(1));
	}
}
