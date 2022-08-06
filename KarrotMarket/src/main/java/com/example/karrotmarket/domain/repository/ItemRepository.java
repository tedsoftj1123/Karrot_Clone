package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
