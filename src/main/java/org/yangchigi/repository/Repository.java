package org.yangchigi.repository;

import java.util.ArrayList;


public interface Repository <T> {
	T findByEmail(String string);
	void add(T user);
	ArrayList<T> findListByEmail();
}
