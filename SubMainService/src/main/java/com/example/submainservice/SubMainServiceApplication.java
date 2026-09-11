package com.example.submainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SubMainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubMainServiceApplication.class, args);
    }

}
