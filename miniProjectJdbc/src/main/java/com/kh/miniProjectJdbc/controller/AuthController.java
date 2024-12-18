package com.kh.miniProjectJdbc.controller;

import com.kh.miniProjectJdbc.dao.MemberDao;
import com.kh.miniProjectJdbc.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberDao memberDao;
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody MemberVo vo) {
        log.info("member : {} ", vo);
        // log.error("이메일 : {}", vo.getEmail() );
        // log.error("패스워드 : {}", vo.getPassword() );
        boolean isSuccess = memberDao.login(vo.getEmail(), vo.getPassword());
        return ResponseEntity.ok(isSuccess);
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody MemberVo vo) {
        log.info("member : {} ", vo);
        // log.error("member : {}", vo);
        boolean isSuccess = memberDao.signup(vo);
        return ResponseEntity.ok(isSuccess);
    }

    // 가입 여부확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> exists(@PathVariable String email) {
        log.error("email : {}", email);
        boolean isExists = memberDao.isEmailExist(email);
        return ResponseEntity.ok(!isExists);
    }

}
