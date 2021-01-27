package com.future.sm.xt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//告知springboot程序,启动时不要加载数据源配置
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
