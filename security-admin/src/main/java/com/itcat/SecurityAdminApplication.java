package com.itcat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.itcat.repository"})
@SpringBootApplication
public class SecurityAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityAdminApplication.class, args);
    }
}
