package com.example.karrotmarket.domain.user.domain;

import com.example.karrotmarket.domain.user.domain.type.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "varchar(20)", unique = true)
    private String accountId;

    @NotNull
    @Column(columnDefinition = "varchar(30)", unique = true)
    private String accountEmail;

    @Embedded
    private Address address;

    @Builder
    public User(String accountId, String accountEmail,
                String dong, String city) {
        this.accountId = accountId;
        this.accountEmail = accountEmail;
        this.address = new Address(dong, city);
    }
}
