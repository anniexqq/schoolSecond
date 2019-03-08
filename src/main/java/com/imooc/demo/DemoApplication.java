package com.imooc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("----------------启动校园二手交易后台系统------------------");
		SpringApplication.run(DemoApplication.class, args);
	}
}
