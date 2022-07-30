package com.example.karrotmarket.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String itemName;

    private String itemDescription;

    private LocalDateTime createdAt;

    private int price;

    private boolean canNegotiate;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
