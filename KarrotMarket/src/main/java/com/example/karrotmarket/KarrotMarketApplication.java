package com.example.karrotmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KarrotMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarrotMarketApplication.class, args);
    }

}
