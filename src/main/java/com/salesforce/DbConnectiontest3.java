package com.salesforce;

import java.io.FileInputStream;
import java.io.IOException;
// 0. import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectiontest3 {

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
            String username = dbProps.getProperty("db.username2");
            String password = dbProps.getProperty("db.password2");
            
            // 2. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 3. Connection 얻어오기 (설정 파일에서 읽은 정보 사용)
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            String sql = "SELECT * FROM employees ORDER BY employee_id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // SELECT 문 실행 

            // 4. 결과처리
            System.out.println("=============================================");
            while (rs.next()) { // 다음 데이터가 없을 때까지 반복
                int employeeId = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String hireDate = rs.getString("hire_date");
                String jobId = rs.getString("job_id");
                int salary = rs.getInt("salary");
                int commissionPct = rs.getInt("commission_pct");
                int managerId = rs.getInt("manager_id");
                int departmentId = rs.getInt("department_id");
                
                System.out.println(
                    "employee_id : " + employeeId + "\t" +
                    "first_name : " + firstName + "\t" +
                    "last_name : " + lastName + "\t" +
                    "email : " + email + "\t" +
                    "phone_number : " + phone + "\t" +
                    "hire_date : " + hireDate + "\t" +
                    "job_id : " + jobId + "\t" +
                    "salary : " + salary + "\t" +
                    "commission_pct : " + commissionPct + "\t" +
                    "manager_id : " + managerId + "\t" +
                    "department_id : " + departmentId
                );
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