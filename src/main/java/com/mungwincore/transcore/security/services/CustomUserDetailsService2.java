package com.mungwincore.transcore.security.services;

import com.mungwincore.transcore.domain.models.App;
import com.mungwincore.transcore.domain.repositories.AppRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("userDetailsService2")
@Transactional
public class CustomUserDetailsService2 implements UserDetailsService {
    private AppRepository repository;
    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    public void setRepository(AppRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<App> appList = repository.findByTokens(s);
        logger.info(appList.toString());
        if (appList.size() == 1) {
            App app = appList.get(0);
            logger.info("Authenticated App: {}", app.getId());
            return new org.springframework.security.core.userdetails.User(
                    app.getKey(), app.getSecret(), true, true, true, true,
                     Collections.singletonList(new SimpleGrantedAuthority("App"))
            );
        }else {
            throw new UsernameNotFoundException("App: " + s + " not found");
        }
    }
}
