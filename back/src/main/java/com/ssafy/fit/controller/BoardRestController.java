package com.ssafy.fit.controller;

import java.util.HashMap;
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
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.BoardService;

import io.swagger.annotations.ApiOperation;

// CrossOrigin 어노테이션 안해주면 충돌이 일어나서 실행 안된다!!
@CrossOrigin("*")
@RestController
@RequestMapping("/boardapi")
public class BoardRestController {

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;
	
//	@GetMapping("/board")
//	public ResponseEntity<List<Board>> allList() {
//
//		return new ResponseEntity<List<Board>>(boardService.getAllBoards(), HttpStatus.OK);
//	}
	
	// 게시글 목록 불러오기 (검색기능 포함)
	@GetMapping("/board")
	@ApiOperation(value = "등록된 모든 게시물 목록을 반환한다.", response = Board.class)
	public ResponseEntity<List<Board>> list(@RequestParam(defaultValue = "") String mode,
			@RequestParam(defaultValue = "") String keyword) {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("mode", mode);
		params.put("keyword", keyword);

		return new ResponseEntity<List<Board>>(boardService.getBoardList(params), HttpStatus.OK);
	}
	
	// 게시글 작성
	@PostMapping("/board")
	@ApiOperation(value = "게시물을 작성한다.", response = Board.class)
	public ResponseEntity<String> write(Board board) {
		boardService.writeBoard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	// 게시글 상세 페이지
	@GetMapping("/board/{id}")
	@ApiOperation(value = "{id}에 해당하는 게시물 정보를 반환한다.", response = Board.class)
	public ResponseEntity<Board> detail(@PathVariable int id) {
		try {
			return new ResponseEntity<Board>(boardService.getBoard(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new BoardNotFoundException(id + "번 게시글은 없다.");
		}
	}

	// 게시글 수정
	@PutMapping("/board")
	@ApiOperation(value = "게시물을 수정한다.", response = Board.class)
	public ResponseEntity<String> update(Board board) {
		boardService.modifyBoard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 게시글 삭제
	@DeleteMapping("/board/{id}")
	@ApiOperation(value = "{id}에 해당하는 게시물을 삭제한다.", response = Board.class)
	public ResponseEntity<String> delete(@PathVariable int id) {
		if (boardService.removeBoard(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
