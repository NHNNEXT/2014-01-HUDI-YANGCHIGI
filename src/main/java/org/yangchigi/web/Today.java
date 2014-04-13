package org.yangchigi.web;

public class Today {
	private int id;
	private String contents;
	private String date;
	private String img;

	public Today(String contents, String date, String img) {
		this.contents = contents;
		this.date = date;
		this.img = img;
	}
	
	public Today(int id, String contents, String date, String img) {
		this.id = id;
		this.contents = contents;
		this.date = date;
		this.img = img;
	}

	public int getId() {
		return id;
	}

	public String getContents() {
		return contents;
	}

	public String getDate() {
		return date;
	}

	public String getImg() {
		return img;
	}

	@Override
	public String toString() {
		return "Today [id=" + id + ", contents=" + contents + ", date=" + date
				+ ", img=" + img + "]";
	}
}
