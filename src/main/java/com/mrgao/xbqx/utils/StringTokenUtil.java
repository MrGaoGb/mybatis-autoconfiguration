package com.mrgao.xbqx.utils;

import java.util.StringTokenizer;

/**
 * @author Mr.Gao
 * @date 2024/7/3 22:21
 * @apiNote:
 */
public class StringTokenUtil {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("this,is.a test");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}
