package com.example.boot13.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot13.dto.MemberDto;
import com.example.boot13.entity.Member;
import com.example.boot13.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	// JpaRepository 를 상속받아서 만든 MemberRepository 주입받기 
	@Autowired MemberRepository repo;
	
	@Override
	public void getList(Model model) {
		/*
		//Entity 가 여러개 들어 있는 List 를 이용해서  List<MemberDto> 를 만들어서 Model 객체에 담아야한다. 
		List<Member> enList=repo.findAll();	
		List<MemberDto> list=new ArrayList<>();
		//반복문 돌면서 Member 객체를 순서대로 참조 
		for(Member tmp:enList) {
			list.add(MemberDto.toDto(tmp));
		}
		*/
		
		//List<MemberDto> list=repo.findAll().stream().map(item -> MemberDto.toDto(item)).toList();
		
		/*
		 *  - 메소드 참조 표현식
		 *  클래스명 :: 메소드명
		 */
		List<MemberDto> list=repo.findAll().stream().map(MemberDto::toDto).toList();
		
		model.addAttribute("list", list);
	}

	@Override
	public void insert(MemberDto dto) {
		// dto 를 entity 로 변경을 해서 
		//Member m=Member.toEntity(dto);
		// 저장한다 
		//repo.save(m);
		//위의 2줄의 코드를 한줄로 표현하면
		repo.save(Member.toEntity(dto));
	}

	@Override
	public void delete(Long num) {
		// 메소드를 이요해서 삭제 
		repo.deleteById(num);
	}

	@Override
	public void getData(Long num, Model model) {
		//회원 번호를 이용해서 Member entity 객체를 얻어낸다.
		Member m=repo.findById(num).get();
		//dto 로 변경
		MemberDto dto=MemberDto.toDto(m);
		//Model 객체에 담는다.
		model.addAttribute("dto", dto);
	}

	@Override
	public void update(MemberDto dto) {
		// save() 메소드는 insert 와 update 겸용이다
		repo.save(Member.toEntity(dto));
	}

}








