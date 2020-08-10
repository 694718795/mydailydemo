package com.shu;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class IDGenerator {

    private static final String Prefix = "Test";

    private ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        public SimpleDateFormat get() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    @Autowired
    private RedisTemplate<String,Long> redisTemplate;


    public  String nextID(){
        //并发的情况下, get和set不是原子操作 就会有问题
        String key = Prefix+simpleDateFormatThreadLocal.get().format(new Date());
        Long existedID = redisTemplate.opsForValue().get(key);
        if(existedID!=null){
            redisTemplate.opsForValue().set(key,existedID+1);
            return key+String.format("%04d",existedID+1);
        }else{
            redisTemplate.opsForValue().set(key,1L);
            return key+"0001";
        }
    }

    public String nextIDLua(){
        String key = Prefix+simpleDateFormatThreadLocal.get().format(new Date());
        DefaultRedisScript<String> redisScript =new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("lua/genID.lua"));
        redisScript.setResultType(String.class);
        //System.out.println(redisScript.getSha1());
        //此时在lua脚本中 的获取自增是redis 内部一个线程实现的操作.  相对于java服务来说  是一个原子操作.
        return redisTemplate.execute(redisScript,(RedisSerializer<?>) redisTemplate.getKeySerializer(),(RedisSerializer<String>)redisTemplate.getKeySerializer(),Lists.newArrayList(key));
    }


    public String currentID(){
        String key = Prefix+simpleDateFormatThreadLocal.get().format(new Date());
        return key+String.format("%04d",redisTemplate.opsForValue().get(key));
    }


}
