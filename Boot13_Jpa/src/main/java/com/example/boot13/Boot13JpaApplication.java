package com.example.boot13;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.boot13.entity.Phone;
import com.example.boot13.repository.PhoneRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@SpringBootApplication
public class Boot13JpaApplication {
	// JPA EntityManagerFactory 객체 주입 받기 
	@Autowired
	EntityManagerFactory emf;
	
	// PhoneRepository 객체 주입 받기 
	@Autowired 
	PhoneRepository phoneRepo;
	
	@PostConstruct
	public void init() {
		// 이 객체를(객체안에 있는정보) 영속화(persistance) 즉 영구 저장하고 싶다!
		Phone p1=Phone.builder().name("아이폰15").company("apple").price(150).build();
		Phone p2=Phone.builder().name("겔럭시S24").company("SAMSUNG").price(130).build();
		Phone p3=Phone.builder().name("아이폰16").company("apple").price(200).build();
		Phone p4=Phone.builder().name("겔럭시S25").company("SAMSUNG").price(170).build();
		
		//EntityManager 객체 얻어내서 
		EntityManager em=emf.createEntityManager();
		//하나의 트랜젝션을 시작한다 
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			//EntityManager 객체의 메소드를 이용해서 저장한다.
			em.persist(p1);
			em.persist(p2);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		phoneRepo.save(p3);
		phoneRepo.save(p4);
		
		List<Phone> phoneList=phoneRepo.findAll();
		for(Phone tmp:phoneList) {
			System.out.println(tmp.getId()+"|"+tmp.getName()+"|"+tmp.getCompany());
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Boot13JpaApplication.class, args);
	}

}










