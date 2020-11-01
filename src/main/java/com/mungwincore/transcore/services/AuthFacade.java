package com.mungwincore.transcore.services;


import org.springframework.security.core.Authentication;

public interface AuthFacade {
  Authentication getAuthentication();
}
