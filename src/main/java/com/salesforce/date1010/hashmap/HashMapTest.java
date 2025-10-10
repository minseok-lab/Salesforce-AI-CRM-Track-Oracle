package com.salesforce.date1010.hashmap;

import java.util.HashMap;
import java.util.Map.Entry;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<Integer, String>() {
            {
            put(1, "사과");
            put(2, "바나나");
            put(3, "포도");
            }
        };
        System.out.println(map); // 전체 출력 : {1=사과, 2=바나나, 3=포도}
        System.out.println(map.get(2)); // 키 2에 해당하는 값 출력 : 바나나

        // entrySet() 활용
        for (Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("[Key] : " + entry.getKey() + ", [Value] : " + entry.getValue());
        }

        // keySet() 활용
        for (Integer i : map.keySet()) {
            System.out.println("[Key] : " + i + ", [Value] : " + map.get(i));
        }
    }
}
