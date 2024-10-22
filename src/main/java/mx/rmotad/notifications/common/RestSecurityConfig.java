package mx.rmotad.notifications.common;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.ignoringRequestMatchers("/**")
        )
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(
            (authz) -> authz
                .anyRequest().permitAll()

        )
        .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.addAllowedOrigin("*");
    configuration.setAllowedOrigins(List.of("*")); // Permitir ciertos orígenes
    configuration.setAllowedMethods(
        Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
//    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Cabeceras permitidas
//    configuration.setAllowCredentials(true); // Permitir credenciales
//    configuration.setMaxAge(3600L); // Tiempo de cacheo en segundos

    configuration.setAllowedHeaders(Arrays.asList("*")); // Permitir todas las cabeceras
    configuration.setAllowCredentials(false); // Permitir credenciales
    configuration.setMaxAge(3600L); // Tiempo de cacheo en segundos

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Aplicar configuración a todas las rutas
    return source;
  }
}
