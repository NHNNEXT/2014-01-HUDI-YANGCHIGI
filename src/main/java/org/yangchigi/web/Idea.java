package org.yangchigi.web;

public class Idea {
	private int id;
	private String content;
	private String time;
	private String img;
	private int today_id;
		
	public Idea(String content, String time, String img, int today_id) {
		this.content = content;
		this.time = time;
		this.img = img;
		this.today_id = today_id;
	}
	public int getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public String getTime() {
		return time;
	}
	public String getImg() {
		return img;
	}
	public int getToday_id() {
		return today_id;
	}
	
	

	

}
