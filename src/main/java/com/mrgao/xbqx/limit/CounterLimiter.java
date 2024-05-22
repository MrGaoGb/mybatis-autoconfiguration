package com.mrgao.xbqx.limit;


import lombok.extern.slf4j.Slf4j;

/**
 * 计数器算法
 */
@Slf4j
public class CounterLimiter implements TrafficLimiter {

    private long timeStamp = System.currentTimeMillis();

    private long reqCount = 0; // 请求次数

    private long limitNum = 100;// 每秒限流的最大请求数

    private long interval = 1000;// 时间窗口时长 单位ms

    /**
     * true 代表限流 false代表通过
     *
     * @return
     */
    @Override
    public synchronized boolean limit() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {//当前时间窗口内
            // 判断 当前时间窗口请求数加1 是否超过每秒限流的最大请求数
            if (reqCount + 1 > limitNum) {
                return true;
            }
            reqCount++;
            return false;
        } else {
            // 开启新的时间窗口
            timeStamp = now;
            // 重置计数器
            reqCount = 1;
            return false;
        }
    }
}
