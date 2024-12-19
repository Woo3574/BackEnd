package com.kh.SpringJpa241217.entity;

import com.kh.SpringJpa241217.constant.ItemSellStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // 해당 클래스가 엔티티임을 나타냄 (즉, 데이터베이스 테이블을 의미)
@Table(name = "item")
@Data
public class Item {
    @Id // 기본키 필드 지장
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품코드

    @Column(nullable = false, length = 50)
    private String itemNum; // 상품명

    @Column(nullable = false) // not null
    private int price;

    @Column(nullable = false)
    private int stockNumber; // 재고 수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}

