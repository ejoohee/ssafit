package com.ssafy.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.BoardComment;
import com.ssafy.fit.model.dto.Comment;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.CommentService;
import com.ssafy.fit.model.service.VideoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/videoapi")
public class VideoRestController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	private VideoService videoService;
	private CommentService commentService;
	
	public VideoRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public VideoRestController(VideoService videoService, CommentService commentService) {
		this.videoService = videoService;
		this.commentService = commentService;
	}
	
//	@GetMapping("/{videoSeq}")
//	public ResponseEntity<?> getVideo(@PathVariable int videoSeq) {
//		if (videoService.get(videoSeq) == null) return new ResponseEntity<String>("존재하지 않는 비디오입니다.", HttpStatus.NOT_FOUND); 
//		return new ResponseEntity<Video>(videoService.get(videoSeq),HttpStatus.OK);
//	}
	
	
	//댓글 등록
	@PostMapping("/{videoId}")
	@ApiOperation(value = "댓글을 등록한다.", response = Comment.class)
	public ResponseEntity<?> postReview(@RequestBody Comment comment, @PathVariable String videoId) {
		comment.setVideoId(videoId);
		int result = commentService.write(comment);
		System.out.println(comment.toString());
		if (result == 1) {
			return new ResponseEntity<String>("등록 성공",HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("등록 실패 혹은 잘못된 결과",HttpStatus.BAD_REQUEST);
		}
	}
	
	// 댓글 수정
	@PutMapping("/{videoId}/{commentSeq}")
	@ApiOperation(value = "댓글을 수정한다.", response = Comment.class)
	public ResponseEntity<String> update(@RequestBody Comment comment, @PathVariable int commentSeq) {
		comment.setCommentSeq(commentSeq);
		commentService.modify(comment);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	
	//댓글 조회
	@GetMapping("/videocomment")
	@ApiOperation(value = "등록된 모든 댓글 목록을 반환한다.", response = Comment.class)
	public ResponseEntity<?> list() {
		List<Comment> comments = commentService.getList();
		if (comments != null && comments.size() > 0) {
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
//	@PutMapping("/{videoId}")
//	public ResponseEntity<?> putVideo(Comment comment) {
//		if (commentService.modify(comment) == 1) {
//			return new ResponseEntity<String>("수정 성공",HttpStatus.OK);
//		} else {
//			return new ResponseEntity<String>("수정 실패 혹은 잘못된 결과",HttpStatus.BAD_REQUEST);
//		}
//	}
	
	// 댓글 삭제
	@DeleteMapping("/{videoId}/{commentSeq}")
	@ApiOperation(value = "댓글을 삭제한다.", response = Comment.class)
	public ResponseEntity<?> deleteVideo(@PathVariable int commentSeq) {
		int result = commentService.remove(commentSeq);
		if (result == 1) {
			return new ResponseEntity<String>("삭제 성공",HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("수정 실패 혹은 잘못된 결과",HttpStatus.BAD_REQUEST);
		}
	}

}
