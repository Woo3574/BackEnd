package com.kh.SpringJpa241217.service;


import com.kh.SpringJpa241217.dto.MemberReqDto;
import com.kh.SpringJpa241217.dto.MemberResDto;
import com.kh.SpringJpa241217.entity.Member;
import com.kh.SpringJpa241217.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor // 생성자를 통한 의존성 주입을 받기 위해서
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 전체 조회
    public List<MemberResDto> getMemberList() {
        // DB로 부터 모든 회원의 Member Entity 객체로 읽어 옴
        List<Member> members = memberRepository.findAll(); // 설계 명사, 하이버네틱이 만들어줌
        // FrontEnd에 정보를 전달하기 위해 DTO List를 생성
        List<MemberResDto> memberResDtoList = new ArrayList<>();
        for (Member member : members) { // Member entity로 구성된 리스트를 순회
            memberResDtoList.add(convertEntityToDto(member));
        }
        return memberResDtoList;
    }

    // 회원 상세 조회
    public MemberResDto getMemberDetail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("해당 회원이 존재 하지 않습니다."));
        return convertEntityToDto(member);
    }

    // 회원 정보 수정
    public boolean modifyMember(MemberReqDto memberReqDto) {
        try {
            Member member = memberRepository.findByEmail(memberReqDto.getEmail())
                    .orElseThrow(()->new RuntimeException("해당 회원이 존재하지 않습니다"));
              member.setName(memberReqDto.getName());
              member.setImgPath(memberReqDto.getImagePath());
              memberRepository.save(member);
              return true;
        } catch (Exception e) {
            log.error(" 회원 정보 수정 : {}", e.getMessage());
            return false;
        }
    }

    // Member Entity => MemberResDto
    private MemberResDto convertEntityToDto(Member member) {
        MemberResDto memberResDto = new MemberResDto();
        memberResDto.setEmail(member.getEmail());
        memberResDto.setName(member.getName());
        memberResDto.setRegDate(member.getRegDate());
        memberResDto.setImagePath(member.getImgPath());
        return memberResDto;
    }
}