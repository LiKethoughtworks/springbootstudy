package com.entity.hello.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class MultiHttpSecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(MultiHttpSecurityConfig.class);

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Configuration
    public static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomerAuthenticationFilter customAuthenticationFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().disable();
            http.securityContext().disable();
            http.requestCache().requestCache(new NullRequestCache());
            http.addFilter(customAuthenticationFilter);
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/token").permitAll()
                    .antMatchers("/exceptions").permitAll()
                    .anyRequest().authenticated();

            http.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
                    .accessDeniedHandler(accessDeniedHandler());
        }

        @Bean
        public AuthenticationEntryPoint unauthorizedEntryPoint() {
            return (request, response, authException) -> {
                logger.error("access error with token {}....: {}", response.getHeader(HttpHeaders.AUTHORIZATION)
                        , authException.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            };
        }

        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
            return (request, response, accessDeniedException) -> {
                logger.error("access denied with token: {}", response.getHeader(HttpHeaders.AUTHORIZATION));
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            };
        }
    }
}