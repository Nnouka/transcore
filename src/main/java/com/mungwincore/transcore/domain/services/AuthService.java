package com.mungwincore.transcore.domain.services;


import com.mungwincore.transcore.aspects.annotations.Loggable;
import com.mungwincore.transcore.services.AuthFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthService implements AuthFacade {
  @Override
  @Loggable("Hello there")
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
