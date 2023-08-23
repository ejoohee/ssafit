package com.ssafy.fit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.fit.model.dto.Video;

@Repository
public interface VideoDao {
	
	List<Video> selectAll();
	
	Video selectOne(String videoId);
	
	List<Video> selectByPart(String part);
	
	List<Video> selectByViewCnt();

}
