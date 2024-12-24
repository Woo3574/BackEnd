package com.kh.SpringJpa241217.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CommentReqDto { // 댓글 쓰기
    private String email;
    private Long boardId;
    private String content;

}
