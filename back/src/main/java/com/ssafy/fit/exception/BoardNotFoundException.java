package com.ssafy.fit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BoardNotFoundException(String msg) {
		super(msg);
	}
}
