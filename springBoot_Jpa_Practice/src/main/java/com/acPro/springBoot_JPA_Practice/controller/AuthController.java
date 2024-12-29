package com.acPro.springBoot_JPA_Practice.controller;

import com.acPro.springBoot_JPA_Practice.dto.MemberReqDto;
import com.acPro.springBoot_JPA_Practice.dto.MemberResDto;
import com.acPro.springBoot_JPA_Practice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<MemberResDto> signupMember(@RequestBody MemberReqDto memberReqDto) {
        return ResponseEntity.ok(authService.addMember(memberReqDto));
    }
}
