package com.kh.SpringJpa241217.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// 글쓰기 화면에서 내용을채우고 전송을 누르면
// 제목,내용,사진 useState - 사진은 파이어베이스 올리고 그 반환값은 사진경로를  useState에 넣는다
// 전송을 누르는 순간 액시오스에서 이정보들을 채워야함
public class BoardReqDto {
    private String title;
    private String content;
    private String imgPath;
    private String email;
}
