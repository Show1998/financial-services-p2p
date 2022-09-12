package com.cpp.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.cpp.usercenter","com.cpp.jwt","com.cpp.utils"})
public class P2pUserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pUserCenterApplication.class, args);
    }

}
