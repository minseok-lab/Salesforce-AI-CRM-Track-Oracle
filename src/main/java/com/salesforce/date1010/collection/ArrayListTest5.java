package com.salesforce.date1010.collection;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest5 {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>(Arrays.asList("Black", "White", "Green", "Red"));

        boolean contains = colors.contains("Black");
        System.out.println("contains: " + contains);

        int index = colors.indexOf("Blue");
        System.out.println("index: " + index);

        index = colors.indexOf("Red");
        System.out.println("index: " + index);
    }
}
