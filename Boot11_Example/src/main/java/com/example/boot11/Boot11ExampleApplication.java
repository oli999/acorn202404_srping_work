package com.example.boot11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/*
 *  @PropertySource(value=" 커스텀 properties 파일의 위치" )
 *  classpath: 는 resources 폴더를 가리킨다.
 */
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot11ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot11ExampleApplication.class, args);
	}

}
