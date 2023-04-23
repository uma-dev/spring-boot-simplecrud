package com.umadev.springboot.simplecrud.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class DemoSecurityConfig {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager () {

        UserDetails john = User.builder()
            .username("John")
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

    @Bean 
    public SecurityFilterChain filterChain( HttpSecurity http) throws Exception { 
        
        http.authorizeHttpRequests( configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
        // use HTTP basic authentication
        http.httpBasic();

        // Disable CSRF
        // In general not required for stateless REST API that use POST, PUT, DELETE and/or PATCH
        http.csrf().disable();
        return http.build();
    }
}
