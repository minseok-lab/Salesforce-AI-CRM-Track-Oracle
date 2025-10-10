package com.salesforce.date1010.collection;

// import java.util.Arrays;
import java.util.HashSet;

public class HashSetLotto {
    public static void main(String[] args) {
        // HashSet을 이용한 로또 번호 생성기
        HashSet<Integer> lottoNumbers = new HashSet<>();
        // HashSet의 크기가 6이 될 때까지 반복
        while (lottoNumbers.size() < 6) {
            int number = (int) (Math.random() * 45) + 1;
            lottoNumbers.add(number);
        }
        System.out.println(lottoNumbers);

        /*
         * // HashSet을 배열로 변환
         * Integer[] lottoArray = lottoNumbers.toArray(new Integer[0]);

         * // 배열을 오름차순으로 정렬
         * Arrays.sort(lottoArray);
         * System.out.println(Arrays.toString(lottoArray));
         */
    }
}
