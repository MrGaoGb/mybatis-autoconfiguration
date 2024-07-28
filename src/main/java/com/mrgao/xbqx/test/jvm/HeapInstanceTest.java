package com.mrgao.xbqx.test.jvm;

import java.util.ArrayList;
import java.util.Random;

public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];
    byte[] buffer1 = new byte[1024 * 1024];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapInstanceTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapInstanceTest());
            Thread.sleep(10);
        }
    }
}
