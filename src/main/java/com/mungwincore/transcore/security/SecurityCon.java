package com.mungwincore.transcore.security;

import org.springframework.security.core.context.SecurityContext;

public interface SecurityCon {
    SecurityContext createSecurityContext(String username);
    SecurityContext createSecurityContext2(String token);
}
