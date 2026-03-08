package com.chitra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.chitra.entity")
public class ChitraApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChitraApplication.class, args);
    }

}