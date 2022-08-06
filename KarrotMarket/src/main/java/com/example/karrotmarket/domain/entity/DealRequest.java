package com.example.karrotmarket.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealRequest {
    @Id @GeneratedValue
    private Long id;

    private int price;

    private String location;

    private boolean isAccepted;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    private String dealMemberId;

    public void toAccepted() {
        this.isAccepted = true;
    }

}
