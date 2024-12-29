package com.acPro.springBoot_JPA_Practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberReqDto {
    private String email;
    private String pwd;
    private String name;
    private String phone;
    private String addr;
}
