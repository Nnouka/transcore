package com.mungwincore.transcore.services;

import com.mungwincore.transcore.domain.dtos.request.LoginRequestDTO;
import com.mungwincore.transcore.domain.dtos.request.app.RegisterAppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppRegisteredDTO;
import com.mungwincore.transcore.security.dtos.AppTokenDTO;

public interface AppService {
    AppRegisteredDTO registerApp(RegisterAppDTO dto);
    AppDTO getAppDetails();
    AppTokenDTO getAppToken(LoginRequestDTO dto, String appToken);
}
