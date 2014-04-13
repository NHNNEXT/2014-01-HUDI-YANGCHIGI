package org.yangchigi.repository;

import org.yangchigi.web.Today;

public interface Repository <T> {
	T findByEmail(String string);
	void add(T user);

	// today와 user 하나로 합칠수 있어야 한다.
	void add(Today today);

}
