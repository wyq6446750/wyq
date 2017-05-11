package com.my.example.dashboard.common.redis;

import com.my.example.dashboard.utils.GZIPUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Date:17/3/30
 * Time:下午3:48
 * <p/>
 * Redis分布式队列，同时提供文本压缩gzip
 *
 * @author yongquan.wen
 */
public class RedisQueue {

    private int magnifyFactor = 100;

    private int enTime;

    private int deTime;

    private int modMagnifyNumber = 4 * magnifyFactor;

    private RedisClientWrapper redisClientWrapper;

    public String deQueue(String queueName) {
        int mod = deTime % modMagnifyNumber;
        String result = redisClientWrapper.lpop(queueName + String.valueOf(mod));
        deTime++;
        if (deTime > 100000) {
            deTime = 0;
        }
        return result;
    }

    public String deQueue(String queueName, boolean gzip) {
        String result = deQueue(queueName);
        return !gzip ? result : (StringUtils.isEmpty(result) ? result : GZIPUtils.ungzip(result));
    }

    public boolean enQueue(String queueName, String data) {
        int mod = enTime % modMagnifyNumber;
        long result = redisClientWrapper.rpush(queueName + String.valueOf(mod), data);
        enTime++;
        if (enTime > 100000) {
            enTime = 0;
        }
        if (result > 0)
            return true;
        return false;
    }

    public boolean enQueue(String queueName, String data, boolean gzip) {
        return gzip ? enQueue(queueName, GZIPUtils.gzip(data)) : enQueue(queueName, data);
    }

    public RedisClientWrapper getRedisClientWrapper() {
        return redisClientWrapper;
    }

    public void setRedisClientWrapper(RedisClientWrapper redisClientWrapper) {
        this.redisClientWrapper = redisClientWrapper;
    }
}
