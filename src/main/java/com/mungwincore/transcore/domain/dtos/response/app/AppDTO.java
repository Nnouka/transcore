package com.mungwincore.transcore.domain.dtos.response.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDTO {
    private String name;
    private String appKey;
}
