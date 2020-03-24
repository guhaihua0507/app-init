package com.ghh.sample;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.ghh.sample.mapper")
@ComponentScan(basePackages = {"com.ghh.sample.config",
		"com.ghh.sample.controller",
		"com.ghh.sample.service"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
