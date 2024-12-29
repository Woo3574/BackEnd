package com.acPro.springBoot_JPA_Practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본생성자 생성
@AllArgsConstructor
public class ShopReqDto {
    private Long shopId;
    private String name;
    private String addr;

}
