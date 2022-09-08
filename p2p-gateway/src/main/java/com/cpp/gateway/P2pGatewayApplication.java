package com.cpp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.cpp.jwt","com.cpp.gateway"})
public class P2pGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pGatewayApplication.class, args);
    }

}
