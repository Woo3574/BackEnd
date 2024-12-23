package com.kh.SpringJpa241217.dto;

import com.kh.SpringJpa241217.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberResDto {
    private String email;
    private String name;
    private String imagePath;
    private LocalDateTime regDate;

    private List<BoardResDto> boards = new ArrayList<>();
}
