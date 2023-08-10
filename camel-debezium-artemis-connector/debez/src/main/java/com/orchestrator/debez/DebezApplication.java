package com.orchestrator.debez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.orchestrator.debez.*"})
public class DebezApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebezApplication.class, args);
    }
    

 
}
