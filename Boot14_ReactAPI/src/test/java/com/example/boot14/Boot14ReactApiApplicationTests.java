package com.example.boot14;

// 메소드를 static import 하면 이 클래스에서 import 메소드를 마치 자기꺼 처럼 쓸수 있다. 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot14.dto.MemberDto;
import com.example.boot14.repository.MemberDao;

// spring boot  application 을 테스트 하기 위한 어노테이션 
@SpringBootTest
// DB 에 insert, update, delete 가 실제 반영되지 않고 ROLLBACK 되도록 한다. 
@Transactional 
class Boot14ReactApiApplicationTests {
	
	
	@Autowired MemberDao dao;
	// @Test 어노테이션을 이용해서 테스트 case 메소드를 작성한다 
	@Test
	public void hello() {
		// 어떤 메소드가 리턴해주는 값이라고 가정 
		int sum = 2;
		// 그 값은 반드시 2 여야 한다는 단언(assertion) 
		// sum 이 2 이면 테스트는 pass 이고  2 가 아니면 테스트는 fail 이다.
		assertEquals(sum, 2);
	}
	
	@Test 
	void testIsNull() {
		String str=null;
		// 반드시 null 이여야 하는 단언 
		// null 이면 pass , null 이 아니면 fail
		assertNull(str);
	}
	
	@Test
	void testNotNull() {
		String str="kimgura";
		// 반드시 null 이 아니여야 한다는 단언
		// null 이 아니면 pass, null 이면 fail
		assertNotNull(str);
	}
	
	@Test
	void testIsTrue() {
		boolean isRun = false;
		// 반드시 true 여야 한다는 단언 
		// true 면 pass, false 면 fail
		assertTrue(isRun);
		assertTrue(isRun , "달릴지 여부는 true 여야 한다");
	}
	
	@Test
	void testMemberDaoNotNull() {
		//실제로 dao 가 null 이 아닌지 여부를 알수 있다.
		assertNotNull(dao);
	}
	/*
	 *  name :  test_name
	 *  addr :  test_addr
	 *  인 회원정보를 저장하고 작업의 성공여부 테스트하기 
	 */
	@Test
	void testMemberDaoInsert() {
		//회원 번호를 얻어낸다
		int num=dao.getSequence();
		//저장할 회원 정보를 MemberDto 에 담는다.
		MemberDto dto=MemberDto.builder()
				.num(num).name("test_name").addr("test_addr").build();
		//DB 에 저장한다.
		dao.insert(dto);
		//저장된 결과를 다시 select 한다.
		MemberDto savedDto = dao.getData(num);
		//제대로 잘 저장이 되었는지 비교해 본다.
		assertEquals(savedDto.getName(), "test_name");
		assertEquals(savedDto.getAddr(), "test_addr");		
	}

}




















