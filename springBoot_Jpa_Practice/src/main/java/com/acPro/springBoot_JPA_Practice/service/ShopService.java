package com.acPro.springBoot_JPA_Practice.service;

import com.acPro.springBoot_JPA_Practice.dto.ShopReqDto;
import com.acPro.springBoot_JPA_Practice.dto.ShopResDto;
import com.acPro.springBoot_JPA_Practice.entity.Shop;
import com.acPro.springBoot_JPA_Practice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j // log 정보 출력
@Service // Spring container(에) 빈(객체)등록
@RequiredArgsConstructor // final 생성자 생성
@Transactional // 여러개의 작업을 하나의 논리적 단위로 묶는 것

public class ShopService {
    private final ShopRepository shopRepository;

    // 매장 목록
    public List<ShopResDto> listShop() {
        List<Shop> shops = shopRepository.findAll();
        return shops.stream()
                .map(shop -> new ShopResDto(shop.getName(), shop.getAddr()))
                .collect(Collectors.toList());
    }

    // 매장 추가
    public ShopResDto addShop(ShopResDto shopResDto) {

        Shop existShop = shopRepository.findByName(shopResDto.getName());
        if (existShop != null) {
            throw new RuntimeException("이미 존재하는 매장입니다.");
        }

        // ShopResDto(를) Shop 엔티티로 변환
        Shop shop = new Shop();
        shop.setName(shopResDto.getName());
        shop.setAddr(shopResDto.getAddr());

        // Shop 엔티티를 DB에 저장
        Shop saveShop = shopRepository.save(shop);

        ShopResDto responseDto = new ShopResDto();
        responseDto.setName(saveShop.getName());
        responseDto.setAddr(saveShop.getAddr());

        return responseDto;
    }

    // 검색
    public List<ShopReqDto> searchShops(String Keyword) {
        List<Shop> shops = shopRepository.findByNameContainingIgnoreCase(Keyword);

        return shops.stream()
                .map(shop -> new ShopReqDto(shop.getId(), shop.getName(), shop.getAddr())) // Shop의 필드를 ShopReqDto에 전달
                .collect(Collectors.toList());
    }
}
