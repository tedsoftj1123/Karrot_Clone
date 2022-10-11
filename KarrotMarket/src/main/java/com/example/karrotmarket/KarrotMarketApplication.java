package com.example.karrotmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KarrotMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarrotMarketApplication.class, args);
    }

}
