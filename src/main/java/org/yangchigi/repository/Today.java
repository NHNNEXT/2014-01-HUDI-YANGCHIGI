package org.yangchigi.repository;

public class Today {
	
	private String date;
	private int like;
	private int userId;

	public Today(String date, int like, int userId) {
		this.date = date;
		this.like = like;
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}


	public int getUserId() {
		return userId;
	}

	public int getLike() {
		return like;
	}

}
