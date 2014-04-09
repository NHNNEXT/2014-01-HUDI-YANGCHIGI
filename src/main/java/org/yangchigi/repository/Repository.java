package org.yangchigi.repository;

public interface Repository <T> {
	T findByEmail(String string);

	void add(T user);

}
