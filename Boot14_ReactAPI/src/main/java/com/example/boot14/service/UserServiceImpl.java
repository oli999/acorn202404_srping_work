package com.example.boot14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.boot14.dto.UserDto;
import com.example.boot14.repository.UserDao;

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
		
		dao.insert(dto);
	}

	@Override
	public UserDto getInfo() {
		//개인 정보를 본다는 것은 이미 로그인을 한 상태인데 로그인된 userName 은 어떻게 얻어내지???
		//Spring Security 의 기능을 통해서 얻어낸다 
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();	
		
		return dao.getData(userName);
	}

	@Override
	public void updateUser(UserDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(UserDto dto) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean canUse(String userName) {
		// userName 을 이용해서 UserDto 를 읽어와 본다.  없으면 null , null 이면 사용 가능하다 
		UserDto dto=dao.getData(userName);
		//사용 가능한지 여부
		boolean canUse = dto == null;
		
		return canUse;
	}

}












