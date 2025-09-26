package com.prateek.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setValue(String key, Object value, Long ttl) {
        try {
            ObjectMapper m = new ObjectMapper();
            String valueString = m.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, valueString, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    public <T> T getValue(String key, Class<T> entityClass) {
        try {

            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper m = new ObjectMapper();
            if (o != null) {
                return m.readValue(o.toString(), entityClass);
            }
            return null;

        } catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }
}
