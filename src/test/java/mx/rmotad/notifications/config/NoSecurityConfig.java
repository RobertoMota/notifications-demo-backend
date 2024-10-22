package mx.rmotad.notifications.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
  public class NoSecurityConfig {

    @Bean
    public SecurityFilterChain disableSecurity(HttpSecurity http) throws Exception {
      return http
          .csrf(csrf -> csrf.ignoringRequestMatchers("/**")
          )
          .authorizeHttpRequests(
              (auth) -> auth
                  .anyRequest().permitAll()

          ).build();
    }
  }