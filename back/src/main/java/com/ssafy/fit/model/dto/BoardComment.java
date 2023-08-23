package com.ssafy.fit.model.dto;

public class BoardComment {
	private int id;
	private int boardId;
	private String writer;
	private String content;
	private String regDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "BoardComment [id=" + id + ", boardId=" + boardId + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	
	
}
