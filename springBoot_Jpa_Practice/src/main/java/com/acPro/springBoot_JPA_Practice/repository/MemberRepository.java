package com.acPro.springBoot_JPA_Practice.repository;

import com.acPro.springBoot_JPA_Practice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
}
