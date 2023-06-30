package com.example.demo.vo;

public class boardVo {
	
	private String board_seq; //글번호
	private String board_title; //제목
	private String board_price; //가격
	private String board_content; //내용
	private String board_views; //조회수
	private String board_like; //관심	
	private String user_id; //작성자
	
	public String getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_price() {
		return board_price;
	}
	public void setBoard_price(String board_price) {
		this.board_price = board_price;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_views() {
		return board_views;
	}
	public void setBoard_views(String board_views) {
		this.board_views = board_views;
	}
	public String getBoard_like() {
		return board_like;
	}
	public void setBoard_like(String board_like) {
		this.board_like = board_like;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
