package com.kh.SpringJpa241217.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResDto {
    private Long boardId;
    private String title;
    private String content;
    private String imgPath;
    private LocalDateTime regDate;
    private String email;
}
