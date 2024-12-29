package com.acPro.springBoot_JPA.controller;

import com.acPro.springBoot_JPA.dto.ShopReqDto;
import com.acPro.springBoot_JPA.dto.ShopResDto;
import com.acPro.springBoot_JPA.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/shop")
@RequiredArgsConstructor

public class shopController {
    private final ShopService shopService;

    @PostMapping("/add")
    public ShopResDto addShop(@RequestBody ShopResDto shopResDto) {
        return shopService.addShop(shopResDto);
    }

    @GetMapping("/search")
    public List<ShopReqDto> searchShops(@RequestParam String keyword) {
        return shopService.searchShops(keyword);
    }

}
