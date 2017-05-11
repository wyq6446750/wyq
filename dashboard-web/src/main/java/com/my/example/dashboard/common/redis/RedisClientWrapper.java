package com.my.example.dashboard.common.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date:17/3/30
 * Time:下午3:13
 *
 * Redis工具类
 *
 * @author yongquan.wen
 */
public class RedisClientWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClientWrapper.class);

    private AtomicBoolean INIT_FLAG = new AtomicBoolean(false);

    private String servers;

    private RedisConfig redisConfig;

    private ShardedJedisPool shardedJedisPool;

    private AtomicInteger ipCount = new AtomicInteger(0);

    private void init() {
        LOGGER.info("initializing redis component");
        if (INIT_FLAG.compareAndSet(false, true)) {
            if (redisConfig == null) {
                throw new NullPointerException("Not Found Redis Config Info");
            }
            if (StringUtils.isBlank(servers)) {
                throw new NullPointerException("Not Support Null Servers Address");
            }
            String[] arr = StringUtils.split(servers, ",");
            List<JedisShardInfo> jedisShardInfoList = new ArrayList<>(arr.length);
            for (String address : arr) {
                String[] temp = StringUtils.split(address, ":");
                String ip = temp[0];
                int port = Integer.parseInt(temp[1]);
                JedisShardInfo jedisShardInfo = new JedisShardInfo(ip, port);
                jedisShardInfoList.add(jedisShardInfo);
                ipCount.incrementAndGet();
            }
            shardedJedisPool = new ShardedJedisPool(redisConfig, jedisShardInfoList);
        }
        LOGGER.info("redis component init success");
    }


    public Integer getIpCount() {
        return ipCount.get();
    }

    public Long setnx(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.setnx(key, value);
        } catch (Exception e) {
            LOGGER.error("RedisClientWrapper expire occur : ", e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public String setex(String key, int seconds, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            LOGGER.error("RedisClientWrapper setex occur : ", e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }


    public Long expire(String key, int expireTime) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.expire(key, expireTime);
        } catch (Exception e) {
            LOGGER.error("RedisClientWrapper expire occur : ", e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public Long rpush(String key, String item) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.rpush(key, item);
        } catch (Exception e) {
            LOGGER.error("RedisClientWrapper lpush occur error: ", e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public Long llen(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.llen(key);
        } catch (Exception e) {
            LOGGER.error("RedisClientWrapper llen occur error: ", e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public String lpop(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lpop(key);
        } catch (Exception e) {
            LOGGER.error("RedisClientWrapper lpush occur error: ", e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     * <p/>
     * 可用版本：
     * >= 1.0.0
     * 时间复杂度：
     * O(1)
     * 返回值：
     * 当 key 不存在时，返回 -2 。
     * 当 key 存在但没有设置剩余生存时间时，返回 -1 。
     * 否则，以秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     */
    public Long ttl(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.ttl(key);
        } catch (Exception e) {
            LOGGER.error("ttl error:" + e.getMessage());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public Set<String> smembers(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public Long sadd(String key, String... members) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.sadd(key, members);
        } catch (Exception e) {
            LOGGER.error("sadd error:" + e.getMessage());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return null;
    }

    //移除set中的元素 在2.4版本之前只能传递一个参数 这点需要注意
    public Long srem(String key, String... members) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.srem(key, members);
        } catch (Exception e) {
            LOGGER.error("srem error :" + e.getMessage());
            return null;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    //get方式说明
    public String get(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.get(key);
        } catch (Exception e) {
            LOGGER.error("get error :" + e.getMessage());
            return null;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    //getset方法说明
    public String getSet(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.getSet(key, value);
        } catch (Exception e) {
            LOGGER.error("getSet error :" + e.getMessage());
            return null;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    //del方法
    public Long del(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.del(key);
        } catch (Exception e) {
            LOGGER.error("del error :" + e.getMessage());
            return null;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    //exists
    public boolean exists(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.exists(key);
        } catch (Exception e) {
            LOGGER.error("exists error :" + e.getMessage());
            return false;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    public Long hset(String key, String field, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.hset(key, field, value);
        } catch (Exception e) {
            LOGGER.error("hset error :" + e.getMessage());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }

        return -1L;
    }

    public String hget(String key, String field) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.hget(key, field);
        } catch (Exception e) {
            LOGGER.error("hget error :" + e.getMessage());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }

        return null;
    }

    public Set<String> hkeys(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.hkeys(key);
        } catch (Exception e) {
            LOGGER.error("hkeys error :" + e.getMessage());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }

        return Collections.emptySet();
    }

    public Map<String, String> hgetAll(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.hgetAll(key);
        } catch (Exception e) {
            LOGGER.error("hkeys error :" + e.getMessage());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }

        return Collections.emptyMap();
    }

    public String set(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error("set error :" + e.getMessage());
            return null;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    public String incr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return "" + shardedJedis.incr(key);
        } catch (Exception e) {
            LOGGER.error("set error :" + e.getMessage());
            return null;
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }
}
