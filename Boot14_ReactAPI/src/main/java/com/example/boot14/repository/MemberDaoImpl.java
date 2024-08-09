package com.example.boot14.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot14.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{
	// mybatis 기반의 dao 에서 필요한 의존객체 
	@Autowired SqlSession session;
	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto);
	}
	@Override
	public void update(MemberDto dto) {
		session.update("member.update", dto);
	}
	@Override
	public void delete(int num) {
		session.delete("member.delete", num);
	}
	@Override
	public MemberDto getData(int num) {
		return session.selectOne("member.getData", num);
	}
	@Override
	public List<MemberDto> getList() {
		return session.selectList("member.getList");
	}
	@Override
	public int getSequence() {
		return session.selectOne("member.getSequence");
	}

}





