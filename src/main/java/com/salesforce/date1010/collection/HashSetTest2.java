package com.salesforce.date1010.collection;

import java.util.HashSet;
import java.lang.reflect.Array;
import java.util.Arrays;

public class HashSetTest2 {
    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();
        colors.add("black");
        colors.add("white");
        colors.add("green");
        colors.add("red");
        colors.add("black");
        colors.add("pink");
        colors.add("yellow");
        colors.add("purple");

        System.out.println(colors);

        colors.remove("black"); // 객체로 삭제
        System.out.println(colors);

        colors.removeIf(color -> color.startsWith("B")); // 조건에 맞는 객체 삭제
        System.out.println(colors);

        colors.removeAll(Arrays.asList("white", "red")); // 여러 객체 삭제
        System.out.println(colors);

        colors.clear(); // 전체 삭제
        System.out.println(colors);
    }
}
