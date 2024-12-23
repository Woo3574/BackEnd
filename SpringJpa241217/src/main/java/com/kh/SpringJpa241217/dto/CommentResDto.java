package com.kh.SpringJpa241217.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class CommentResDto {
    private String email;
    private Long boardId;
    private Long commentId;
    private String content;
    private LocalDateTime regDate;
}
