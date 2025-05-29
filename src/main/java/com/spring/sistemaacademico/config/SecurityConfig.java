package com.spring.sistemaacademico.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())

                // --- AÑADIR CONFIGURACIÓN DE LOGOUT AQUÍ ---
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout") // Define la URL para el logout (puede ser POST por defecto)
                        // Si quieres que el logout funcione con GET (más simple para enlaces, pero POST es más seguro semánticamente)
                        // .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout", "GET"))
                        .logoutSuccessHandler((request, response, authentication) -> {
                            // Puedes enviar una respuesta JSON personalizada si lo deseas
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            response.getWriter().append("{\"message\": \"Logout exitoso desde el backend\"}");
                            response.getWriter().flush();
                        })
                        .invalidateHttpSession(true) // Invalida la sesión HTTP (importante)
                        .deleteCookies("JSESSIONID") // Opcional: elimina cookies de sesión si se usan
                        .permitAll() // Permite a todos acceder al endpoint de logout
                );

        return http.build();
    }
}