package com.mrgao.xbqx.limit;

import lombok.extern.slf4j.Slf4j;

/**
 * 令牌桶算法
 */
@Slf4j
public class TokenBucketLimiter implements TrafficLimiter {

    private long timeStamp = System.currentTimeMillis();

    private long capacity = 100;//桶的容量

    private long rate = 10; // 令牌放入的速度

    private long token = 20; // 当前令牌的数量

    /**
     * true代表限流，false代表通过
     *
     * @return
     */
    @Override
    public synchronized boolean limit() {
        long now = System.currentTimeMillis();
        // 先添加令牌
        token = Math.min(capacity, token + (now - timeStamp) * rate);
        timeStamp = now;
        if (token < 1) {
            // 不存在可用的令牌则拒绝
            return true;
        } else {
            // 存在可用的令牌 取出一个令牌
            token--;
            return false;
        }
    }
}
