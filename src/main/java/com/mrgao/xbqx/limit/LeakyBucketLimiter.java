package com.mrgao.xbqx.limit;

import lombok.extern.slf4j.Slf4j;

/**
 * 漏桶算法
 */
@Slf4j
public class LeakyBucketLimiter implements TrafficLimiter {

    private long timeStamp = System.currentTimeMillis();

    private long capacity = 100; //桶的容量

    private long rate = 10; // 水漏出的速度（每秒系统处理的请求数）

    private long water = 20;// 当前水量（当前累积的请求数）

    /**
     * 返回true则表示限流 false代表通过
     *
     * @return
     */
    @Override
    public synchronized boolean limit() {
        long now = System.currentTimeMillis();
        // 先执行漏水 计算剩余水量
        water = Math.max(0, water - ((now - timeStamp) / 1000) * rate);
        timeStamp = now;
        if ((water + 1) <= capacity) {
            // 水还未被加满
            water++;
            System.out.println("加水至:" + water);
            return false;
        } else {
            // 水满则拒绝加水
            return true;
        }
    }
}
