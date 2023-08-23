package com.ssafy.fit.config;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.jdt.internal.compiler.batch.ClasspathDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ssafy.fit.interceptor.LoginCheckInterCeptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
		
	}
	
//	@Autowired
//	LoginCheckInterCeptor lci;
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(lci)
//		.addPathPatterns("/*")
//		.excludePathPatterns("/")
//		.excludePathPatterns("/home")
//		.excludePathPatterns("/index")
//		.excludePathPatterns("/user/signup")
//		.excludePathPatterns("/user/login")
//		.excludePathPatterns("/swagger-ui/**");
//	}
//	
	
}
