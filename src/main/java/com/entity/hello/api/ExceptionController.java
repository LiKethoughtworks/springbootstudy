package com.entity.hello.api;

import com.entity.hello.api.response.LoginResponse;
import com.entity.hello.exception.MyCustomerizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/exceptions")
public class ExceptionController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse findLeadsByGemsUserId() {
        throw new MyCustomerizedException("this is my exception message");
    }
}
