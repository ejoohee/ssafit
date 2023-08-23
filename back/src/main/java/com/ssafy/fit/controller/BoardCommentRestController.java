package com.ssafy.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.exception.BoardNotFoundException;
import com.ssafy.fit.model.dto.Board;
import com.ssafy.fit.model.dto.BoardComment;
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.BoardCommentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/boardcommentapi")
public class BoardCommentRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardCommentService boardCommentService;
	
	// 댓글 전체 불러오기
	@GetMapping("/boardcomment")
	@ApiOperation(value = "등록된 모든 댓글 목록을 반환한다.", response = BoardComment.class)
	public ResponseEntity<?> list() {
		List<BoardComment> comments = boardCommentService.getBoardComments();
		if (comments != null && comments.size() > 0) {
			return new ResponseEntity<List<BoardComment>>(comments, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	// 댓글 선택
	@GetMapping("/boardcommet/{id}")
	@ApiOperation(value = "{id]에 해당하는 댓글 정보를 반환한다.", response = BoardComment.class)
	public ResponseEntity<BoardComment> detail(@PathVariable int id) {
		try {
			return new ResponseEntity<BoardComment>(boardCommentService.getBoardComment(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new BoardNotFoundException(id + "번 댓글은 없다.");
		}
	}
	
	// 댓글 작성
	@PostMapping("/board/{boardId}")
	@ApiOperation(value = "{boardid}에 해당하는 게시물에 댓글을 작성한다.", response = BoardComment.class)
	public ResponseEntity<String> write(@RequestBody BoardComment boardComment) {
		System.out.println(boardComment.toString());
		boardCommentService.writeBoardComment(boardComment);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	// 댓글 수정
	@PutMapping("/board/{boardId}/{id}")
	@ApiOperation(value = "{id}에 해당하는 댓글을 수정한다.", response = BoardComment.class)
	public ResponseEntity<String> update(BoardComment boardComment) {
		System.out.println("**************************************************");
		System.out.println("수정 전: " + boardComment.toString());
		boardCommentService.modifyBoardComment(boardComment);
		System.out.println("수정 후: " + boardComment.toString());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
	
	// 댓글 삭제
	@DeleteMapping("/board/{boardId}/{id}")
	@ApiOperation(value = "{id}에 해당하는 댓글을 삭제한다.", response = BoardComment.class)
	public ResponseEntity<String> delete(@PathVariable int id) {
		if (boardCommentService.removeBoardComment(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
