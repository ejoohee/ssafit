package com.ssafy.fit.model.dto;

public class Comment {

	private int commentSeq;
	private String videoId;
	private String nickname;
	private String content;
	private String regDate;
	
	public int getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
		return "Comment [commentSeq=" + commentSeq + ", videoId=" + videoId + ", nickname=" + nickname + ", content="
				+ content + ", regDate=" + regDate + "]";
	}
	
	
}