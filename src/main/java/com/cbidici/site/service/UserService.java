package com.cbidici.site.service;

import com.cbidici.site.entity.Role;
import com.cbidici.site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
        .map(u -> User.builder()
            .username(u.getUsername())
            .password(u.getPassword())
            .authorities(u.getRoles().stream().map(Role::getCode).toList().toArray(new String[0]))
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
        )
        .orElseThrow(() -> new UsernameNotFoundException("Unknown user "+ username));
  }
}
