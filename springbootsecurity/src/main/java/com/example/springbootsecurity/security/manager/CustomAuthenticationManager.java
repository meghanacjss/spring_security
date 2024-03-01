package com.example.springbootsecurity.security.manager;

import com.example.springbootsecurity.security.authentication.ExternalAuthentication;
import com.example.springbootsecurity.security.provider.CustomAuthenticationProvider;
import com.example.springbootsecurity.security.provider.ExternalAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final ExternalAuthenticationProvider externalAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(customAuthenticationProvider.supports(authentication.getClass())){
         return customAuthenticationProvider.authenticate(authentication);
        }else{
           return externalAuthenticationProvider.authenticate((authentication));
        }
    }
}
