package org.yangchigi.web;

public class Idea {
	private String content;
	private String time;
	private String imgName;
	private String date;
	private boolean isPrivate;
	private int userId;

	public Idea(String content, String date, String time, String imgName,
			boolean is_private, int user_id) {
		this.content = content;
		this.date = date;
		this.time = time;
		this.imgName = imgName;
		this.isPrivate = is_private;
		this.userId = user_id;
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

	public int getUserId() {
		return userId;
	}

	public String getImg() {
		return imgName;
	}

	public boolean getIsPrivate() {
		return isPrivate;
	}

	@Override
	public String toString() {
		return "Idea [content=" + content + ", time=" + time + ", imgName="
				+ imgName + ", date=" + date + ", isPrivate=" + isPrivate
				+ ", userId=" + userId + "]";
	}
}
