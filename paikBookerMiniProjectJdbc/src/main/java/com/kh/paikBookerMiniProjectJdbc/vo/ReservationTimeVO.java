package com.kh.paikBookerMiniProjectJdbc.vo;

import lombok.*;

@Data
@AllArgsConstructor // 매개변수가 전부 다 있는 생성자
@NoArgsConstructor // 매개변수가 없는 생성자 (기본 생성자)

public class ReservationTimeVO {
    private int rNo;               // 예약번호
    private String rTime;          // 예약 시간
    private int rPersonCnt;        // 예약 인원수
    private String rSubmitTime;    // 예약 버튼 누른 시간
}
