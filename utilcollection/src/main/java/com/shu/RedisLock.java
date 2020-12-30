package com.example.demo.RedisLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis分布式锁
 */
@Component
public class RedisLock {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean tryLock(String key) {
        Long currentTime = this.currentTime();

        // 获取锁
        boolean success = false;
        for(int i = 0; i < 15; i ++) {
            success = redisTemplate.execute(new RedisCallback<Boolean>() {

                @Override
                public Boolean doInRedis(RedisConnection conn) {
                    boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                    conn.expire(key.getBytes(), 3 * 60L);// 3分钟
                    return succ;
                }
            });
            if(success) return success;

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                logger.error("",e);
                Thread.currentThread().interrupt();
            }
        }

        // 防止宕机导致锁一直存在，比较锁value的时间值与当前时间，如果锁是在很久以前加上的，则可以认为该锁异常，可强制释放锁
        if(! success) {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String value = operations.get(key);
            if(value == null) {
                return false;
            }
            Long oldTime = Long.valueOf(value);

            if(currentTime - oldTime > 3 * 60 * 1000) {// 3分钟
                // 释放锁
                releaseLock(key);

                // 再次获取锁
                return redisTemplate.execute(new RedisCallback<Boolean>() {

                    @Override
                    public Boolean doInRedis(RedisConnection conn)  {
                        boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                        conn.expire(key.getBytes(), 3 * 60L);// 3分钟
                        return succ;
                    }
                });
            }
        }
        return false;
    }


    public void releaseLock(String key) {
        redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection conn)  {
                return conn.del(key.getBytes());
            }
        });
    }

    public Boolean updateLock(String key) {
        Long currentTime = this.currentTime();
        boolean success = false;
        success = redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection conn) {
                boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                conn.expire(key.getBytes(), 3 * 60L);// 3分钟
                return succ;
            }
        });
        if(success){
            return success;
        }else {
            return false;
        }
    }


    private Long currentTime() {
        return redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection conn)  {
                return conn.time();
            }
        });
    }



}