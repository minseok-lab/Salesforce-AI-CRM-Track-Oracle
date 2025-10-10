package com.salesforce.date1010;

public class BoxingText {
    public static void main(String[] args) {
        // Boxing
        int value1 = 100;
        Integer obj1 = new Integer(value1);
        System.out.println("value: " + obj1.intValue());

        // Unboxing
        int value = obj1;
        System.out.println("value: " + value);

        int c = obj1.intValue();
        System.out.println("c: " + c);

        // 연산 시 Unboxing
        int result = obj1 + 100;
        System.out.println("result: " + result);

        // Boxing
        long valuel = 100;
        Long objl = new Long(valuel);
        System.out.println("value: " + objl.longValue());

        // Unboxing
        long value2 = objl;
        System.out.println("value: " + value2);

        long c2 = objl.longValue();
        System.out.println("c: " + c2);
        
    }
}
