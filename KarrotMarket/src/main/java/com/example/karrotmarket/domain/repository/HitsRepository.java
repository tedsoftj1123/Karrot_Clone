package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.Hits;
import com.example.karrotmarket.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitsRepository extends JpaRepository<Hits, Long> {
    boolean existsByMemberIdAndItem(Long memberId, Item item);
}
