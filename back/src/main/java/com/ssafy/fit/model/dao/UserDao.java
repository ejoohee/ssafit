package com.ssafy.fit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.fit.model.dto.User;

@Repository
public interface UserDao {
	
	int insert(User user);
	
	int update(User user);
	
	int delete(String id);

	User selectById(String id);
	
	User selectByNickname(String nickname);
	
	User selectByEmail(String email);
	
	List<User> selectAll();
	

}
