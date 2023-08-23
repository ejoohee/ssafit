package com.ssafy.fit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.fit.model.dto.Board;


public interface BoardDao {
	// 게시글 등록
	void insertBoard(Board board);
	
//	// 게시글 목록 (전체)
//	List<Board> selectAll();
	
	// 게시글 목록 (검색)
	List<Board> selectList(HashMap<String, String> params);
	
	// 게시글 목록(본인 게시물)
	List<Board> selectUserList(String writer);

	// 게시글 조회
	Board selectOne(int id);

	// 게시글 삭제
	int deleteBoard(int id);

	// 게시글 수정
	int updateBoard(Board board);
}
