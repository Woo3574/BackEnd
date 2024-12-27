package com.kh.SpringJpa241217.dto;

// DTO : 다른 레이어간의 데이터를 교환 할 때 사용, 주로 FontEnd 와 BackEnd 사이에 데이터를 주고 받는 용도
// 회원 가입용

import com.kh.SpringJpa241217.constant.Authority;
import com.kh.SpringJpa241217.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberReqDto {
    private String email;
    private String pwd;
    private String name;
    private String imagePath;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .pwd(passwordEncoder.encode(pwd))
                .name(name)
                .img(imagePath)
                .authority(Authority.ROLE_USER)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, pwd);
    }
}
