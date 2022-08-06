package com.example.karrotmarket.domain.repository;

import com.example.karrotmarket.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberEmail(String email);
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByMemberEmail(String memberEmail);
}
