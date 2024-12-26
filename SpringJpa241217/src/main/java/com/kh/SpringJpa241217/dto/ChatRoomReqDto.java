package com.kh.SpringJpa241217.dto;

import lombok.Getter;
import lombok.Setter;

// 채팅방 생성 요청
@Getter
@Setter
public class ChatRoomReqDto {
    private String email; // 개설자 이메일
    private String name; // 방 제목

}
