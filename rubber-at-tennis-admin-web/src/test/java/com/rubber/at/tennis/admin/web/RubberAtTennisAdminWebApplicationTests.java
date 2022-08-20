package com.rubber.at.tennis.admin.web;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest
@ComponentScan("com.rubber.*")
@MapperScan("com.rubber.at.tennis.admin.**.dao.mapper")
class RubberAtTennisAdminWebApplicationTests {





    @Test
    public void doTest(){


    }



}
