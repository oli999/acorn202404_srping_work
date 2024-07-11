package com.example.boot11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot11.dto.UserDto;
import com.example.boot11.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	//SecurityConfig 클래스에서 bean 으로 등록된 객체 주입받기 
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public void addUser(UserDto dto) {
		//role 은 일반 USER 로 넣어준다.
		dto.setRole("USER");
		//비밀번호 암호화 해서 dto 에 다시 넣기
		String encodedPwd=encoder.encode(dto.getPassword());
		dto.setPassword(encodedPwd);
		//dao 객체를 이용해서 DB 에 저장하기
		dao.insert(dto);
	}

	@Override
	public void getInfo(Model model) {
		//개인 정보를 본다는 것은 이미 로그인을 한 상태인데 로그인된 userName 은 어떻게 얻어내지???
		//Spring Security 의 기능을 통해서 얻어낸다 
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//로그인된 userName 을 이용해서 UserDto 객체를 dao 를 통해서 얻어낸다.
		UserDto dto=dao.getData(userName);
		//Model 객체에 담아주기
		model.addAttribute("dto", dto);
	}

	@Override
	public void updateUser(UserDto dto) {
		
	}

	@Override
	public void updatePassword(UserDto dto) {
		//1. 로그인된 userName 을 얻어낸다.
		
		//2. 기존의 비밀번호를 DB 에서 읽어와서
		
		//3. 입력한(암호화 되지 않은 구비밀번호) 와 일치하는지 비교 해서
		
		//4. 만일 일치하지 않으면 Exception 을 발생 시킨다.
		
		//5. 일치하면 새비밀번호를 암호화해서 dto 에 담은 다음
		
		//6. userName 도 dto 에담고 
		
		//7. DB 에 비밀번호 수정반영를 한다.
		
	}

}



















