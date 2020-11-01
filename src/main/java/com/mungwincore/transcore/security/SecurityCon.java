package com.mungwincore.transcore.security;

import org.springframework.security.core.context.SecurityContext;

public interface SecurityCon {
    org.springframework.security.core.context.SecurityContext createSecurityContext(String username);
}
