package org.yangchigi.web;

public class Idea {
	private int id;
	private String content;
	private String date;
	private String time;
	private String img_name;
	private int user_id;
	
	
	public Idea(String content, String date, String time, String img_name,
			int user_id) {
		this.content = content;
		this.date = date;
		this.time = time;
		this.img_name = img_name;
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public String getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	public String getImg_name() {
		return img_name;
	}
	public int getUser_id() {
		return user_id;
	}
	
	
		
	

}
