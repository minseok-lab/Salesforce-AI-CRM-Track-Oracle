package com.salesforce.homework.month10week2;

import java.util.List;

public interface AuthorDAO {
  public int insert(AuthorVO vo); // 저자정보 저장용

  public List<AuthorVO> getList(); // 저자목록 조회용

  public int delete(Long author_id); // 저자정보 삭제용

  public int update(AuthorVO vo); // 저자정보 수정용

}
