package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.Heart;
import com.example.karrotmarket.domain.entity.Item;
import com.example.karrotmarket.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByMemberAndItem(Member member, Item item);

    void deleteByMemberAndItem(Member member, Item item);
}
