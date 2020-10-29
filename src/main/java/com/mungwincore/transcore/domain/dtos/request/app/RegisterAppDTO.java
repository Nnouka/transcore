package com.mungwincore.transcore.domain.dtos.request.app;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterAppDTO {
    private String name;
    @NotBlank(message = "secret cannot be blank")
    private String secret;
}
