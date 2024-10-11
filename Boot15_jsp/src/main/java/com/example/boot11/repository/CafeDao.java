package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.CafeDto;

public interface CafeDao {
	//pageNum 과 검색조건에 해당하는 글목록을 리턴하는 메소드 
	public List<CafeDto> getList(CafeDto dto);
	//검색 조건에 해당하는 글의 갯수를 리턴하는 메소드 
	public int getCount(CafeDto dto);
	//글추가 
	public void insert(CafeDto dto);
	//글번호에 해당하는 글정보 리턴하는 메소드
	public CafeDto getData(int num);
	//검색조건과 글번호를 CafeDto 에 담아서 전달하면 글하나의 정보를 리턴하는 메소드(이전글의 글번호, 다음글의 글번호 포함)
	public CafeDto getDetail(CafeDto dto);
	//글삭제 
	public void delete(int num);
	//글수정
	public void update(CafeDto dto);
}












