package com.ssafy.fit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.UserService;
import com.ssafy.fit.model.service.VideoService;

@CrossOrigin("*")
@RestController
public class MainRestController {
	
	private VideoService videoService;
//	private UserService userService;
	private String[] parts = new String[] {"전신","상체","복부","하체"};

	public MainRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public MainRestController(VideoService videoService, UserService userService) {
		this.videoService = videoService;
//		this.userService = userService;
	}
	
	@GetMapping({"/","home","index"})
	public ResponseEntity<?> main(HttpSession session) {
		Map<String,List<Video>> lists = new HashMap<String, List<Video>>();
		lists.put("전체", videoService.getList());
		lists.put("인기", videoService.getListSortedByViewCnt());
		for (String part : parts) {
			lists.put(part, videoService.getListOfPart(part));
		}
		return new ResponseEntity<Map<String,List<Video>>>(lists, HttpStatus.OK);
	}
	
}
