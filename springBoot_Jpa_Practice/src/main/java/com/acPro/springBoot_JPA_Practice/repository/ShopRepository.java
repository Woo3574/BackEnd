package com.acPro.springBoot_JPA.repository;

import com.acPro.springBoot_JPA.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Shop 엔티티에 대한 CRUD 작업을 처리하는 Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    // Containing: name 또는 addr 필드에 키워드가 포함된 데이터를 찾습니다.
    // IgnoreCase: 대소문자 구분 없이 검색을 합니다.
    List<Shop> findByNameContainingIgnoreCase(String name);
}
