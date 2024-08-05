package com.example.boot13.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="PHONE_INFO") //테이블명 
public class Phone {
	// id 라는 칼럼은 primary key 값으로 설정되도록 하겠다 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;  //필드명이 칼럼명이 된다. 
	private String company;
	private String name;
	// null 값이 가능한 Entity 필드는 반드시 참조 data type 이어야 한다 
	@Column(nullable = true)
	private Integer price;  
	//등록일을 저장하고 싶다면?
	@Column(nullable = false)
	private Date regdate;
	
	//Entity 를 영속화 하기 직전에 뭔가 작업할게 있으면 @PrePersist 어노테이션을 활용하면 된다.
	@PrePersist
	public void onPersist() {
		//오라클에서 데이터를 넣을때 SYSDATE 함수를 이용해서 넣는 효과를 낸다 
		regdate = new Date();
	}
}









