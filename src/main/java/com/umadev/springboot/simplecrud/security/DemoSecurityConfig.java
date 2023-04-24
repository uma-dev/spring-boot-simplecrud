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

    // Add support  for JDBC, to not hardcode users. This code doesn't change with pwd encryption
    @Bean 
    UserDetailsManager userDetailsManager (DataSource dataSource) {
        // Spring security queries the db each login. In the database: 
        // Roles and authorities are similar in Spring.The main difference is 
        // that roles have special semantics. Starting with Spring Security 4, 
        //the ‘ROLE_‘ prefix is automatically added (if it's not already there)
        // by any role-related method.

        // Queries for using custom tables/schema
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            // Question mark is the parameter value from login
            "select user_id, pw, active from members where user_id=?"
        );

        // Define query to retrieve the authorities/roles
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            // Question mark is the parameter value from login
            "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
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
