package com.cbidici.site.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SpringUser extends org.springframework.security.core.userdetails.User {

  private User user;

  public SpringUser(User user) {
    super(
        user.getUsername(),
        user.getPassword(),
        true,
        true,
        true,
        true,
        user.getRoles().stream().map(Role::getCode).map(SimpleGrantedAuthority::new).toList()
    );
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
