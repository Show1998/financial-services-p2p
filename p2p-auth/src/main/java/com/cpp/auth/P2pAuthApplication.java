package com.cpp.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.cpp.jwt","com.cpp.auth","com.cpp.utils"})
public class P2pAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pAuthApplication.class, args);
    }

}
