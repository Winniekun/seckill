package com.wkk.dao.cache;

import com.wkk.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Time: 19-12-29下午4:00
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class RedisDao {
    private JedisPool jedisPool;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        // redis操作
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key =  "seckill:" + seckillId;
                jedis.get
            }finally {
                jedis.close();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        return null;
    }
}
