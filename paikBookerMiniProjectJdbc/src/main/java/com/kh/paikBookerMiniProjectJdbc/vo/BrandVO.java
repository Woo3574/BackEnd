package com.kh.paikBookerMiniProjectJdbc.vo;

import lombok.*;

@Data
@AllArgsConstructor // 매개변수가 전부 다 있는 생성자
@NoArgsConstructor // 매개변수가 없는 생성자 (기본 생성자)

public class BrandVO {
    private int brandNo;        // 브랜드번호
    private String brandName;   // 브랜드명
    private String brandOpen;   // 영업시작시간
    private String brandClose;  // 영업종료시간
    private String brandFood;   // 음식종류
    private String brandLogo1;  // 브랜드 LOGO 세로 이미지 URL
    private String brandLogo2;  // 브랜드 LOGO 가로 이미지 URL
    private String brandMaker;  // 브랜드 MARKER 세로 이미지 URL
    private String brandImg;    // 브랜드 지점 이미지 URL
}
