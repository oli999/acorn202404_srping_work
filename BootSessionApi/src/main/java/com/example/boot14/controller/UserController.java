package com.example.boot14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot14.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
	
	@PostMapping("/user/login_success")
	public Map<String, Object> loginSuccess(@AuthenticationPrincipal UserDetails ud){
			
		return Map.of("isSuccess", true,
				"userName", ud.getUsername());
	}
	@PostMapping("/user/login_fail")
	public ResponseEntity<String> loginFail(String errMsg){
		
		return new ResponseEntity<String>(errMsg, HttpStatus.UNAUTHORIZED);
	}
	@GetMapping("/user/logout_success")
	public ResponseEntity<String> logout(){
		
		return new ResponseEntity<String>("로그 아웃 되었습니다.", HttpStatus.OK);
				
	}
	@GetMapping("/ping")
	public Map<String, Object> ping(@AuthenticationPrincipal UserDetails ud) {
		return Map.of("userName", ud.getUsername());
	}
}












