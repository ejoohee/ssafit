package com.ssafy.fit.model.dto;

public class User {
	
	private int userSeq; // 유저 번호
	private String id; // 아이디
	private String password; // 비밀번호
	private String nickname; // 이름
	private String email;// 이메일
	private int age; // 나이

	// 기본 생성자
	public User() {
	}

	public User(String id, String password, String nickname, String email, int age) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.age = age;
	}

	public int getUserSeq() {
		return userSeq;
	}
	
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", nickname=" + nickname + ", email=" + email + ", age=" + age
				+ "]";
	}

	

	
}
