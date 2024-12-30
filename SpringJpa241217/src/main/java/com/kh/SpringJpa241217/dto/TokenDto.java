package com.kh.SpringJpa241217.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String grantType; // 인증 방식
    private String accessToken; // 액세스 토큰
    private Long accessTokenExpiresIn; // 액세스 토큰 만료 시간
    private String refreshToken; // 리프레쉬 토큰
    private Long refreshTokenExpiresIn; // 리프레쉬 토큰 만료 시간
}
