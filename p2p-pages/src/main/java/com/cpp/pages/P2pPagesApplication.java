package com.cpp.pages;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.cpp.pages","com.cpp.jwt"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.cpp.pages.mapper")
public class P2pPagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pPagesApplication.class, args);
    }

}
