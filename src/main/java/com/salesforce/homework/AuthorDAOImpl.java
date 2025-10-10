package com.salesforce.homework;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AuthorDAOImpl implements AuthorDAO {

  private Connection getConnection() throws SQLException {
    Connection conn = null;
    try {
      // 1. 설정 파일에서 DB 정보 읽어오기
      Properties dbProps = new Properties();
      // "db.properties" 파일의 실제 경로를 넣어주세요.
      dbProps.load(AuthorDAOImpl.class.getClassLoader().getResourceAsStream("db.properties"));

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
  public int insert(AuthorVO vo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;

    try {

      conn = getConnection();

      // 3. SQL문 준비 / 바인딩 / 실행
      // int count = pstmt.executeUpdate(); //insert, update, delete
      String query = "INSERT INTO author VALUES (seq_author_id.nextval, ?, ? )";
      pstmt = conn.prepareStatement(query);

      pstmt.setString(1, vo.getAuthor_name());
      pstmt.setString(2, vo.getAuthor_desc());

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
  public List<AuthorVO> getList() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<AuthorVO> list = new ArrayList<>();

    try {

      conn = getConnection();

      // 3. SQL문 준비 / 바인딩 / 실행
      // int count = pstmt.executeUpdate(); //insert, update, delete
      String query = "select * from author ORDER BY author_id ASC";
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery(); // select

      // 4.결과처리
      while (rs.next()) {
        int author_id = rs.getInt("author_id");
        String author_name = rs.getString("author_name");
        String author_desc = rs.getString("author_desc");

        AuthorVO vo = new AuthorVO(author_id, author_name, author_desc);
        list.add(vo);
        // System.out.println(author_id + "\t" + author_name + "\t" + author_desc );
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
  public int update(AuthorVO vo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;

    try {
      conn = getConnection();

      // author_id를 기반으로 author_name과 author_desc 수정
      String query = "UPDATE author"
                   + "SET author_name = ?, author_desc = ?"
                   + "WHERE author_id = ?"
                   + "ORDER BY author_id ASC";
      pstmt = conn.prepareStatement(query);

      pstmt.setString(1, vo.getAuthor_name());
      pstmt.setString(2, vo.getAuthor_desc());
      pstmt.setInt(3, vo.getAuthor_id());

      count = pstmt.executeUpdate(); // UPDATE 실행

      System.out.println(count + "건의 저자 정보가 수정되었습니다.");

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
  public int delete(Long authorId) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;

    try {
      conn = getConnection();

      // author_id를 기반으로 데이터를 삭제
      String query = "DELETE FROM author WHERE author_id = ?";
      pstmt = conn.prepareStatement(query);

      pstmt.setLong(1, authorId);

      count = pstmt.executeUpdate(); // DELETE 실행

      System.out.println(count + "건의 저자 정보가 삭제되었습니다.");

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
  public List<AuthorVO> getList(String keyword) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<AuthorVO> list = new ArrayList<>();

    try {

      conn = getConnection();

      // 3. SQL문 준비 / 바인딩 / 실행
      // int count = pstmt.executeUpdate(); //insert, update, delete
      String query = "SELECT * FROM author WHERE author_name LIKE ? OR author_desc LIKE ? ORDER BY author_id ASC";
      pstmt = conn.prepareStatement(query);
      String likeKeyword = "%" + keyword + "%";
      pstmt.setString(1, likeKeyword);
      pstmt.setString(2, likeKeyword);
      rs = pstmt.executeQuery(); // select

      // 4.결과처리
      while (rs.next()) {
        int author_id = rs.getInt("author_id");
        String author_name = rs.getString("author_name");
        String author_desc = rs.getString("author_desc");
        AuthorVO vo = new AuthorVO(author_id, author_name, author_desc);
        list.add(vo);
        // System.out.println(author_id + "\t" + author_name + "\t" + author_desc );
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
