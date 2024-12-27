package com.kh.SpringJpa241217.entity;


import com.kh.SpringJpa241217.constant.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor // 매개자가 없는 생성자를 만들어줌
@Entity // 해당 클래스가 Entity 임을 나타냄
@Table(name="member") // 테이블 이름을 지정, 테이블 이름은 소문자, 카멜 표기법은 -> snake 표기법으로 변경 됨.
// ToString 오버라이딩
// Jpa 는 설계 명세

public class Member {
    @Id // 해당 필드를 기본키로 지정
    @Column(name="member_id") // DB_Table 에서 열 이름
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 전략, Jpa 가 자동으로 생성 전략을 정함
    private Long id; // Primary Key

    @Column(nullable = false) // null 값이 올수 없다는 제약 조건
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String name;

    @Column (name ="reg_date" )
    private LocalDateTime regDate;

    @Column (name = "image_path")
    private String imgPath;

    //   @PrePersist //JPA, 엔티티가 저장되기 직전에 실행
   // protected void onCreate() { // JPA의 콜백 메서드로 엔티티가 저장되기 전에 실행; DB 데이터가 삽입되기 전에 자동 설정
    //    this.regDate = LocalDateTime.now(); // 등록 일자를 현재 시간으로 설정
   // }

    // 게시글 목록에 대한 OneToMany
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true) // 주인이 아님을 의미, 즉 객체를 참조만 함
    private List<Board> boards = new ArrayList<>();
    
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true) // 주인이 아님을 의미, 즉 객체를 참조만 함
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String name, String pwd, String img, Authority authority) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.imgPath = img;
        this.authority = authority;
        this.regDate = LocalDateTime.now();
    }

}
