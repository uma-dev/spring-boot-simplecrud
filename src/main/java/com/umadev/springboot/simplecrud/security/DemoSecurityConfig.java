package com.umadev.springboot.simplecrud.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class DemoSecurityConfig {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager () {

        UserDetails john = User.builder()
            .username("john")
            .password("{noop}123")
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("Mary")
            .password("{noop}123")
            .roles("EMPLOYEE", "MANAGER")
            .build();

        UserDetails susan = User.builder()
            .username("Susan")
            .password("{noop}123")
            .roles("EMPLOYEE", "MANAGER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
}
