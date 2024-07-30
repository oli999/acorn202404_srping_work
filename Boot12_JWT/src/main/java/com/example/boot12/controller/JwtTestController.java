package com.example.boot12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.boot12.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class JwtTestController {
	//Jwt 토큰 유틸
	@Autowired 
	private JwtUtil jwtUtil;
	
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	//쿠키 유지시간
	@Value("${jwt.cookie.expiration}")
	private int cookieExpiration;
	
	@GetMapping("/test/login")
	public String login(HttpServletResponse response) {
		//토큰을 발급해서 쿠키로 
		String jwtToken=jwtUtil.generateToken("kimgura");
		// JWT를 쿠키에 담아 응답
        Cookie cookie = new Cookie(jwtName, jwtToken);
        cookie.setMaxAge(cookieExpiration); // 쿠키 유지 시간 초 단위로 설정
        cookie.setHttpOnly(true); //웹브라우저에서 JavaScript에서 접근 불가 하도록 설정 
        cookie.setPath("/"); // 모든 경로에서 쿠키를 사용할수 있도록 설정 
        response.addCookie(cookie);
        
		return "redirect:/";
	}
	
	@GetMapping("/test/logout")
	public String logout(HttpServletResponse response) {
		// jwt 를 저장하고 있는 쿠키를 삭제하면 로그아웃과 동일한 효과를 낸다 
		Cookie cook=new Cookie(jwtName, null);
		//쿠키를 삭제하기 위해 setMaxAge(0)
		cook.setMaxAge(0);
		cook.setPath("/");
		response.addCookie(cook);
		
		return "redirect:/";
	}
}















