package org.yangchigi.repository;

import java.util.ArrayList;


public interface Repository <T> {
	T findByEmail(String string);
	ArrayList<T> findListByEmail();
	
	void add(T obj);

}
