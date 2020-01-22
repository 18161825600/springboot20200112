package com.example.springboot20200112;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class Springboot20200112Application  {

    public static void main(String[] args) {
        SpringApplication.run(Springboot20200112Application.class, args);
    }
}