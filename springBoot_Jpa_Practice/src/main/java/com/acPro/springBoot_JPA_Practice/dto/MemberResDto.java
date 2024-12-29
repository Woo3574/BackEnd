package com.acPro.springBoot_JPA_Practice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResDto {
    private String email;
    private String name;
    private String phone;
    private String addr;
    private LocalDateTime regDate;
}
