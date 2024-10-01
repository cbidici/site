package com.cbidici.site.user;

import com.cbidici.site.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository repository;

  @Override
  public SpringUser loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
        .map(SpringUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("Unknown user "+ username));
  }

}
