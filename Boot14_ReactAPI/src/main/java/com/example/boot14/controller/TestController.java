package com.example.boot14.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {
	//파일 저장경로
	@Value("${file.location}")
	private String fileLocation;
		
	//이미지 업로드 테스트
	@PostMapping("/image/upload")
	public Map<String, Object> imageUpload(MultipartFile image){
		//원본 파일명
		String orgFileName=image.getOriginalFilename();
		//저장할 파일명 구성
		String saveFileName=UUID.randomUUID().toString();
		//파일시스템에 저장할 경로
		String filePath=fileLocation+File.separator+saveFileName;
		//파일객체 생성
		File dest=new File(filePath);
		try {
			//업로드된 이미지를 원하는 곳으로 전송(copy) 
			image.transferTo(dest);
		}catch(Exception e) {}
		//업로드된 정보를 json 으로 응답하기 위해 
		Map<String, Object> map=new HashMap<>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		return map;
	}
}























