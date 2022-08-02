package com.example.karrotmarket.repository;

import com.example.karrotmarket.domain.Hits;
import com.example.karrotmarket.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitsRepository extends JpaRepository<Hits, Long> {
    boolean existsByMemberIdAndItem(Long memberId, Item item);
}
