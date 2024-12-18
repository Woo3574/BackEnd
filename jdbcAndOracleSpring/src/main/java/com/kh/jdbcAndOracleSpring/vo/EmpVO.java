package com.kh.jdbcAndOracleSpring.vo;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpVO {
    private int empNO;
    private String name;
    private String job;
    private int mgr;
    private Date date;
    private BigDecimal sal;
    private BigDecimal comm;
    private int deptNO;
}
