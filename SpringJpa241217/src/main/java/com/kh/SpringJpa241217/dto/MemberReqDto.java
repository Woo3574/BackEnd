package com.kh.SpringJpa241217.dto;

// DTO : 다른 레이어간의 데이터를 교환 할 때 사용, 주로 FontEnd 와 BackEnd 사이에 데이터를 주고 받는 용도
// 회원 가입용

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberReqDto {
    private String email;
    private String pwd;
    private String name;
}
