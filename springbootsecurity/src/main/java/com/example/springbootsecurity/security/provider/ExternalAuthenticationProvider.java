package com.example.springbootsecurity.security.provider;

import com.example.springbootsecurity.security.authentication.CustomAuthentication;
import com.example.springbootsecurity.security.authentication.ExternalAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExternalAuthenticationProvider implements AuthenticationProvider {
    @Value("${external.secret.key}")
    private String appExternalSecretKey;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ExternalAuthentication externalAuthentication=(ExternalAuthentication) authentication;
        String headerKey = externalAuthentication.getKey();
        if(appExternalSecretKey.equalsIgnoreCase(headerKey)){
            log.info("request have valid key");
            return new ExternalAuthentication(true,headerKey);
        }
        throw new BadCredentialsException("Secret key in header did not match external application Secret key...");
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return ExternalAuthentication.class.equals(authentication);
    }
}
