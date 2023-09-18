package com.spring.micro_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSpringBootApplication.class, args);
    }

}
