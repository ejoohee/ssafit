package com.ssafy.fit.model.dto;

public class Video {

	private int videoSeq;
	private String title;
	private String part;
	private String videoId;
	private String creator;
	private int viewCnt;
	public int getVideoSeq() {
		return videoSeq;
	}
	public void setVideoSeq(int videoSeq) {
		this.videoSeq = videoSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.videoSeq;
	}
	
	
	
	
}
