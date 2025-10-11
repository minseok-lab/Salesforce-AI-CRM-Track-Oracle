package com.salesforce.homework.month10week2;

import java.util.List;

public interface BookDAO {

    // 책 정보 저장용
    public int insert(BookVO vo);

    // 책 목록 조회용
    public List<BookVO> getList();

    // 책 정보 삭제용
    public int delete(Long book_id);

    // 책 정보 수정용
    public int update(BookVO vo);

    // 책 정보 검색용
    public List<BookVO> getList(String keyword);

}
