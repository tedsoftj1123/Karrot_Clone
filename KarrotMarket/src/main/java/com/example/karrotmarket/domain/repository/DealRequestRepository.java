package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.DealRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRequestRepository extends JpaRepository<DealRequest, Long> {
}
