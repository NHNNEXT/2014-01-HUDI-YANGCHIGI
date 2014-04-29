package org.yangchigi.repository;

public interface Repository <T> {
	T findById(int id);
	void add(T data) throws Exception;
}