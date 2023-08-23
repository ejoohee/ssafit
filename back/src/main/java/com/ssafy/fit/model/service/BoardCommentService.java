package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.BoardComment;

public interface BoardCommentService {
	// 전체 댓글 보기
	List<BoardComment> getBoardComments();
	
	// 댓글 하나 선택
	BoardComment getBoardComment(int id);
	
	// 댓글 등록
	void writeBoardComment(BoardComment boardComment);
	
	// 댓글 삭제
	boolean removeBoardComment(int id);
	
	// 댓글 수정
	boolean modifyBoardComment(BoardComment boardComment);
}
