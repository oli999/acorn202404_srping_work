package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot11.dto.GalleryDto;
import com.example.boot11.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired private GalleryService service;
	
	@GetMapping("/gallery/delete")
	public String delete(int num) {
		service.deleteOne(num);
		return "redirect:/gallery/list";
	}
	
	@GetMapping("/gallery/detail")
	public String detail(Model model, int num) {
		//num 에는 자세히 보여줄 gallery 의 pk 가 들어 있다. 
		service.selectOne(model, num);
		
		return "gallery/detail";
	}
	
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto) {
		
		//String caption 과 MultipartFile image 가 들어 있는 GalleryDto 를 서비스에 전달해서 저장한다 
		service.addToGallery(dto);
		
		return "redirect:/gallery/list";
	}
	
	@GetMapping("/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	
	@GetMapping("/gallery/list")
	public String list(Model model,@RequestParam(defaultValue = "1") int pageNum) {
		/*
		 *  서비스에 Model 객체와 pageNum 을 전달해서 
		 *  Model 에 pageNum 에 해당하는 글 목록이 담기도록 한다.
		 *  Model 에 담긴 내용을 view page(Thymeleaf 템플릿페이지) 에서 사용할수 있다
		 */
		service.selectPage(model, pageNum);
		
		return "gallery/list";
	}
}
















