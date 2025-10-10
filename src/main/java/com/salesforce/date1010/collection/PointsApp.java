package com.salesforce.date1010.collection;

import java.util.ArrayList;
import java.util.List;

public class PointsApp {
    public static void main(String[] args) {
        // Point(x, y)를 담을 수 있는 ArrayList 컬렉션 생성
        List<Point> points = new ArrayList<>(); // 생성자 생략
        points.add(new Point(1, 2));
        points.add(new Point(3, 4));
        points.add(new Point(5, 10));

        System.out.println("[" + points.remove(1) + "]");

        System.out.println(points);
    }
}
