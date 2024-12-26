package com.kh.SpringJpa241217.controller;

import com.kh.SpringJpa241217.dto.LoginReqDto;
import com.kh.SpringJpa241217.dto.MemberReqDto;

import com.kh.SpringJpa241217.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 1.POSTMAN으로 회원 존재여부 확인, 회원가입, 로그인
// 2.회원 전체 조회 및 회원 이메일 조회 만들기 (POSTMAN)
// 3.SWAGGER 등록 후 회원 존재여부 확인, 회원가입, 로그인, 회원 전체조회, 개별 회원 조회, 회원 삭제
// 4.MemberController : 회원 전체 조회, 개별 회원 조회, 회원정보 수정, 회원 삭제

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
        return ResponseEntity.ok(!isTrue);
    }

    // 회원 가입
    @PostMapping("/signup")
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


