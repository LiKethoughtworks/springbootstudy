package com.entity.hello.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTokenPool {
    public static final String TOKEN_GROUP_KEY = "TOKEN_GROUP";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void insertCustomerData(String token, String userName){
        redisTemplate.opsForHash().put(TOKEN_GROUP_KEY, token, userName);
    }

    public String getCustomer(String token){
        String name = redisTemplate.<String, String>opsForHash()
                .get(TOKEN_GROUP_KEY, token);

        System.out.println("get customer from redis  using token " + name + token);
        return name;
    }

}
