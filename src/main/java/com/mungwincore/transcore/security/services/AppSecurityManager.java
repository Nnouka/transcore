package com.mungwincore.transcore.security.services;

import com.mungwincore.transcore.domain.dtos.request.LoginRequestDTO;
import com.mungwincore.transcore.domain.models.App;
import com.mungwincore.transcore.security.SecurityCon;
import com.mungwincore.transcore.security.SecurityService;
import com.mungwincore.transcore.security.dtos.AppTokenDTO;
import com.mungwincore.transcore.security.props.AuthServerProps;
import com.mungwincore.transcore.security.props.ResourceServerProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class AppSecurityManager implements SecurityService {
    private AuthorizationServerTokenServices tokenServices;
    private AuthServerProps authServerProps;
    private ResourceServerProps resourceServerProps;
    private SecurityCon securityCon;
    Logger logger = LoggerFactory.getLogger(AppSecurityManager.class);

    @Autowired
    public void setTokenServices(@Qualifier("defaultAuthorizationServerTokenServices") AuthorizationServerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }
    @Autowired
    public void setAuthServerProps(AuthServerProps authServerProps) {
        this.authServerProps = authServerProps;
    }

    @Autowired
    public void setSecurityCon(SecurityCon securityCon) {
        this.securityCon = securityCon;
    }

    @Autowired
    public void setResourceServerProps(ResourceServerProps resourceServerProps) {
        this.resourceServerProps = resourceServerProps;
    }

    @Override
    public AppTokenDTO getAppToken(LoginRequestDTO dto) {
        Map<String, String> params = new HashMap<>();
        params.put("username", dto.getKey());
        params.put("password", dto.getSecret());
        params.put("grant_type", "password");
        OAuth2Request auth2Request = new OAuth2Request(
                params, authServerProps.getClientId(),
                getGrantedAuthority(),
                true,
                null,
                Collections.singleton(resourceServerProps.getId()),
                null,
                null,
                null
        );

        SecurityContext securityContext = securityCon.createSecurityContext(dto.getKey());

        OAuth2Authentication authentication = new OAuth2Authentication(
               auth2Request, securityContext.getAuthentication()
        );

        OAuth2AccessToken auth2AccessToken = tokenServices.createAccessToken(authentication);
        return new AppTokenDTO(
                auth2AccessToken.getValue(),
                auth2AccessToken.getTokenType()
        );
    }

    @Override
    public AppTokenDTO getAppTokenWithProvided(String p) {

        SecurityContext securityContext = securityCon.createSecurityContext2(p);

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        OAuth2Request auth2Request = new OAuth2Request(
                params, authServerProps.getClientId(),
                getGrantedAuthority(),
                true,
                null,
                Collections.singleton(resourceServerProps.getId()),
                null,
                null,
                null
        );

        OAuth2Authentication authentication = new OAuth2Authentication(
                auth2Request, securityContext.getAuthentication()
        );

        OAuth2AccessToken auth2AccessToken = tokenServices.createAccessToken(authentication);
        return new AppTokenDTO(
                auth2AccessToken.getValue(),
                auth2AccessToken.getTokenType()
        );
    }

    private List<GrantedAuthority> getGrantedAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("App"));
    }
}
