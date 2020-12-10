package com.mungwincore.transcore.domain.dtos.response.app;

import com.mungwincore.transcore.security.dtos.AppTokenDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRegisteredDTO {
    private String name;
    private String appKey;
    private AppTokenDTO grantedToken;
    private String token1;
    private String token2;
}
