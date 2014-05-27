package org.yangchigi.dto;

public class Idea {
	private int id;
	private String content;
	private String date;
	private String time;
	private boolean isPrivate;
	private String imgName;
	private int userId;

	public Idea(int id, String content, String date, String time, String imgName,
			boolean is_private, int user_id) {
		this.id = id;
		this.content = content;
		this.date = date;
		this.time = time;
		this.imgName = imgName;
		this.isPrivate = is_private;
		this.userId = user_id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
//	public String getEscapedContent() {
//		return ReplaceUtils.replace()
//	}

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
