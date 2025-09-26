package com.prateek.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @Disabled("Tested")
    void testRedis() {
        redisTemplate.opsForValue().set("email", "prateek@gmail.com");
        String value = (String) redisTemplate.opsForValue().get("email");
        assertEquals("prateek@gmail.com", value);
    }
}
