package com.me.post_share_api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                // Endpoint públicos
                                .requestMatchers(HttpMethod.GET, "/api/users/**", "/api/posts/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()

                                // Endpoint protegidos
                                .requestMatchers(HttpMethod.POST, "/api/posts/**")
                                .hasAnyRole("USER", "MODERATOR", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/users/**", "/api/posts/**")
                                .hasAnyRole("USER", "MODERATOR", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/posts/**")
                                .hasAnyRole("USER", "MODERATOR", "ADMIN")

                                // Endpoint importantes
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                                .anyRequest().authenticated())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
