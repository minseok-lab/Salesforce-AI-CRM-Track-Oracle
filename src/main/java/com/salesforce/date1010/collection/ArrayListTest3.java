package com.salesforce.date1010.collection;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest3 {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>(Arrays.asList("Black", "White", "Green"));
        String removedColor = colors.remove(0); // index 위치의 객체 삭제
        System.out.println("removedColor: " + removedColor);

        colors.remove("White"); // 객체로 삭제
        System.out.println(colors);

        colors.clear(); // 전체 삭제
        System.out.println(colors);
    }
}
