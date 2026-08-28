package com.razarpay.razerpaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RazerPayDemo {
	
	public static void main(String[] args) {
		SpringApplication.run(RazerPayDemo.class, args);
	}

}
