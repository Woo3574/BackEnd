package com.kh.SpringJpa241217.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor // 매개자가 없는 생성자를 만들어줌
@Entity // 해당 클래스가 Entity 임을 나타냄
@Table(name="member") // 테이블 이름을 지정, 테이블 이름은 소문자, 카멜 표기법은 -> snake 표기법으로 변경 됨.
// ToString 오버라이딩
// JPA는 설계 명세

public class Member {
    @Id // 해당 필드를 기본키로 지정
    @Column(name="member_id") // DB_Table에서 열 이름
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 전략, JPA가 자동으로 생성 전략을 정함
    private Long id; // Primary Key

    @Column(nullable = false, length = 50) // null 값이 올수 없다는 제약 조건
    private String email;

    @Column(nullable = false, length = 50)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String name;

    @Column (name ="reg_date" )
    private LocalDateTime regDate;

    @Column (name = "image_path")
    private String imgPath;

    @PrePersist //JPA
    protected void onCreate() { // JPA의 콜백 메서드로 엔티티가 저장되기 전에 실행; DB 데이터가 삽입되기 전에 자동 설정
        this.regDate = LocalDateTime.now();
    }
}
