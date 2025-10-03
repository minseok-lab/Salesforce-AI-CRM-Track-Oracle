package com.salesforce.homework;

import java.util.List;
import java.util.Scanner;

public class AuthorApp {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    AuthorDAO dao = new AuthorDAOImpl();

    while (true) {
      System.out.println("\n==============================================================");
      System.out.println("|                     저자 관리 프로그램                     |");
      System.out.println("| 1.저자추가 | 2.저자수정 | 3.저자삭제 | 4.저자목록 | 5.종료 |");
      System.out.println("==============================================================");
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
          dao.insert(voInsert);
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
          dao.update(voUpdate);
          break;

        case 3: // 3. 저자삭제
          System.out.println("[저자 정보 삭제]");
          System.out.print("삭제할 저자 ID: ");
          long idDelete = Long.parseLong(sc.nextLine());

          dao.delete(idDelete);
          break;

        case 4: // 4. 저자목록
          List<AuthorVO> authorList = dao.getList();
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

        case 5: // 5. 종료
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
