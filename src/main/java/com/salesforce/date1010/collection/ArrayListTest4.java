package com.salesforce.date1010.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayListTest4 {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>(Arrays.asList("Black", "White", "Green", "Red"));

        for (String color : colors) {
            System.out.print(color + "\t");
        }

        System.out.println();

        for (int i = 0; i < colors.size(); i++) {
            System.out.print(colors.get(i) + "\t");
        }

        System.out.println();

        // using iterator -- 순방향 회전
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }

        System.out.println();

        // using list iterator -- 역방향 회전
        ListIterator<String> listIterator = colors.listIterator(colors.size());
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + "\t");
        }
    }
}
