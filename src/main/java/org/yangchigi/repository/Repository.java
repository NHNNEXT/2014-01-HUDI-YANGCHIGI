package org.yangchigi.repository;

public interface Repository <T> {
	final String addr = "jdbc:mysql://localhost/seize";
	final String driver = "com.mysql.jdbc.Driver";
	final String user = "yangchigi";
	final String pw = "yangchigi";
	
	T findById(int id);
	void add(T data);
}