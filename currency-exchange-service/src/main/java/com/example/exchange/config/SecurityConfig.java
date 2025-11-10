package com.example.exchange.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.CollectionUtils;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .anyRequest().authenticated())
        .oauth2ResourceServer(oauth -> oauth
            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

    return http.build();
  }

  private JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
    scopeConverter.setAuthorityPrefix("SCOPE_");
    scopeConverter.setAuthoritiesClaimName("scope");

    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(jwt -> {
      Set<GrantedAuthority> authorities = new HashSet<>(scopeConverter.convert(jwt));
      authorities.addAll(extractRealmRoles(jwt));
      authorities.addAll(extractResourceRoles(jwt));
      return authorities;
    });
    return converter;
  }

  private Collection<? extends GrantedAuthority> extractRealmRoles(Jwt jwt) {
    Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
    if (realmAccess == null) {
      return Collections.emptySet();
    }
    Object rolesObj = realmAccess.get("roles");
    if (!(rolesObj instanceof Collection<?> roles) || CollectionUtils.isEmpty(roles)) {
      return Collections.emptySet();
    }
    Set<GrantedAuthority> authorities = new HashSet<>();
    for (Object role : roles) {
      if (role != null) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()));
      }
    }
    return authorities;
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
    if (resourceAccess == null || resourceAccess.isEmpty()) {
      return Collections.emptySet();
    }
    Set<GrantedAuthority> authorities = new HashSet<>();
    for (Object clientEntry : resourceAccess.values()) {
      if (!(clientEntry instanceof Map<?, ?> clientMap)) {
        continue;
      }
      Object rolesObj = clientMap.get("roles");
      if (!(rolesObj instanceof Collection<?> roles) || CollectionUtils.isEmpty(roles)) {
        continue;
      }
      for (Object role : roles) {
        if (role != null) {
          authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()));
        }
      }
    }
    return authorities;
  }
}
