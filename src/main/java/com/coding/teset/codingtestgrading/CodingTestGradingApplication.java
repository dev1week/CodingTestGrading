package com.coding.teset.codingtestgrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CodingTestGradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingTestGradingApplication.class, args);
    }

}
