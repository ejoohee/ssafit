package com.ssafy.fit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.fit.model.dto.BoardComment;

@Repository
public interface BoardCommentDao {
	// 댓글 하나 선택 (id로)
	BoardComment selectOne(int id);
	
	// 댓글 전체
	List<BoardComment> selectAll();
	
	// 댓글 등록
	void insertBoardComment(BoardComment boardComment);
	
	// 댓글 삭제
	int deleteBoardComment(int id);
	
	// 댓글 수정
	int updateBoardComment(BoardComment boardComment);
	
}
