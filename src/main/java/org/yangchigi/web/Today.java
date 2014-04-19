package org.yangchigi.web;

public class Today {
	
	private int id;
	private String date;
	private int like;
	private int userId;

	public Today(String date, int like, int userId) {
		this.date = date;
		this.like = like;
		this.userId = userId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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

	public void setLike(int like) {
		this.like = like;
	}

}
