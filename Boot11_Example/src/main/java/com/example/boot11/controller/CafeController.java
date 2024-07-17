package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot11.dto.CafeDto;
import com.example.boot11.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired private CafeService service;
	
	@GetMapping("/cafe/detail")
	public String detail(Model model, CafeDto dto) {
		service.getDetail(model, dto);
		return "cafe/detail";
	}
	
	@PostMapping("/cafe/insert")
	public String insert(CafeDto dto) {
		service.saveContent(dto);
		return "cafe/insert";
	}
	
	@GetMapping("/cafe/insertform")
	public String insertForm() {
		
		return "cafe/insertform";
	}
	
	@GetMapping("/cafe/list")
	public String list(Model model, CafeDto dto) {
		//pageNum 또는 keyword 가 파라미터로 전달된다면 dto 에 들어 있다. 
		service.getList(model, dto);
		
		// templates/cafe/list.html 타임리프 페이지에서 응답 
		return "cafe/list";
	}
}












