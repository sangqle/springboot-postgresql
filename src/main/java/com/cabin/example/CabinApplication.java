package com.cabin.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CabinApplication {
    public static void main(String[] args) {
       SpringApplication app = new SpringApplication(CabinApplication.class);
       app.run(args);
    }
}