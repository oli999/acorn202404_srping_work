package com.example.boot13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Member;

// extends JpaRepository<Entity type, Entity 의 id type >
public interface MemberRepository extends JpaRepository<Member, Long>{

}
