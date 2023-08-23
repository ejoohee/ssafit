package com.ssafy.fit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.fit.model.dto.Comment;

public interface CommentService {
	
	
	int write(Comment comment);
	
	int modify(Comment comment);
	
	int remove(int commentSeq);
	
	List<Comment> getList();
	
	List<Comment> getListOfVideo(String videoId);
	
	Comment getComment(int commentSeq);

	
}