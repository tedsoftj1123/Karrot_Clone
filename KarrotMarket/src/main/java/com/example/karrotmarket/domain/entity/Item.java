package com.example.karrotmarket.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private boolean negotiable;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String itemImgUrl;

    private int views;

    @OneToMany(mappedBy = "item")
    private List<Heart> likeCount = new ArrayList<>();

    public void changeItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public void addViewCount() {
        this.views += 1;
    }

    public void changeItemCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
