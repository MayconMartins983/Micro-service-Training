package com.example.serviceauthh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceAuthhApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthhApplication.class, args);
    }

}
