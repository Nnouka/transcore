package com.mungwincore.transcore.security.services;

import com.mungwincore.transcore.security.SecurityCon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextMaker implements SecurityCon {
    private UserDetailsService userDetailsService;
    private UserDetailsService userDetailsService2;

    @Autowired
    public void setUserDetailsService(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setUserDetailsService2(@Qualifier("userDetailsService2") UserDetailsService userDetailsService2) {
        this.userDetailsService2 = userDetailsService2;
    }

    @Override
    public SecurityContext createSecurityContext(String username) {
        UserDetails principal = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, principal.getPassword(), principal.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }

    @Override
    public SecurityContext createSecurityContext2(String token) {
        UserDetails principal = userDetailsService2.loadUserByUsername(token);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, principal.getPassword(), principal.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
