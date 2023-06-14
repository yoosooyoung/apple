package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.example.demo", annotationClass = Mapper.class)
@SpringBootApplication
public class AppleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppleApplication.class, args);
	}

}
