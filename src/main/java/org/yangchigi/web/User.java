package org.yangchigi.web;

public class User {
	private String email;
	private String nickname;
	private String password;
	
	public User(String email, String nickname, String password) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", nickname=" + nickname
				+ ", password=" + password + "]";
	}
}
