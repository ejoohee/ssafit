package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.fit.exception.DuplicateKeyException;
import com.ssafy.fit.model.dao.LikeDao;
import com.ssafy.fit.model.dao.UserDao;
import com.ssafy.fit.model.dto.Like;
import com.ssafy.fit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private LikeDao likeDao;
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserServiceImpl(UserDao userDao, LikeDao likeDao) {
		this.userDao = userDao;
		this.likeDao = likeDao;
	}
	
	// 아이디, 닉네임, 이메일 중복처리할 때 alert 창 띄우는 거 고민해보자.
	@Override
	@Transactional
	public int insert(User user) throws DuplicateKeyException {
		if (user.getId().equals("") || user.getPassword().equals("") || user.getNickname().equals("") || user.getEmail().equals("")) return 0;
		if (userDao.selectById(user.getId()) != null) throw new DuplicateKeyException("아이디 중복");
		if (userDao.selectByNickname(user.getNickname()) != null) throw new DuplicateKeyException("닉네임 중복");
		if (userDao.selectByEmail(user.getEmail()) != null) throw new DuplicateKeyException("이메일 중복");
		return userDao.insert(user);
	}
	
	@Override
	public User login(User user) {
		if (!userDao.selectById(user.getId()).getPassword().equals(user.getPassword())) return null;
		return user;
	}
	
	@Override
	@Transactional
	public int like(Like like) {
//		List<Like> likeList = likeDao.selectByUser(like.getUserId());
//		for (Like l : likeList) {
//			if (l.getVideoId().equals(like.getVideoId())) return 0;
//		}
		return likeDao.insert(like);
	}
	
	
	@Override
	@Transactional
	public int unlike(Like like) {
		return likeDao.delete(like);
	}


	@Override
	public User select(String id) {
		return userDao.selectById(id);
	}

	@Override
	public int update(User user) throws DuplicateKeyException {
//		if (userDao.selectByNickname(user.getNickname()) != null) throw new DuplicateKeyException("닉네임 중복");
		return userDao.update(user);
	}

	@Override
	public int delete(String id) {
		return userDao.delete(id);
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}
	@Override
	public List<Like> getLikeList(String id){
		return likeDao.selectByUser(id);
		
	}

}
