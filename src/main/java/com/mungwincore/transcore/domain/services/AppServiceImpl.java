package com.mungwincore.transcore.domain.services;

import com.mungwincore.transcore.domain.dtos.request.LoginRequestDTO;
import com.mungwincore.transcore.domain.dtos.request.app.RegisterAppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppDTO;
import com.mungwincore.transcore.domain.dtos.response.app.AppRegisteredDTO;
import com.mungwincore.transcore.domain.models.App;
import com.mungwincore.transcore.domain.repositories.AppRepository;
import com.mungwincore.transcore.exceptions.ApiException;
import com.mungwincore.transcore.exceptions.ErrorCodes;
import com.mungwincore.transcore.security.SecurityService;
import com.mungwincore.transcore.security.dtos.AppTokenDTO;
import com.mungwincore.transcore.services.AppService;
import com.mungwincore.transcore.services.AuthFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {
    private AppRepository appRepository;
    private PasswordEncoder passwordEncoder;
    private SecurityService securityService;
    private AuthFacade authFacade;

    @Autowired
    public void setAppRepository(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setAuthFacade(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @Override
    public AppRegisteredDTO registerApp(RegisterAppDTO dto) {
        String appKey = UUID.randomUUID().toString().replace("-", "");
        App app = new App();
        app.setSecret(passwordEncoder.encode(dto.getSecret()));
        app.setName(dto.getName());
        app.setKey(appKey);
        appRepository.save(app);

        // get a token
        AppTokenDTO appTokenDTO = securityService.getAppToken(
                new LoginRequestDTO(appKey, dto.getSecret())
        );
        return new AppRegisteredDTO(
                app.getName(),
                app.getKey(),
                appTokenDTO
        );
    }

    @Override
    public AppDTO getAppDetails() {
        Optional<App> optionalApp = appRepository.findFirstByKey(authFacade.getAuthentication().getName());
        if (optionalApp.isPresent()) {
            App app = optionalApp.get();
            return new AppDTO(
                    app.getName(),
                    app.getKey()
            );
        }
        throw new ApiException(
                ErrorCodes.RESOURCE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString()
        );
    }

    @Override
    public AppTokenDTO getAppToken(LoginRequestDTO dto) {
        App app = getAppByKey(dto.getKey(), true);
        matchSecretsOrReject(app.getSecret(), dto.getSecret());
        return securityService.getAppToken(dto);
    }

    private App getAppByKey(String key, boolean auth) {
        Optional<App> optionalApp = appRepository.findFirstByKey(key);
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        }
        if (auth) {
            throw new ApiException(
                    ErrorCodes.BAD_CREDENTIALS.getMessage(), HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.toString()
            );

        } else {
            throw new ApiException(
                    ErrorCodes.RESOURCE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString()
            );
        }
    }
    private void matchSecretsOrReject(String p0, String p1) {
         if (!passwordEncoder.matches(p1, p0)) {
             throw new ApiException(
                     ErrorCodes.BAD_CREDENTIALS.getMessage(), HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.toString()
             );
         }
    }
}
