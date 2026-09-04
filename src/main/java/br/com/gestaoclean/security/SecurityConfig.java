package br.com.gestaoclean.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/clientes/**",
                                "/agendamentos/**")
                        .hasAnyRole("ADMIN", "FUNCIONARIO")

                        .requestMatchers(HttpMethod.POST,
                                "/clientes/**",
                                "/agendamentos/**")
                        .hasAnyRole("ADMIN", "FUNCIONARIO")

                        .requestMatchers(HttpMethod.PUT,
                                "/clientes/**",
                                "/agendamentos/**")
                        .hasAnyRole("ADMIN", "FUNCIONARIO")

                        .requestMatchers(HttpMethod.DELETE,
                                "/clientes/**",
                                "/agendamentos/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PATCH,
                                "/agendamentos/*/cancelar")
                        .hasAnyRole("ADMIN", "FUNCIONARIO")

                        .requestMatchers("/financeiro/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/usuarios/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}