package com.mungwincore.transcore.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
  @NotBlank(message = "key is required")
  private String key;
  @NotBlank(message = "secret is required")
  private String secret;
}
