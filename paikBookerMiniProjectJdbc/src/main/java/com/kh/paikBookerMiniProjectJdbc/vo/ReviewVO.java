package com.kh.paikBookerMiniProjectJdbc.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor // 매개변수가 전부 다 있는 생성자
@NoArgsConstructor // 매개변수가 없는 생성자 (기본 생성자)
public class ReviewVO {
    private int rvNo;             // 리뷰 번호
    private BigDecimal rvAverage; // 리뷰 평점
}
