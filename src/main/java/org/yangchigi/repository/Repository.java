package org.yangchigi.repository;

import org.yangchigi.web.Today;
import org.yangchigi.web.User;

public interface Repository {

	Object findByEmail(String string);

	void add(User user);

	void add(Today today);

}
