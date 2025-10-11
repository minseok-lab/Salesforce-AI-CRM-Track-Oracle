package com.salesforce.homework.month10week2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDAOImpl implements BookDAO {
    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
        // 1. 설정 파일에서 DB 정보 읽어오기
        Properties dbProps = new Properties();
        // "db.properties" 파일의 실제 경로를 넣어주세요.
        dbProps.load(BookDAOImpl.class.getClassLoader().getResourceAsStream("db.properties"));

        String url = dbProps.getProperty("db.url");
        String username = dbProps.getProperty("db.username1");
        String password = dbProps.getProperty("db.password1");

        // 2. JDBC 드라이버 로딩
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 3. Connection 얻어오기 (설정 파일에서 읽은 정보 사용)
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("접속성공");

        } catch (IOException e) {
        System.out.println("설정 파일을 읽는 중 오류 발생: " + e.getMessage());
        } catch (ClassNotFoundException e) {
        System.err.println("JDBC 드라이버 로드 실패!");
        }
        return conn;
    }

    @Override
    public int insert(BookVO vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {

        conn = getConnection();

        // 3. SQL문 준비 / 바인딩 / 실행
        // int count = pstmt.executeUpdate(); //insert, update, delete
        String query = "INSERT INTO book VALUES (seq_book_id.nextval, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(query);

        pstmt.setString(1, vo.getTitle());
        pstmt.setString(2, vo.getPubs());
        pstmt.setString(3, vo.getPub_date());
        pstmt.setInt(4, vo.getAuthor_id());

        count = pstmt.executeUpdate(); // insert

        // 4.결과처리
        if (count == 0) {
            System.out.println(" DB 에러발생 : 데이터 입력 건수 =>" + count);
        } else {
            System.out.println(" DB에 데이터 입력된 건수 : " + count);
        }

        } catch (SQLException e) {
        System.out.println("error:" + e);
        } finally {

        // 5. 자원정리
        try {
            if (pstmt != null)
            pstmt.close();
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        }
        return count;
    }

    @Override
public List<BookVO> getList() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<BookVO> list = new ArrayList<>();

    try {
        conn = getConnection();
        String query = "SELECT b.book_id, " +
                       "       b.title, " +
                       "       b.pubs, " +
                       "       TO_CHAR(b.pub_date, 'YYYY-MM-DD') AS pub_date_str, " +
                       "       a.author_id, " +
                       "       a.author_name " +
                       "FROM book b " +
                       "JOIN author a ON b.author_id = a.author_id " +
                       "ORDER BY b.book_id ASC";

        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();

        // 2. 결과 처리 수정
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String pubs = rs.getString("pubs");
            String pub_date = rs.getString("pub_date_str");
            int author_id = rs.getInt("author_id");
            String author_name = rs.getString("author_name");

            // BookVO 객체 생성 및 값 설정
            BookVO vo = new BookVO();
            vo.setBook_id(book_id);
            vo.setTitle(title);
            vo.setPubs(pubs);
            vo.setPub_date(pub_date);
            vo.setAuthor_id(author_id);
            vo.setAuthor_name(author_name);
            
            list.add(vo);
        }
        } catch (SQLException e) {
        System.out.println("error:" + e);
        } finally {

        // 5. 자원정리
        try {
            if (rs != null)
            rs.close();
            if (pstmt != null)
            pstmt.close();
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        }
        return list;
    }

    @Override
    public int update(BookVO vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
        conn = getConnection();

        // book_id를 기반으로 책 정보 수정
        String query = "UPDATE book SET title = ?, pubs = ?, pub_date = ?, author_id = ? WHERE book_id = ?";
        pstmt = conn.prepareStatement(query);

        pstmt.setString(1, vo.getTitle());
        pstmt.setString(2, vo.getPubs());
        pstmt.setString(3, vo.getPub_date());
        pstmt.setInt(4, vo.getAuthor_id());
        pstmt.setInt(5, vo.getBook_id());

        count = pstmt.executeUpdate(); // UPDATE 실행

        if (count > 0) {
            System.out.println(count + "건의 책 정보가 수정되었습니다.");
        }

        } catch (SQLException e) {
        System.out.println("error:" + e);
        } finally {
        try {
            if (pstmt != null)
            pstmt.close();
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        }
        return count;
    }

    @Override
    public int delete(Long bookId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
        conn = getConnection();

        // book_id를 기반으로 데이터를 삭제
        String query = "DELETE FROM book WHERE book_id = ?";
        pstmt = conn.prepareStatement(query);

        pstmt.setLong(1, bookId);

        count = pstmt.executeUpdate(); // DELETE 실행

        if (count > 0) {
            System.out.println(count + "건의 책 정보가 삭제되었습니다.");
        }

        } catch (SQLException e) {
        System.out.println("error:" + e);
        } finally {
        try {
            if (pstmt != null)
            pstmt.close();
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        }
        return count;
    }

    @Override
    public List<BookVO> getList(String keyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookVO> list = new ArrayList<>();

        try {

        conn = getConnection();

        // 3. SQL문 준비 / 바인딩 / 실행
        // int count = pstmt.executeUpdate(); //insert, update, delete
        String query = "SELECT b.book_id, b.title, b.pubs, to_char(b.pub_date, 'YYYY-MM-DD') AS pub_date_str, a.author_name "
            + "FROM book b "
            + "JOIN author a "
            + "ON b.AUTHOR_ID = a.AUTHOR_ID "
            + "WHERE a.author_name LIKE ? "
            + "OR b.title LIKE ? "
            + "OR b.pubs LIKE ? "
            + "ORDER BY b.book_id ASC";

        pstmt = conn.prepareStatement(query);
        String likeKeyword = "%" + keyword + "%";
        pstmt.setString(1, likeKeyword);
        pstmt.setString(2, likeKeyword);
        pstmt.setString(3, likeKeyword);
        rs = pstmt.executeQuery(); // select

        // 4.결과처리
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String pubs = rs.getString("pubs");
            String pub_date = rs.getString("pub_date_str");
            String author_name = rs.getString("author_name"); // 새로 추가된 필드

            BookVO vo = new BookVO(book_id, title, pubs, pub_date, author_name);
            list.add(vo);
        }
        } catch (SQLException e) {
        System.out.println("error:" + e);
        } finally { 

        // 5. 자원정리
        try {
            if (rs != null)
            rs.close();
            if (pstmt != null)
            pstmt.close();
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        }
        return list;
    }
}
