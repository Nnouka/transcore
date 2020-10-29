package com.mungwincore.transcore.services;

import com.mungwincore.transcore.domain.dtos.request.app.RegisterAppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppRegisteredDTO;

public interface AppService {
    AppRegisteredDTO registerApp(RegisterAppDTO dto);
}
