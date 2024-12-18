package DB_TestSelf.DAO;

import DB_TestSelf.Common.Common;
import DB_TestSelf.VO.FamilyVO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FamilyDAO {
    private Connection conn = null; // conn 은 DB와 연결을 나타내는 Connection 객체
    private Statement stmt = null; // stmt 는 sql 쿼리문을 실행하는데 사용되는 Statement 객체
    private ResultSet rs = null; // rs는 SQL 쿼리 실행결과를 담는 Result 객체, 쿼리 결과를 통해 여러 행을 탐색할수있게 함
    //private Scanner sc = null;

    public FamilyDAO() {
        //sc = new Scanner(System.in);
    }

    public List<FamilyVO> fSelect() {
        List<FamilyVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 변수 conn에 DB 연결 결과값을 받음
            stmt = conn.createStatement(); //
            String sql = "SELECT * FROM FAMILY";
            rs = stmt.executeQuery(sql); // ResultSet : 여러행의 결과 값을 받아서 반복자 제공
                while (rs.next()) {
                    String familyPos = rs.getString("FAMILY_Pos");
                    String fName = rs.getString("F_NAME");
                    int fAge = rs.getInt("F_AGE");
                    int fHeight = rs.getInt("F_HEIGHT");
                    FamilyVO vo = new FamilyVO(familyPos, fName, fAge, fHeight);
                    list.add(vo);
                }

        } catch (Exception e) {
            System.out.println("조회 실패");
        }
        return list;
    }
}
