package com.kh.SpringJpa241217.controller;

import com.kh.SpringJpa241217.dto.LoginReqDto;
import com.kh.SpringJpa241217.dto.MemberReqDto;
import com.kh.SpringJpa241217.entity.Member;
import com.kh.SpringJpa241217.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService; // 의존성 주입

    // 회원 가입 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> isMember(@PathVariable String email) {
        boolean isTrue = authService.isMember(email);
        return ResponseEntity.ok(isTrue);
    }

//    // 회원 전체 조회
//    @GetMapping("/allSearch")
//    public ResponseEntity<List<Member>> allSearch() {
//        List<Member> members = authService.allSearch();
//        return ResponseEntity.ok(members);
//    }
//
//    // 해당 회원 정보 조회
//    @GetMapping("/emailInfoSearch")
//    public ResponseEntity<List<Member>> emailInfo(@PathVariable String email) {
//        List<Member> member = authService.emailInfoSearch(email);
//        return ResponseEntity.ok(member);
//    }
    // 회원 가입
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signup(@RequestBody MemberReqDto memberReqDto) {
        boolean isSuccess = authService.singUp(memberReqDto);
        return ResponseEntity.ok(isSuccess);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginReqDto loginReqDto) {
        boolean isSuccess = authService.login(loginReqDto);
        return ResponseEntity.ok(isSuccess);
    }

}


