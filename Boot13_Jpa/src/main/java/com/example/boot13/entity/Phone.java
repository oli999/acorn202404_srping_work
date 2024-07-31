package com.example.boot13.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private int price;
	
}
