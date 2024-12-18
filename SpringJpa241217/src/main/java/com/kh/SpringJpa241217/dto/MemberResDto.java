package com.kh.SpringJpa241217.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberResDto {
    private String email;
    private String name;
    private String imagePath;
    private LocalDateTime regDate;
}
