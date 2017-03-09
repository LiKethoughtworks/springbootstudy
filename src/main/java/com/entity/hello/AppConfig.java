package com.entity.hello;

import com.entity.hello.repository.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate< String, Customer> redisTemplate() {
        final RedisTemplate<String, Customer > template = new RedisTemplate<>();
        template.setConnectionFactory( jedisConnectionFactory() );
        return template;
    }
}