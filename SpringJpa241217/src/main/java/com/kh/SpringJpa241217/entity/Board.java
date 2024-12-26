package com.kh.SpringJpa241217.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "board") // DB Table 이름
@NoArgsConstructor // 기본 생성자를 생성해주는 어노테이션
@Entity // 해당 클래스를 JPA Entity 선언

public class Board {
    @Id // 기본 키 (Primary Key)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title; // 글 제목

    @Lob // DB에 큰 데이터를 저장할때 사용 Large Object, (긴 텍스트)
    @Column(length = 1000)
    private String content; // 글 내용

    private String imgPath; // 게시글 이미지 경로
    private LocalDateTime regDate; // 게시글 등록 일자
    @PrePersist
    public void prePersist() {
        regDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 회원에 대한 참조변수, 가입되어있던 회원 (게시판 글작성하는 사람에 대한 정보)

    // 영속성 전이 : 부모 엔티티의 상태 변화가 자식 엔티티에도 전이 되는 것
    // orphanRemoval : 고아 객체 제거 : 부모와의 연관 관계가 끊어진 자식 엔티티를 자동으로 데이터베이스에 제거하는 것
    // 부모가 관리하는 List 에서 해당 객체를 삭제하는 경우, 실제 DB 에서 삭제 됨
    // 부모 보드, 자식 코맨트
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true) // 주인이 아님을 의미, 즉 객체를 참조만 함
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setBoard(null);
    }
    
}
