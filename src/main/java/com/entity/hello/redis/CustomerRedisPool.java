package com.entity.hello.redis;

import com.entity.hello.repository.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerRedisPool {
    public static final String CUSTMER_KEY = "mycustomer";
    public static final long OBJECT_KEY = 999L;

    @Autowired
    private RedisTemplate<String, Customer> redisTemplate;


    public void insertCustomerData(){
        Customer customer = new Customer("cachefirstname", "cachelastname");
        customer.setId(OBJECT_KEY);

        redisTemplate.opsForHash().put(CUSTMER_KEY, customer.getId(), customer);
    }

    public void getCustomer(){
        Customer customer = redisTemplate.<Long, Customer>opsForHash()
                .get(CUSTMER_KEY, OBJECT_KEY);

        System.out.println("get customer from redis" + customer);
    }

}
