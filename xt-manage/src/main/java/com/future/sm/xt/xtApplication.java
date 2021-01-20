package com.future.sm.xt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.future.sm.xt.mapper")
public class xtApplication {
    public static void main(String[] args) {
        SpringApplication.run(xtApplication.class,args);
    }
}
