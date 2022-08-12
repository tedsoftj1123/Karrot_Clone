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

    @Size(max = 20)
    @Column(unique = true)
    private String memberId;

    @Size(max = 5)
    private String memberName;

    @Size(min = 5, max = 80)
    private String memberPassword;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();

    //내가 보낸 거래요청
    @OneToMany(mappedBy = "itemBuyer", cascade = CascadeType.REMOVE)
    private List<DealRequest> dealRequests = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Heart> hearts = new ArrayList<>();}
