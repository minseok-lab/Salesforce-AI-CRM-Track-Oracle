package com.salesforce.date1010.collection;

import java.util.HashSet;

public class HashSetTest4 {
    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();
        colors.add("black");
        colors.add("white");
        colors.add("green");
        colors.add("red");

        System.out.println(colors.contains("green")); // true
        System.out.println(colors.contains("blue"));  // false
    }
}
