package com.kh.paikBookerMiniProjectJdbc.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StoreVO {
    private int storeNo;       // 매장번호
    private String storeName;  // 지점명
    private String storePhone; //   매장 연락처
    private String storeAddr;  // 매장 주소
    private String storeMap;   // 매장 위치(위도, 경도)

    private BrandVO brandVO;   // BrandVO 객체를 추가
    private ReviewVO reviewVO; // ReviewVO 객체를 추가
    private ReservationTimeVO reservationTimeVO; // ReservationTimeVO 객체를 추가
    private AvgRatingVO avgRatingVO; // AvgRatingVO 객체를 추가
}
