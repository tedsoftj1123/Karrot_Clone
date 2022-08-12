package com.example.karrotmarket.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

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
    private boolean isAccepted;

    @Embedded
    private DealRequestTimeDetail timeDetail;

    @Embedded
    private DealRequestLocationDetail locationDetail;

    private LocalDateTime sendTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member itemBuyer;

    @NotNull
    private String itemOwner;

    public void toAccepted() {
        this.isAccepted = true;
    }

}
