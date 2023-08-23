package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.exception.DuplicateKeyException;
import com.ssafy.fit.model.dto.Like;
import com.ssafy.fit.model.dto.User;

public interface UserService {
	
	int insert(User user) throws DuplicateKeyException;
	
	int delete(String id);
	
	int update(User user) throws DuplicateKeyException;
	
	int like(Like like);
	
	int unlike(Like like);
	
	List<User> selectAll();

	User select(String id);

	User login(User user);

	List<Like> getLikeList(String id);
	
}
