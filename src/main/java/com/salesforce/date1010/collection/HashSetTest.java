package com.salesforce.date1010.collection;

import java.util.Arrays;
import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();
        colors.add("black");
        colors.add("white");
        colors.add("green");
        colors.add("red");
        colors.add("black");

        System.out.println(colors);

        HashSet<String> otherColors = new HashSet<>(Arrays.asList("pink", "yellow", "purple", "black"));
        colors.addAll(otherColors);
        System.out.println(colors);
    }
}
