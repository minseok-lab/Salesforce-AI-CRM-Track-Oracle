package com.salesforce;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class AuthorInsertTest {
    public static void main(String[] args) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. 설정 파일에서 DB 정보 읽어오기
            Properties dbProps = new Properties();
            // "db.properties" 파일의 실제 경로를 넣어주세요.
            dbProps.load(new FileInputStream("src/db.properties")); 

            String url = dbProps.getProperty("db.url");
            String username = dbProps.getProperty("db.username1");
            String password = dbProps.getProperty("db.password1");
            
            // 2. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 3. Connection 얻어오기 (설정 파일에서 읽은 정보 사용)
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("접속성공");

            // 4. SQL문 준비 / 바인딩 / 실행
            String query = "INSERT INTO author VALUES (seq_author_id.nextval, ?, ? )";
            pstmt = conn.prepareStatement(query);
            
            // 바인딩
            pstmt.setString(1, "홍길동1");
            pstmt.setString(2, "홍길동전1");

            int count = pstmt.executeUpdate(); // insert update delete

            // 5.결과처리
            System.out.println(count + "건 처리");

        } catch (IOException e) {
            System.out.println("설정 파일을 읽는 중 오류 발생: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
        // 5. 자원정리
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        }

    }

}