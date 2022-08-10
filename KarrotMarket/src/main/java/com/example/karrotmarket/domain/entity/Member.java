package com.example.karrotmarket.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Size(max = 10)
    @Column(unique = true)
    private String memberId;

    @Size(max = 5)
    private String memberName;

    @Size(max = 15)
    @Column(unique = true)
    private String memberEmail;

    @Size(min = 5, max = 20)
    private String memberPassword;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<DealRequest> dealRequests = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Heart> hearts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;
}
