package org.yangchigi.web;

public class Like {
	private int userId;
	private int todayId;
	private int id;

	public Like(int userId, int todayid) {
		this.userId = userId;
		this.todayId = todayid;
	}

	public int getUserId() {
		return userId;
	}

	public int getTodayId() {
		return todayId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}