package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.VideoDao;
import com.ssafy.fit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService{

	private VideoDao videoDao; 
	
	public VideoServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	
	@Override
	public List<Video> getList() {
		return videoDao.selectAll();
	}

	@Override
	public Video get(String videoId) {
		return videoDao.selectOne(videoId);
	}

	@Override
	public List<Video> getListOfPart(String part) {
		return videoDao.selectByPart(part);
	}

	@Override
	public List<Video> getListSortedByViewCnt() {
		return videoDao.selectByViewCnt();
	}

}
