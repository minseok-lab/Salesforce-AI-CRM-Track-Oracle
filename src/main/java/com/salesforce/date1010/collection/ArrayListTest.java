package com.salesforce.date1010.collection;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> integer1 = new ArrayList<Integer>(); // 제네릭, 타입 지정
        ArrayList<Integer> integer2 = new ArrayList<>(); // 타입 생략 가능
        ArrayList<Integer> integer3 = new ArrayList<>(10); // 초기 용량 지정
        ArrayList<Integer> integer4 = new ArrayList<>(integer1); // 컬렉션으로 초기화
        ArrayList<Integer> integer5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)); // 배열로 초기화
    }
}
