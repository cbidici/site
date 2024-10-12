package com.cbidici.site.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.cbidici.site.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

@ExtendWith(MockitoExtension.class)
@Profile("unit")
class UserServiceTest {

  @Mock private UserRepository userRepository;
  @InjectMocks private UserService userService;

  @Test
  void testLoadsByUserName() {
    var user = Mockito.mock(User.class);
    var role1 = Mockito.mock(Role.class);
    when(userRepository.findByUsername("x")).thenReturn(Optional.of(user));
    when(user.getUsername()).thenReturn("x");
    when(user.getPassword()).thenReturn("p");
    when(user.getRoles()).thenReturn(List.of(role1));
    when(role1.getCode()).thenReturn("A");

    var result = userService.loadUserByUsername("x");

    assertThat(result.getUsername()).isEqualTo("x");
    assertThat(result.getAuthorities()).extracting("authority").containsExactly("A");
  }

}