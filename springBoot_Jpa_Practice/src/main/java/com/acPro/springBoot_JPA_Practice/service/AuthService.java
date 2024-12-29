package com.acPro.springBoot_JPA_Practice.service;

import com.acPro.springBoot_JPA_Practice.dto.MemberReqDto;
import com.acPro.springBoot_JPA_Practice.dto.MemberResDto;
import com.acPro.springBoot_JPA_Practice.entity.Member;
import com.acPro.springBoot_JPA_Practice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional

public class AuthService {
    private final MemberRepository memberRepository;

    // 회원가입
    public MemberResDto addMember (MemberReqDto memberReqDto) {
        if (memberRepository.existsByEmail(memberReqDto.getEmail())) {
            throw new RuntimeException("이미 가입되어있는 회원 입니다.");
        }
            Member member = new Member();
            member.setEmail(memberReqDto.getEmail());
            member.setPwd(memberReqDto.getPwd());
            member.setAddr(memberReqDto.getAddr());
            member.setName(memberReqDto.getName());
            member.setPhone(memberReqDto.getPhone());
            member.setRegDate(LocalDateTime.now());
            memberRepository.save(member);

            return new MemberResDto(member.getEmail(), member.getAddr(), member.getPhone(), member.getName(), member.getRegDate());
    }

}
