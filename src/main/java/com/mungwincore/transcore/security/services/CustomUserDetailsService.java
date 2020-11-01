package com.mungwincore.transcore.security.services;


import com.mungwincore.transcore.domain.models.App;
import com.mungwincore.transcore.domain.repositories.AppRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
  private AppRepository repository;
  private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

  @Autowired
  public void setRepository(AppRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String key) throws UsernameNotFoundException {
    Optional<App> optionalUser = repository.findFirstByKey(key);
    if (optionalUser.isPresent()) {
      App user = optionalUser.get();
      logger.info("Authenticated App: {}", user.getId());
      return new org.springframework.security.core.userdetails.User(
        user.getKey(), user.getSecret(), true, true, true, true,
              /*getAuthorities(user.getRoles())*/ getGrantedAuthority()
      );
    } else {
      throw new UsernameNotFoundException("App: " + key + " not found");
    }
  }

  /*private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }
  private List<String> getPrivileges(List<Role> roles) {
    List<Privilege> privileges = new ArrayList<>();
    for (Role role: roles) {
      privileges.addAll(role.getPrivileges());
    }
    return privileges.stream().distinct().map(
      Privilege::getName
    ).collect(Collectors.toList());
  }
  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    return privileges.stream().map(
      SimpleGrantedAuthority::new
    ).collect(Collectors.toList());
  }*/

  private List<GrantedAuthority> getGrantedAuthority() {
    return Collections.singletonList(new SimpleGrantedAuthority("App"));
  }
}
