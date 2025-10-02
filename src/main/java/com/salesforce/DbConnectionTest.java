package com.salesforce; // 2단계에서 입력한 Group Id에 맞게 수정하세요.

import java.io.FileInputStream;
import java.io.IOException;
// 0. import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionTest {

    public static void main(String[] args) {
        // DB 연결 정보를 담는 변수
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

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

            // 3. SQL문 준비 / 바인딩 / 실행
            String sql = "SELECT author_id, author_name, author_desc FROM author ORDER BY author_id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // SELECT 문 실행 [cite: 119]

            // 4. 결과처리
            System.out.println("=============================================");
            while (rs.next()) { // 다음 데이터가 없을 때까지 반복
                int authorId = rs.getInt("author_id");
                String authorName = rs.getString("author_name");
                String authorDesc = rs.getString("author_desc");
                System.out.println("author_id : " + authorId + "\t" + "author_name : " + authorName + "\t" + "author_desc : " + authorDesc);
            }

        } catch (IOException e) {
            System.out.println("설정 파일을 읽는 중 오류 발생: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            // 5. 자원정리
            try {
                if (rs != null) {
                    rs.close();
                }
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