package com.example.boot14.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




// bean 으로 만들기 위한 어노테이션 
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired PasswordEncoder encoder;
	
	//Spring Security 가 로그인 처리시 호출하는 메소드 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username 을 이용해서 UserRepository 에서 UserDto(사용자정보) 를 읽어와야한다 
		
		//DB 에서 읽어온 username 과 비밀번호(암호화된 비밀번호) 과 role 이라고 가정
		String password=encoder.encode("1234");
		String role="USER";
		//만일 username 이 park 가 아니라면 
		if(!username.equals("park")) {
			//예외를 발생시킨다
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다");
		}
		
		//있다면 해당정보를 이용해서 UserDetails 객체를 만들어서 리턴해 주어야 한다 

		//권한 목록을 List 에 담아서  (지금은 1개 이지만)
		List<GrantedAuthority> authList=new ArrayList<>();
		// Authority 는 접두어로 "ROLE_" 가 붙어 있어야 한다. 
		authList.add(new SimpleGrantedAuthority("ROLE_"+role));
		
		//UserDetails 객체를 생성해서 UserDto 에 있는 username 과 password, 위에 있는 권한목록을 넣어준다.
		UserDetails ud=new User(username, password, authList);
		
		//리턴해준다.
		return ud;
	}

}
























