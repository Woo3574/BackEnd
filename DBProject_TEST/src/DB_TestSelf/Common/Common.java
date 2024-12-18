package DB_TestSelf.Common;
// 1.연결할 DB 데이터 클래스 변수 설정 (URL,ID,PASSWORD,DRIVER)
// 2.DB 연결
// 3.DB 연결해제
// 4.DB

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Class.forName;

public class Common {
    final static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String user_Id = "TEST";
    final static String user_Password = "test";
    final static String user_Driver = "oracle.jdbc.driver.OracleDriver";


    // DB 연결 조건
    public static Connection getConnection() {
        Connection conn = null;
        try {
            class.forName(user_Driver);
            conn = DriverManager.getConnection(url, user_Id, user_Password);
            System.out.print("오라클 DB 연결 완료");
        } catch (Exception e) {
            System.out.println(e + "오라클 DB 연결 실패, 에러 발생");
        }
        return conn;
    }
    // DB 연결 해제 조건
    public static void Close(Connection conn) {
        try {
            if (conn != null & !conn.isClosed()) {
                conn.close();
                System.out.println("Connection 해제 성공");
            }
        } catch (Exception e) {
            System.out.println("Connection 해제 실패");
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();  //  예외 정보 출력 : 발생한 예외의 스택 추적 정보를 콘솔에 출력하여,오류의 원인 분석
        }
    }
    public static void close(ResultSet rst) {
        try {
            if( rst != null && !rst.isClosed()) {
                rst.close();
                System.out.println("Result set 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}