package com.acPro.springBoot_JPA_Practice.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "member")
@NoArgsConstructor

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email", unique = true, nullable = false)
    private String email;

    @Column(name = "member_pwd", nullable = false)
    private String pwd;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_phone", unique = true)
    private String phone;

    @Column(name = "member_addr")
    private String addr;

    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
