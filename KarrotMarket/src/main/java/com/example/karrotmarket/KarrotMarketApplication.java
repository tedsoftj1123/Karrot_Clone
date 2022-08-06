package com.example.karrotmarket;

import com.example.karrotmarket.global.security.jwt.JwtProperties;
import com.example.karrotmarket.domain.img.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({
        JwtProperties.class,
        FileUploadProperties.class
})
public class KarrotMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarrotMarketApplication.class, args);
    }

}
