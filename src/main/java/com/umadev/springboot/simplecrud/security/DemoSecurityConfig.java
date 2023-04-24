package com.umadev.springboot.simplecrud.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class DemoSecurityConfig {

    // Add support  for JDBC, to not hardcode users
    @Bean UserDetailsManager userDetailsManager (DataSource dataSource) {
        
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean 
    public SecurityFilterChain filterChain( HttpSecurity http) throws Exception { 
        
        http.authorizeHttpRequests( configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                // In case you are passing ID in JSON (service branch)
                //.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")

                // If passing ID by URL: 
                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
        // use HTTP basic authentication
        http.httpBasic();

        // Disable CSRF
        // In general not required for stateless REST API that use POST, PUT, DELETE and/or PATCH
        http.csrf().disable();
        return http.build();
    }


    /*
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
     */
}
