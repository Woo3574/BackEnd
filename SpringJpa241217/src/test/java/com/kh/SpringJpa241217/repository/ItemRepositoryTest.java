package com.kh.SpringJpa241217.repository;

import com.kh.SpringJpa241217.constant.ItemSellStatus;
import com.kh.SpringJpa241217.entity.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository; // 필드로 생성자 주입

    @Test
    @DisplayName("상품 저장 테스트") // 테스트 이름
    public void createItemTest() {
        for (int i = 0; i <= 10; i++) {
            Item item = new Item(); // 빈 객체 만듬
            item.setItemNum("테스트 상품" + i);
            item.setPrice(1000 * i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            if( i % 2 == 0) item.setItemSellStatus(ItemSellStatus.SELL);
            else item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
            log.info("Item 저장 : {}", saveItem);
        }
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNumTest() {
        this.createItemTest(); // 10개의 상품을 생성'
        List<Item> itemList = itemRepository.findByItemNum("테스트 상품5");
        for(Item item : itemList) {
            log.info("상품 조회 테스트 : {}", item);
        }
    }

    @Test
    @DisplayName(" OR 조건 테스트")
    public void findByItemNumOrItemDetailTest() {
        this.createItemTest();
        List<Item> findItem = itemRepository.findByItemNumOrItemDetail("테스트 상품2","테스트 상품 상세 설명3");
        for(Item item : findItem) {
            log.info("상품 OR 테스트 : {}", item);
        }
    }

    @Test
    @DisplayName("상품명 및 상세 설명으로 조회")
    public void findByPriceLessThanTest() {
        this.createItemTest();
        List<Item> lessThanItem = itemRepository.findByPriceLessThan(5000);
        for(Item item : lessThanItem) {
            log.info("상품 조회 테스트 : {}", item);
        }
    }

    @Test
    @DisplayName("설정 금액이상인 판매 중 상품 조회")
    public void findByPriceGreaterThanEqualAndItemSellStatusTest() {
        this.createItemTest();
        List<Item> sellItem = itemRepository.findByPriceGreaterThanEqualAndItemSellStatus(5000,
                ItemSellStatus.SELL);
        for (Item item : sellItem) {
            log.info("상품 조회 테스트 : {}", item);
        }
    }

    @Test
    @DisplayName("상품 가격 내림차순")
    public void findAllByOrderByPriceDescTest() {
        this.createItemTest();
        List<Item> OrderByItem = itemRepository.findAllByOrderByPriceDesc();
        for (Item item : OrderByItem) {
            log.info("가격에 대한 내림차순 정렬 : {}", item);
        }
    }


    @Test
    @DisplayName("특정키워드 검색")
    public void findByItemNumContainingTest() {
        this.createItemTest();
        List<Item> containingByItem = itemRepository.findByItemNumContaining("상품10");
        for (Item item : containingByItem) {
            log.info("상품 조회 테스트 : {}", item);
        }
    }


    @Test
    @DisplayName("상품명과 가격으로 검색")
    public void findByItemNumAndPriceTest() {
        this.createItemTest();
        List<Item> findByItem = itemRepository.findByItemNumAndPrice("테스트 상품10",10000);
        for (Item item : findByItem) {
            log.info("상품 조회 테스트 : {}", item);
        }
    }

    // JPQL 테스트
    @Test
    @DisplayName("JPQL 상품 상세 정보 텍스트")
    public void findByJPQLTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("설명1");
        for ( Item item : itemList) {
            log.info("JPQL Like 검색 : {}", item);
        }
    }
}