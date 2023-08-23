package com.ssafy.fit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.exception.BoardNotFoundException;
import com.ssafy.fit.exception.DuplicateKeyException;
import com.ssafy.fit.model.dto.Board;
import com.ssafy.fit.model.dto.BoardComment;
import com.ssafy.fit.model.dto.Comment;
import com.ssafy.fit.model.dto.Like;
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.BoardService;
import com.ssafy.fit.model.service.UserService;
import com.ssafy.fit.model.service.VideoService;

import io.swagger.annotations.ApiOperation;

//localhost:9999/swagger-ui/index.html

@CrossOrigin("*")
@RestController
@RequestMapping("/userapi")
public class UserRestController {

	private UserService userService;
	private VideoService videoService;
	private BoardService boardService;
	
	public UserRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserRestController(UserService userService, VideoService videoService, BoardService boardService) {
		this.userService = userService;
		this.videoService = videoService;
		this.boardService = boardService;
	}
	
	@GetMapping("/user")
	@ApiOperation(value = "등록된 모든 사용자 정보를 반환한다.", response = User.class)
	public ResponseEntity<?> selectAll() {
		try {
			List<User> users = userService.selectAll();
			if (users != null && users.size() > 0) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/{id}")
	@ApiOperation(value = "{id}에 해당하는 사용자 정보를 반환한다.", response = User.class)
	public ResponseEntity<?> select(@PathVariable String id) {
		try {
			User user = userService.select(id);
			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/user")
	@ApiOperation(value = "사용자 정보를 삽입한다.", response = Integer.class)
	public ResponseEntity<?> insert(@RequestBody User user) {
		try {
			System.out.println(user);
			int result = userService.insert(user);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/user")
	@ApiOperation(value = "사용자 정보를 수정한다.", response = Integer.class)
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			int result = userService.update(user);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "{id} 에 해당하는 사용자 정보를 삭제한다.", response = Integer.class)
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			int result = userService.delete(id);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	@GetMapping("/login")
//	public ResponseEntity<?> login(User user, HttpSession session) {
//		User loginUser = userService.login(user);
//		if (loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//			return new ResponseEntity<String>("로그인 성공", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<String>("로그인 실패", HttpStatus.UNAUTHORIZED);
//		}
//	}
//	
//	@GetMapping("/logout")
//	public ResponseEntity<?> logout(HttpSession session) {
//		session.removeAttribute("loginUser");
//		return new ResponseEntity<String>("로그아웃 성공", HttpStatus.OK);
//	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@PostMapping("/like/{videoId}")
	@ApiOperation(value = "{videoId}에 해당하는 비디오를 찜한다.", response = Like.class)
	public ResponseEntity<?> doLike(@RequestBody Like like, @PathVariable String videoId) {
		like.setVideoId(videoId);
		int result=userService.like(like);
		System.out.println(like);
		if (result == 1) {
			return new ResponseEntity<String>("찜 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("찜 실패 혹은 잘못된 결과", HttpStatus.BAD_REQUEST);
		}
	}
	

	
	
	@DeleteMapping("/like/{videoId}")
	@ApiOperation(value = "{videoId}에 해당하는 비디오를 찜목록에서 삭제한다.", response = User.class)
	public ResponseEntity<?> unlike(@PathVariable String videoId) {
		Like like = new Like();
		like.setVideoId(videoId);
		int result = userService.unlike(like);
		if (result == 1) {
			return new ResponseEntity<String>("찜 해제 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("찜 해제 실패 혹은 잘못된 결과", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/like")
	@ApiOperation(value = "{id}에 해당하는 찜목록을 반환한다.", response = Like.class)
	public ResponseEntity<?> getLike(String id) {
		List<Like> likeList = userService.getLikeList(id);
		if (likeList != null && likeList.size() > 0) {
			return new ResponseEntity<List<Like>>(likeList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	// 댓글 전체 불러오기
//		@GetMapping("/boardcomment")
//		@ApiOperation(value = "등록된 모든 댓글 목록을 반환한다.", response = BoardComment.class)
//		public ResponseEntity<?> list() {
//			List<BoardComment> comments = boardCommentService.getBoardComments();
//			if (comments != null && comments.size() > 0) {
//				return new ResponseEntity<List<BoardComment>>(comments, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		}
	
	// 본인이 작성한 게시글 목록
	@GetMapping("/user/board/{writer}")
	@ApiOperation(value = "본인이 작성한 게시글 목록을 반환한다.", response = User.class)
	public ResponseEntity<List<Board>> userBoardList(@PathVariable String writer) {
	    try {
	        List<Board> userBoards = boardService.getUserBoard(writer);
	        return new ResponseEntity<List<Board>>(userBoards, HttpStatus.OK);
	    } catch (Exception e) {
	        throw new BoardNotFoundException("사용자의 게시글을 가져오는 중에 오류가 발생했습니다.");
	    }
	}
	
}
