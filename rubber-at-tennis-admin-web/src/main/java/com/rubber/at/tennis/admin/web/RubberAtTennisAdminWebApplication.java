package com.rubber.at.tennis.admin.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.rubber.*")
@MapperScan("com.rubber.at.tennis.admin.**.dao.mapper")
@SpringBootApplication
public class RubberAtTennisAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RubberAtTennisAdminWebApplication.class, args);
    }

}
