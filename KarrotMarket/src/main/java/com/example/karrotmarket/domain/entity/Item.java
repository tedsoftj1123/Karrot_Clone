package com.example.karrotmarket.domain.entity;

import com.example.karrotmarket.domain.controller.dto.req.ModifyItemRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Size(max = 20)
    private String itemName;
    @NotNull
    @Size(max = 200)
    private String itemDescription;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private int price;
    @NotNull
    private boolean negotiable;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @NotNull
    private String itemImgUrl;
    @NotNull
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

    public void modifyItemInfo(ModifyItemRequest changeInfo) {
        this.itemName = changeInfo.getItemName();
        this.itemDescription = changeInfo.getItemDescription();
        this.category = changeInfo.getItemCategory();
        this.price = changeInfo.getItemPrice();
        this.negotiable = changeInfo.isItemNegotiable();
    }
}
