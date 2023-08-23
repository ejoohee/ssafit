package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.BoardCommentDao;
import com.ssafy.fit.model.dto.BoardComment;

@Service
public class BoardCommentServiceImpl implements BoardCommentService{
	
	@Autowired
	private BoardCommentDao boardCommentDao;
	
	@Override
	public List<BoardComment> getBoardComments() {
		return boardCommentDao.selectAll();
	}
	
	@Override
	public BoardComment getBoardComment(int id) {
		return boardCommentDao.selectOne(id);
	}

	@Override
	public void writeBoardComment(BoardComment boardComment) {
		boardCommentDao.insertBoardComment(boardComment);
		
	}

	@Override
	public boolean removeBoardComment(int id) {
		return boardCommentDao.deleteBoardComment(id) == 1;
	}

	@Override
	public boolean modifyBoardComment(BoardComment boardComment) {
		BoardComment originBoardComment = boardCommentDao.selectOne(boardComment.getId());
		originBoardComment.setContent(boardComment.getContent());
		return boardCommentDao.updateBoardComment(originBoardComment) == 1;
	}

}
