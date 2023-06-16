package com.example.demo.vo;

public class memberVo {
	
	private String user_id; //id
	private String user_pw; //pw
	private String user_join;//가입시간
	private String nick_name;//사용자이름
	private String user_email;//사용자이메일
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_join() {
		return user_join;
	}
	public void setUser_join(String user_join) {
		this.user_join = user_join;
	}
	
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
	
}
