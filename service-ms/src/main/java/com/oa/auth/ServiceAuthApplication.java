package com.oa.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Jason
 * @Date: 2023/3/4 21:29 21 29
 */

@ComponentScan("com.service.config.mbp")
@ComponentScan("com.service.config.exception")
@SpringBootApplication
@MapperScan(basePackages = {"com.oa.auth.mapper"})
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}

