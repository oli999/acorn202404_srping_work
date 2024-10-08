package com.example.boot14;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot14.dto.UserDto;
import com.example.boot14.service.UserService;
import com.example.boot14.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // 객체(dto 등) 에 저장된 내용를 json 문자열로 변경해주는 기능을 가지고 있는 객체 
    ObjectMapper oMapper=new ObjectMapper();
    
    @Test
    void authTest() throws Exception {
    	//실제 DB 에 존재하는 정보 
    	UserDto dto=UserDto.builder()
    			.userName("kimgura")
    			.password("@1111")
    			.build();
    	
    	mockMvc.perform(post("/auth")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(oMapper.writeValueAsString(dto)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$").isNotEmpty())
    		.andDo(result -> {
    			String jwt = result.getResponse().getContentAsString();
    			//문자열이 Bearer 로 시작되는지 확인
    			assertTrue(jwt.startsWith("Bearer"));
    			System.out.println(jwt);
    		});
    }
    
    @Test
    void authFailTest() throws Exception {
    	//실제 DB 에 존재하지 않는 정보  
    	UserDto dto=UserDto.builder()
    			.userName("xxx")
    			.password("yyy")
    			.build();
    	
    	mockMvc.perform(post("/auth")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(oMapper.writeValueAsString(dto)))
    			.andDo(print())
    		.andExpect(status().is4xxClientError()); // 400 번때 에러 응답이 되는지 확인 
    }
    
    // 아이디 사용가능 여부 테스트  ( 존재하는 아이디 => false, 존재하지 않는 아이디 => true)
    @Test
    void checkUserNameTest() throws Exception {
    	//사용 불가능한 아이디
    	String userName1 = "kimgura";
    	mockMvc.perform(get("/user/check_username/"+userName1))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.canUse", is(false)));
    	
    	//사용 가능한 아이디
    	String userName2 = UUID.randomUUID().toString();
    	mockMvc.perform(get("/user/check_username/"+userName2))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.canUse", is(true)));
    	
    }
    
    //회원 가입 테스트 
    @Test
    @Transactional //임시 반영만 하고 테스트후에 자동 rollback 된다.
    void testAddUser() throws Exception{
    	//회원가입정보를 UserDto 에 담는다
    	UserDto dto=UserDto.builder()
    			.userName("testuser")
    			.password("testpwd")
    			.email("testemail@")
    			.build();
    	mockMvc.perform(post("/user")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(oMapper.writeValueAsString(dto)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.isSuccess", is(true)));
    }
    
    @Test
    @Transactional
    @WithUserDetails("kimgura")
    void testUpdateUser() throws Exception{
    	
    	mockMvc.perform(patch("/user")
    			.param("userName", "kimgura")
    			.param("email", "testemail@"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.isSuccess", is(true)));
    	
    }
    
}




















