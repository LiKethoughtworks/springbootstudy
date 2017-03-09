package com.entity.hello.api;

import com.entity.hello.api.request.LoginRequest;
import com.entity.hello.api.response.LoginResponse;
import com.entity.hello.repository.Customer;
import com.entity.hello.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserLoginService userLoginService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return  userLoginService.login(loginRequest);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> listAll(){
        return userLoginService.findAll();
    }
}


