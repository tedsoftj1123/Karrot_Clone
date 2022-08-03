package com.example.karrotmarket.repository;

import com.example.karrotmarket.domain.Heart;
import com.example.karrotmarket.domain.Item;
import com.example.karrotmarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByMemberAndItem(Member member, Item item);

    void deleteByMemberAndItem(Member member, Item item);
}
