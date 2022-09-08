package com.cpp.loginandregister.service.impl;


import com.cpp.loginandregister.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public void put(String s1, String s2) {
        redisTemplate.opsForValue().set(s1, s2,5, TimeUnit.MINUTES);
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
