package com.example.karrotmarket.domain.user.domain;

import com.example.karrotmarket.domain.*;
import lombok.*;

import javax.persistence.*;
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

    private String memberId;

    private String memberName;

    private String memberEmail;

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
