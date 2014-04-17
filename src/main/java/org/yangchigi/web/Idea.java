package org.yangchigi.web;

public class Idea {
	private String content;
	private String time;
	private String imgName;
	private String date;
	private int user_id;

	public Idea(String content, String date, String time, String imgName, int user_id) {
		this.content = content;
		this.date = date;
		this.time = time;
		this.imgName = imgName;
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public String getTime() {
		return time;
	}

	public String getImgName() {
		return imgName;
	}

	public String getDate() {
		return date;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getImg() {
		return imgName;
	}

	@Override
	public String toString() {
		return "Idea [content=" + content + ", time=" + time + ", imgName="
				+ imgName + ", date=" + date + ", user_id=" + user_id + "]";
	}
}
