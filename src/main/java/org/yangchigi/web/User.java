package org.yangchigi.web;

public class User {
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
		return thumbnail;
	}
	
	@Override
	public String toString() {
		return "User [email=" + email + ", nickname=" + nickname
				+ ", password=" + password + "]";
	}
}
