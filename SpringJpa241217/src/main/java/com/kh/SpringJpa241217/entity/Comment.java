package com.kh.SpringJpa241217.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString(exclude = {"board", "member"}) // 순환 참조 방지
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId; // comment_id

    @ManyToOne
    @JoinColumn(name = "board_id") // 참조키는 해당 객체의 기본키여야 함
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 1000)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime regDate;

    @PrePersist // Jpa 콜백, DB에 쓰기직전에 불러서 DB에 써준다,(아래 작성한 것 처럼)시간을 자동으로 입력
    public void prePersist() {
        regDate = LocalDateTime.now();
    }
}
