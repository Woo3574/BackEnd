package com.kh.SpringJpa241217.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardReqDto {
    private String title;
    private String content;
    private String imgPath;
    private String email;
}
