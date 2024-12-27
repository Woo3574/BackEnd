package com.kh.SpringJpa241217.service;

import com.kh.SpringJpa241217.dto.LoginReqDto;
import com.kh.SpringJpa241217.dto.MemberReqDto;
import com.kh.SpringJpa241217.dto.MemberResDto;
import com.kh.SpringJpa241217.dto.TokenDto;
import com.kh.SpringJpa241217.entity.Member;
import com.kh.SpringJpa241217.jwt.TokenProvider;
import com.kh.SpringJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j // log 정보를 출력하기 위함
@Service // 스프링 컨테이너에 빈(객체) 등록
@RequiredArgsConstructor // 생성자 생성
@Transactional // 여러개의 작업을 하나의 논리적인 단위로 묶는 것

public class AuthService {
    // 생성자를 통한 의존성 주입, 생성자를 통해서 의존성 주입을 받는 경우 Autowired 생략
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder managerBuilder; // 인증을 담당하는 클래스
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 회원 가입 여부
    public boolean isMember(String email) {
        return memberRepository.existsByEmail(email);
    }

    // 회원 가입
    public MemberResDto singUp(MemberReqDto memberReqDto) {
        if (memberRepository.existsByEmail(memberReqDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Member member = memberReqDto.toEntity(passwordEncoder);
        return MemberResDto.of(memberRepository.save(member));
    }

    // 로그인
    public TokenDto login(MemberReqDto memberReqDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberReqDto.toAuthentication();
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.generateTokenDto(authentication);
    }

    // 회원 가입 DTO -> Entity
    private Member coverDtoToEntity(MemberReqDto memberReqDto) {
        Member member= new Member();
        member.setEmail(memberReqDto.getEmail());
        member.setName(memberReqDto.getName());
        member.setPwd(memberReqDto.getPwd());
        return member;
    }

}
