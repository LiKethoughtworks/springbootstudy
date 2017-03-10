package com.entity.hello.service;

import com.entity.hello.api.request.LoginRequest;
import com.entity.hello.api.response.LoginResponse;
import com.entity.hello.redis.RedisTokenPool;
import com.entity.hello.repository.Customer;
import com.entity.hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserLoginService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RedisTokenPool redisTokenPool;


    public List<Customer> findAll(){
       return customerRepository.findAll();
    }
     private static final Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    public LoginResponse login(LoginRequest loginRequest) {
        logger.info("hello world");
        Customer customer = customerRepository.findByLastName(loginRequest.getUserName()).get(0);
        Optional.ofNullable(customer).orElseThrow(()->{
                    logger.error("user not exist: {}", loginRequest.getUserName());
                    return new BadCredentialsException("Wrong crediatials");
                }
        );

        LoginResponse loginResponse = new LoginResponse();
        String token = generateToken();
        redisTokenPool.insertCustomerData(token, loginRequest.getUserName());
        loginResponse.setToken(token);
        loginResponse.setUserName(loginRequest.getUserName());
        return loginResponse;
    }

    private String generateToken(){
        return UUID.randomUUID().toString();
    }
}
