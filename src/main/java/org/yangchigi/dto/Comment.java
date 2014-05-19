package org.yangchigi.dto;

public class Comment {
	private int id;
	private String content;
	private int userId;
	private int todayId;
	
	
	
	public Comment(String content, int userId, int todayId) {
		this.content = content;
		this.userId = userId;
		this.todayId = todayId;
	}
	public int getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public int getUserId() {
		return userId;
	}
	public int getTodayId() {
		return todayId;
	}
	
	
}
