package com.example.betterdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "test")
    public void test() {


        for (int i = 400; i < Long.MAX_VALUE; i++) {
            try {
                String key = "" + i;
                stringRedisTemplate.opsForValue().set(key, String.valueOf(i));
                String value = stringRedisTemplate.opsForValue().get(key);
                System.out.println("key:" + key + " ,value:" + value);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
