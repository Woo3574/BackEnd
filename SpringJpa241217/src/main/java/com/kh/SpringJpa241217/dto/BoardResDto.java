package com.kh.SpringJpa241217.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardResDto {
    private Long boardId;
    private String title;
    private String content;
    private String imgPath;
    private LocalDateTime regDate;
    private String email;

    // 댓글 목록 추가
    private List<CommentResDto> comments;
}
