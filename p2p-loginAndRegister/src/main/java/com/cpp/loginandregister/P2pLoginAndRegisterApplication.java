package com.cpp.loginandregister;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan(basePackages = "com.cpp.loginandregister.mapper")
@SpringBootApplication
public class P2pLoginAndRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pLoginAndRegisterApplication.class, args);
    }

}
