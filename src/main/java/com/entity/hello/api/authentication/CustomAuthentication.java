package com.entity.hello.api.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final String userName;
    private final List<String> privilegeCodes;

    public CustomAuthentication(String userName, List<String> privilegeCodes) {
        this.userName = userName;
        this.privilegeCodes = privilegeCodes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.privilegeCodes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public Object getCredentials() {
        return userName;
    }

    @Override
    public Object getDetails() {
        return userName;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return userName;
    }
}
