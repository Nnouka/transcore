package com.mungwincore.transcore.controllers;

import com.mungwincore.transcore.domain.dtos.request.LoginRequestDTO;
import com.mungwincore.transcore.domain.dtos.request.app.RegisterAppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppRegisteredDTO;
import com.mungwincore.transcore.security.dtos.AppTokenDTO;
import com.mungwincore.transcore.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AppController {
    private AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("api/public/app/register")
    public ResponseEntity<AppRegisteredDTO> registerApp(@RequestBody @Valid RegisterAppDTO dto) {
        return ResponseEntity.ok(appService.registerApp(dto));
    }
    @PostMapping("api/public/app/token")
    public ResponseEntity<AppTokenDTO> getAppToken(@RequestBody @Valid LoginRequestDTO dto) {
        return ResponseEntity.ok(appService.getAppToken(dto));
    }
    @GetMapping("api/protected/app/details")
    public ResponseEntity<AppDTO> getAppDetails() {
        return ResponseEntity.ok(appService.getAppDetails());
    }
}
