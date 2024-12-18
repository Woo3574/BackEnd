package com.kh.SpringJpa241217.controller;

import com.kh.SpringJpa241217.dto.MemberReqDto;
import com.kh.SpringJpa241217.dto.MemberResDto;
import com.kh.SpringJpa241217.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    // 회원 전체 조회
    @GetMapping("/memberlist")
    public ResponseEntity<List<MemberResDto>> allMembers() {
        List<MemberResDto> memberResDtoList = memberService.getMemberList();
        return ResponseEntity.ok(memberResDtoList);
    }

    // 회원 상세 조회
    @GetMapping("/{email}")
    public ResponseEntity<MemberResDto> memberDetail(@PathVariable String email) {
        MemberResDto memberResDto = memberService.getMemberDetail(email);
        return ResponseEntity.ok(memberResDto);
    }

    // 회원 수정
    @PutMapping("/modify")
    public ResponseEntity<Boolean> memberModify(@RequestBody MemberReqDto memberReqDto) {
        boolean isSuccess = memberService.modifyMember(memberReqDto);
        return ResponseEntity.ok(isSuccess);
    }

    // 회원 삭제
    @DeleteMapping("/{email}")
    public ResponseEntity<Boolean> memberDelete(@PathVariable String email) {
        boolean isSuccess = memberService.deleteMember(email);
        return ResponseEntity.ok(isSuccess);
    }
}
