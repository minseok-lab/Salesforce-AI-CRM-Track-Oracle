package com.salesforce.date1010.collection;

import java.util.ArrayList;

public class ArrayListTest2 {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
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
