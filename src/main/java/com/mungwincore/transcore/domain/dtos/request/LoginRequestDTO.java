package com.mungwincore.transcore.domain.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
  @NotBlank(message = "id is required")
  private String id;
  @NotBlank(message = "secret is required")
  private String secret;
}
