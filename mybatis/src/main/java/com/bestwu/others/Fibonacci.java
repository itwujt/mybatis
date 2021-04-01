package com.bestwu.others;

import java.util.ArrayList;
import java.util.List;

/**
 * 斐波 为什么要 纳妾？
 * @author Best Wu
 * @date 2021/3/25 16:18 <br>
 */
public class Fibonacci {

    public static long resolve(int index) {
        long start = 1L;
        List<Long> list = new ArrayList<>();
        for (int i = 1; i <= index; i++) {
            if (1 == index) {
                return start;
            }
            if (i == 1) {
                list.add(0, 1L);
                continue;
            }
            if (i == 2) {
                list.add(1, 1L);
                continue;
            }
            list.add(i - 1, list.get(i - 3) + list.get(i - 2));
        }
        return list.get(index - 1);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long resolve = resolve3(100);
        long end = System.currentTimeMillis();
        System.out.println(resolve);
        System.out.println(end - start);

    }

    public static int resolve2(int n){
        if(n > 2){
            return resolve2(n - 1) + resolve2(n - 2);
        }
        return 1;
    }


    public static long resolve3(int index) {
        long first = 1L;
        long second = first;
        long res = first;
        for (int i = 1; i <= index; i++) {
            if (i > 2) {
                res = first + second;
                first = second;
                second = res;
            }
        }
        return res;
    }
}
