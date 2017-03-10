package com.entity.hello.authentication;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SecurityUtils {

    public SecurityContext buildContext(String userName) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(new CustomAuthentication(userName, getPrivilegesFrom()));
        return securityContext;
    }

    private List<String> getPrivilegesFrom() {
        return Arrays.asList("ROLE_ADMIN");
    }
}
