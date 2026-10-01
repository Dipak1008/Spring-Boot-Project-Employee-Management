package com.springboot.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootEmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmployeeManagementApplication.class, args);
		System.out.println("Start Employee Management....");
	}

}
