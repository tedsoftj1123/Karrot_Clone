package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.Category;
import com.example.karrotmarket.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByCreatedAtDesc();

    List<Item> findAllByCategory(Category category);
}
