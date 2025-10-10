package com.salesforce.date1010.collection;

import java.util.Iterator;
import java.util.HashSet;

public class HashSetTest3 {
    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();
        colors.add("black");
        colors.add("white");
        colors.add("green");
        colors.add("red");

        // for-each 문으로 전체 탐색
        for (String color : colors) {
            System.out.print(color + "\t");
        }

        System.out.println();

        // using iterator
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
    }
}
