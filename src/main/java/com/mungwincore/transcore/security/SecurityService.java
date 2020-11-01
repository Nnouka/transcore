package com.mungwincore.transcore.security;

import com.mungwincore.transcore.domain.dtos.request.LoginRequestDTO;
import com.mungwincore.transcore.security.dtos.AppTokenDTO;

public interface SecurityService {
    AppTokenDTO getAppToken(LoginRequestDTO dto);
}
