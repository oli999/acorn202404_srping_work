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
	
	@PostMapping("/cafe/update")
	public String update(CafeDto dto) {
		//서비스객체를 이용해서 수정 반영하고 
		service.updateContent(dto);
		//해당글 자세히 보기로 리다일렉트 이동 (GET 방식 parameter 로  글번호도 전달해야 한다)
		return "redirect:/cafe/detail?num="+dto.getNum();
	}
	
	@GetMapping("/cafe/updateform")
	public String updateForm(Model model, int num) {
		//수정할 글정보를 Model 객체에 담아주는 서비스 메소드 
		service.getData(model, num);
		return "cafe/updateform";
	}
	
	@GetMapping("/cafe/delete")
	public String delete(int num) { // /cafe/delete?num=x
		//서비스 메소드를 이용해서 삭제하기 
		service.deleteContent(num);
		return "redirect:/cafe/list";
	}
	
	
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












