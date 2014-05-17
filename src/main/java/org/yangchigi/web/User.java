package org.yangchigi.web;

public class User {
	private int id;
	private String email;
	private String nickname;
	private String password;
	private String thumbnail;
	
	public User(String email, String nickname, String password, String thumbnail) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.thumbnail = thumbnail;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}
	
	public String getThumbnail() {
		if(thumbnail.length()==0){
			return "default_thumbnail.jpg";
		} 
		return thumbnail;
		
	}
	
	@Override
	public String toString() {
		return "User [email=" + email + ", nickname=" + nickname
				+ ", password=" + password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
