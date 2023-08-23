package com.ssafy.fit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.fit.model.dto.Comment;

@Repository
public interface CommentDao {
	
	int insert(Comment comment);
	
	int update(Comment comment);
	
	int delete(int commentSeq);
	
	List<Comment> selectAll();
	
	List<Comment> selectByVideo(String videoId);
	
	Comment select(int commentSeq);

}