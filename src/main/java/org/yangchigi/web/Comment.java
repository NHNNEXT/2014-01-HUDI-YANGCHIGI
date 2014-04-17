package org.yangchigi.web;

public class Comment {
	private int id;
	private String content;
	private int user_id;
	private int today_id;
	
	public Comment(String content, int user_id, int today_id) {
		this.content = content;
		this.user_id = user_id;
		this.today_id = today_id;
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
