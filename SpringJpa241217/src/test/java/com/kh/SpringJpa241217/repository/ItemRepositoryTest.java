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
            item.setItemSellStatus(ItemSellStatus.SELL);
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
    @DisplayName("상품명 및 상세 설명으로 조회")
    public void findByItemNumOrItemDetailTest() {
        this.createItemTest();
        List<Item> findItem = itemRepository.findByItemNumOrItemDetail("테스트 상품2","테스트 상품 상세 설명3");
        for(Item item : findItem) {
            log.info("상품 조회 테스트 : {}", item);
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
}