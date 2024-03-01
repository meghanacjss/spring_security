package com.example.springbootsecurity.config;

import com.example.springbootsecurity.security.filter.CustomAuthenticationFilter;
import com.example.springbootsecurity.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@AllArgsConstructor
@Configuration
public class SecurityConfig  {

    private final CustomAuthenticationFilter customAuthenticationFilter;
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity.addFilterAfter(customAuthenticationFilter,
                BasicAuthenticationFilter.class).authorizeHttpRequests(auth->auth.anyRequest()
                .authenticated()).build();
    }
}



/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("your_username")
                .password("your_password")
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    public AuthenticationManagerBuilder authenticationManagerBuilder(UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder();
        auth.userDetailsService(userDetailsService);
        return auth;
    }
}

*/
