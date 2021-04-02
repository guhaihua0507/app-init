package com.ghh.sample.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan(basePackages = "com.ghh.sample.mapper")
@ComponentScan(basePackages = {"com.ghh.sample.config",
        "com.ghh.sample.controller",
        "com.ghh.sample.service",
        "com.ghh.sample.security"})
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
