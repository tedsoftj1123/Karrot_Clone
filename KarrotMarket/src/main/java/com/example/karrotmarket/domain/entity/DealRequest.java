package com.example.karrotmarket.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealRequest {
    @Id @GeneratedValue
    private Long id;
    @NotNull
    private int price;
    @NotNull
    private String location;
    @NotNull
    private boolean isAccepted;

    @Embedded
    private DealRequestTimeDetail timeDetail;

    @Embedded
    private DealRequestLocationDetail locationDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private String dealMemberId;

    public void toAccepted() {
        this.isAccepted = true;
    }

}
