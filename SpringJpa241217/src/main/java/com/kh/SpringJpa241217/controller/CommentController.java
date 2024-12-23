package com.kh.SpringJpa241217.controller;

import com.kh.SpringJpa241217.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/new")
    public  ResponseEntity<Boolean> registerComment() {

    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id,
                                                 @RequestParam String email) {
        boolean isSuccess = commentService.commentDelete(id, email);
        return ResponseEntity.ok(isSuccess);
    }

    // 댓글 목록 조회 (게시글 ID)

}
