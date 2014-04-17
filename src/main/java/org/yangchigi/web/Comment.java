package org.yangchigi.web;

public class Comment {
	private int id;
	private String content;
	private int user_id;
	private int today_id;
	
	public Comment(String content) {
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getToday_id() {
		return today_id;
	}
	
	
}
