package com.example.karrotmarket.domain.entity;

import lombok.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {
    @Id @Column(name = "refresh_key")
    private String key;

    @Column(name = "refresh_val")
    private String value;

    public void updateValue(String token) {
        this.value = token;
    }
}
