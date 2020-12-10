package com.mungwincore.transcore.controllers;

import com.mungwincore.transcore.domain.dtos.request.LoginRequestDTO;
import com.mungwincore.transcore.domain.dtos.request.app.RegisterAppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppRegisteredDTO;
import com.mungwincore.transcore.security.dtos.AppTokenDTO;
import com.mungwincore.transcore.services.AppService;
import com.mungwincore.transcore.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<AppTokenDTO> getAppToken(
            @RequestBody LoginRequestDTO dto,
            @RequestHeader(value = "X-Sub-Token", required = false) String subToken) {
        return ResponseEntity.ok(appService.getAppToken(dto, subToken));
    }
    @GetMapping("api/protected/app/details")
    @PreAuthorize("hasAuthority('App')")
    public ResponseEntity<AppDTO> getAppDetails() {
        return ResponseEntity.ok(appService.getAppDetails());
    }
}
