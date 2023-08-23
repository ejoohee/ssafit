package com.ssafy.fit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.fit.model.dto.Like;

@Repository
public interface LikeDao {
	
	int insert(Like like);
	
	int delete(Like like);
	
	List<Like> selectByUser(String id);

}
