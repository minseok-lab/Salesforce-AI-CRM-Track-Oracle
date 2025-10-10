package com.salesforce.date1010.collection;

import java.util.LinkedList;
import java.util.Arrays;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> integers1 = new LinkedList<Integer>(); // 제네릭, 타입 지정
        LinkedList<Integer> integers2 = new LinkedList<>(); // 타입 생략 가능
        LinkedList<Integer> integers3 = new LinkedList<>(integers1); // 다른 컬렉션으로 초기화
        LinkedList<Integer> integers4 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5)); // 배열로 초기화
    }
}
