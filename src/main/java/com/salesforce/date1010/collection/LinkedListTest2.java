package com.salesforce.date1010.collection;

import java.util.LinkedList;

public class LinkedListTest2 {
    public static void main(String[] args) {
        LinkedList<String> colors = new LinkedList<>();
        // add() 메서드로 객체 추가
        colors.add("black");
        colors.add("white");
        colors.add(0, "green"); // index 위치에 객체 추가
        colors.add("red");

        // set() 메서드로 객체 변경
        colors.set(0, "blue");

        System.out.println(colors);
    }
}
