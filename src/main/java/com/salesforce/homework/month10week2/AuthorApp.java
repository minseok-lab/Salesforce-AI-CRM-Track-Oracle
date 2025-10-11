package com.salesforce.homework.month10week2;

import java.util.List;
import java.util.Scanner;

public class AuthorApp {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    AuthorDAO authorDao = new AuthorDAOImpl(); // 저자 DAO
    BookDAO bookDao = new BookDAOImpl(); // 책 DAO

    while (true) {
      System.out.println("\n=====================================================");
      System.out.println("|                 저자 관리 프로그램                |");
      System.out.println("| 1.저자추가 | 2.저자수정 | 3.저자삭제 | 4.저자목록 |");
      System.out.println("| 5.도서추가 | 6.도서수정 | 7.도서삭제 | 8.도서목록 |");
      System.out.println("|         9.도서, 저자 키워드 검색 | 10.종료        |");
      System.out.println("=====================================================");
      System.out.print("메뉴 선택 >> ");

      int menu;
      try {
        menu = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자만 입력하세요");
        continue;
      }

      switch (menu) {
        case 1: // 1. 저자추가
          System.out.println("[저자 정보 추가]");
          System.out.print("이름: ");
          String name = sc.nextLine();
          System.out.print("설명: ");
          String desc = sc.nextLine();

          AuthorVO voInsert = new AuthorVO(0, name, desc);
          authorDao.insert(voInsert);
          break;

        case 2: // 2. 저자추가
          System.out.println("[저자 정보 수정]");
          System.out.print("수정할 저자 ID: ");
          int idUpdate = Integer.parseInt(sc.nextLine());
          System.out.print("새로운 이름: ");
          String newName = sc.nextLine();
          System.out.print("새로운 설명: ");
          String newDesc = sc.nextLine();

          AuthorVO voUpdate = new AuthorVO(idUpdate, newName, newDesc);
          authorDao.update(voUpdate);
          break;

        case 3: // 3. 저자삭제
          System.out.println("[저자 정보 삭제]");
          System.out.print("삭제할 저자 ID: ");
          long idDelete = Long.parseLong(sc.nextLine());

          authorDao.delete(idDelete);
          break;

        case 4: // 4. 저자목록
          List<AuthorVO> authorList = authorDao.getList();
          System.out.println("\n----------- [전체 저자 목록] -----------");
          if (authorList.isEmpty()) {
            System.out.println("등록된 저자 정보가 없습니다.");
          } else {
            for (AuthorVO author : authorList) {
              System.out.printf("ID: %d, 이름: %s, 설명: %s\n",
                  author.getAuthor_id(),
                  author.getAuthor_name(),
                  author.getAuthor_desc());
            }
          }
          System.out.println("------------------------------------");
          break;

        case 5: // 5. 도서추가
          System.out.println("[도서 정보 추가]");
          System.out.print("제목: ");
          String title = sc.nextLine();
          System.out.print("출판사: ");
          String pubs = sc.nextLine();
          System.out.print("출판일(YYYY-MM-DD): ");
          String pubDate = sc.nextLine();
          System.out.print("저자 ID: ");
          int authorId = Integer.parseInt(sc.nextLine());

          BookVO bookVoInsert = new BookVO(0, title, pubs, pubDate, authorId);
          bookDao.insert(bookVoInsert);
          break;

        case 6: // 6. 도서수정
          System.out.println("[도서 정보 수정]");
          System.out.print("수정할 책 ID: ");
          int bookIdUpdate = Integer.parseInt(sc.nextLine());

          System.out.print("새로운 제목: ");
          String newTitle = sc.nextLine();
          System.out.print("새로운 출판사: ");
          String newPubs = sc.nextLine();
          System.out.print("새로운 출판일(YYYY-MM-DD): ");
          String newPubDate = sc.nextLine();
          System.out.print("새로운 저자 ID: ");
          int newAuthorId = Integer.parseInt(sc.nextLine());

          BookVO bookVoUpdate = new BookVO(bookIdUpdate, newTitle, newPubs, newPubDate, newAuthorId);
          bookDao.update(bookVoUpdate);
          break;

        case 7: // 7. 도서삭제
          System.out.println("[도서 정보 삭제]");
          System.out.print("삭제할 책 ID: "); // "저자 ID" -> "책 ID"로 수정
          long bookIdDelete = Long.parseLong(sc.nextLine());

          bookDao.delete(bookIdDelete);
          break;

        case 8: // 8. 도서목록
          List<BookVO> bookList = bookDao.getList();
          System.out.println("\n----------- [전체 도서 목록] -----------");
          if (bookList.isEmpty()) {
            System.out.println("등록된 도서 정보가 없습니다.");
          } else {
            for (BookVO book : bookList) {
              System.out.printf("책 ID: %d, 제목: %s, 출판사: %s, 출판일: %s, 저자: %s\n", // 저자 ID 대신 이름 출력
                  book.getBook_id(),
                  book.getTitle(),
                  book.getPubs(),
                  book.getPub_date(),
                  book.getAuthor_name());
            }
          }
          System.out.println("------------------------------------");
          break;

        case 9: // 9. 키워드 검색
          System.out.println("[저자 정보 검색]");
          System.out.print("검색할 키워드: ");
          String keyword = sc.nextLine();

          // BookDAO를 사용하여 책과 저자 정보를 함께 조회
          List<BookVO> searchList = bookDao.getList(keyword);
          System.out.println("\n----------- [책 및 저자 키워드 검색 결과] -----------");
          if (searchList.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
          } else {
            for (BookVO book : searchList) {
              System.out.printf("책 ID: %d, 제목: %s, 출판사: %s, 출판일: %s, 저자 이름: %s\n",
                  book.getBook_id(),
                  book.getTitle(),
                  book.getPubs(),
                  book.getPub_date(),
                  book.getAuthor_name());
            }
          }
          System.out.println("------------------------------------");
          break;

        case 10: // 10. 종료
          System.out.println("프로그램을 종료합니다.");
          sc.close(); // Scanner 종료
          return;

        default:
          System.out.println("잘못된 메뉴를 선택하셨습니다.");
          break;
      }
    }
  }
}
