package com.ssafy.fit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.fit.model.dto.User;



@Component
public class LoginCheckInterCeptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			String path = request.getContextPath();
			response.sendRedirect(path+"/home");
			return false;
		} else {
			return true;
		}
	}

}
