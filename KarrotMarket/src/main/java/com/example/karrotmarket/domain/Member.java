package com.example.karrotmarket.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String memberId;

    private String memberName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<DealRequest> dealRequests = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles = new ArrayList<>();
}
