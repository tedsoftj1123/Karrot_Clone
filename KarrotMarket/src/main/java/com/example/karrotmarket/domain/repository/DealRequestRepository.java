package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.DealRequest;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRequestRepository extends JpaRepository<DealRequest, Long> {
    List<DealRequest> findAllByItemOwnerOrderBySendTimeDesc(String ownerId);

    boolean existsByItemBuyerAndItem(Member member, Item item);

    void deleteAllByItem(Item item);
}
