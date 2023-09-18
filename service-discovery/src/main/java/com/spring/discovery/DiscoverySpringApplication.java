package com.spring.discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableAutoConfiguration
@EnableEurekaServer
public class DiscoverySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoverySpringApplication.class, args);
    }
}
