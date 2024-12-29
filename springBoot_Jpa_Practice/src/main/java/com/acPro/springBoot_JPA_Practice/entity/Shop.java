package com.acPro.springBoot_JPA_Practice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // (Getter, Setter, ToString) 포함
@Table(name = "shop") // DB Table 이름
@Entity // (해당 클래스를 JPA Entity로 선언)
@NoArgsConstructor // (기본 생성자 생성)

public class Shop {
    @Id // DB Table 기본키를 가리킴(Primary Key), 고유 식별자
    // strategy 속성을 통해 JPA(가) 기본 키 값을 자동으로 생성하도록 설정합니다.
    // IDENTITY: 데이터베이스의 Auto Increment(를) 사용하여 기본 키 값을 생성.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name", nullable = false)
    private String name;

    @Column(name = "shop_addr", nullable = false)
    private String addr;
}
