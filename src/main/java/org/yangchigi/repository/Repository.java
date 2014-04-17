package org.yangchigi.repository;

public interface Repository <T> {
	T findById(String id);
	void add(T data);
}