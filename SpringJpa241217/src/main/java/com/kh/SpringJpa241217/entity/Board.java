package com.kh.SpringJpa241217.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "board")
@NoArgsConstructor
@Entity

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title; // 글 제목

    @Lob
    @Column(length = 1000)
    private String content; // 글 내용

    private String imgPAth; // 게시글 이미지 경로
    private LocalDateTime regDate; // 게시글 등록 일자
    @PrePersist
    public void prePersist() {
        regDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 회원에 대한 참조변수, 가입되어있던 회원 (게시판 글작성하는 사람에 대한 정보)

    @OneToMany(mappedBy = "board") // 주인이 아님을 의미, 즉 객체를 참조만 함
    private List<Comment> comments = new ArrayList<>();
}
