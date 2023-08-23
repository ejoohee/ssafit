package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.fit.model.dao.CommentDao;
import com.ssafy.fit.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;
	
	
	public CommentServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	@Override
	@Transactional
	public int write(Comment comment) {
		return commentDao.insert(comment);
	}

	@Override
	@Transactional
	public int modify(Comment comment) {
		return commentDao.update(comment);
	}

	@Override
	@Transactional
	public int remove(int commentSeq) {
		return commentDao.delete(commentSeq);
	}

	@Override
	public List<Comment> getList() {
		return commentDao.selectAll();
	}

	@Override
	public List<Comment> getListOfVideo(String videoId) {
		return commentDao.selectByVideo(videoId);
	}

	@Override
	public Comment getComment(int commentSeq) {
		return commentDao.select(commentSeq);
	}

}