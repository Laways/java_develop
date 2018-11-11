package com.huang.demo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.huang")
@MapperScan("com.huang.demo1.dao")
public class Demo1Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Demo1Application.class).run(args);
//        SpringApplication.run(Demo1Application.class, args);
        System.out.println("success");
    }
}
