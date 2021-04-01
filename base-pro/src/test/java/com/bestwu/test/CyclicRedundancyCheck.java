package com.bestwu.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2020/6/29 21:29 <br>
 */
@Slf4j
public class CyclicRedundancyCheck {
    /**
     * 将有效信息码移位
     * @param gx 约定多项式
     * @param origin 有效信息码
     * @return 移位后的数据
     */
    public static String shift(long gx, String origin) {
        byte[] bytes = origin.getBytes(StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte eightBit : bytes) {
            stringBuilder.append(Long.toBinaryString(eightBit));
        }
        return stringBuilder.toString();
    }

    @Test
    public void test0() {
        String str = "1234";
        System.out.println(Long.parseLong(str, 16));

        System.out.println(15 + 15 * 16L);

        System.out.println(0b1010 ^ 0b1111);
    }
}
