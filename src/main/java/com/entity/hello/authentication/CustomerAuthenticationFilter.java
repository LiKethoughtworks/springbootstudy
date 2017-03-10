package com.entity.hello.authentication;

import com.entity.hello.redis.RedisTokenPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomerAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Autowired
    private RedisTokenPool userTokenPool;

    @Autowired
    public CustomerAuthenticationFilter(@Qualifier(value = "authenticationManager")
                                              AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token) || token == null) {
            return null;
        }

        return userTokenPool.getCustomer(token);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }


}
