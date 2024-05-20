package com.mrgao.xbqx.limit;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 滑动时间窗口限流
 */
@Slf4j
public class SlidingTimeTrafficLimiter implements TrafficLimiter {

    // 服务器在最近1秒内的访问次数，可以放到Redis中 实现分布式系统的访问统计
    private int reqCount;

    // 使用linkedList来记录滑动窗口的10个格子
    private LinkedList<Integer> slots = new LinkedList<>();

    // 每秒限流的最大请求数
    private int limitNum = 100;

    // 滑动时间窗口里的每个格子的时间长度 单位ms
    private long windowsLength = 100;

    // 滑动时间窗口里的格子数量
    private int windowsNum = 10;

    /**
     * 返回true代表限流 false代表通过
     *
     * @return
     */
    @Override
    public synchronized boolean limit() {
        if ((reqCount + 1) > limitNum) {
            return true;
        }
        // 此处代码的逻辑为：为集合中最后一个格子设置添加请求数+1
        slots.set(slots.size() - 1, slots.peekLast() + 1);
        reqCount++;
        return false;
    }

    /**
     * 滑动时间窗口限流算法构造器
     */
    public SlidingTimeTrafficLimiter() {
        // 先占用一个格子
        slots.addLast(0);
        // 开启一个线程
        new Thread(() -> {
            while (true) {
                try {
                    // 每延时100ms 添加一个格子
                    Thread.sleep(windowsLength);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 添加占用一个格子
                slots.addLast(0);
                if (slots.size() > windowsNum) {
                    // 超过10个格子时, 先将第一个格子里的请求数减去
                    reqCount = reqCount - slots.peekFirst();
                    // 再移除第一个格子
                    slots.removeFirst();
                    System.out.println("滑动格子:" + reqCount);
                }
            }
        }).start();
    }

}
