package com.example.boot14.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		//여기서 세션 유지 시간 설정
    	HttpSession session=request.getSession();
        session.setMaxInactiveInterval(60*60*30);//초단위로 설정
        
        //Authentication 객체의 메소드를 이용해서 지금 로그인된 사용자에 대한 자세한 정보를 얻어낼수 있다.
        String userName=authentication.getName();
        System.out.println("로그인된 사용자:"+userName);
        
        //로그인 성공을 응답하기 위한 forward 이동 (UserController)
    	RequestDispatcher rd=request.getRequestDispatcher("/user/login_success");
    	rd.forward(request, response);
		
	}
}




