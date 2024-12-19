package com.kh.SpringJpa241217.repository;

import com.kh.SpringJpa241217.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 기본적인 CRUD는 이미 구현되어 있음
}
