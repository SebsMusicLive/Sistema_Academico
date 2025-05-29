package com.spring.sistemaacademico.config; // Asegúrate que el paquete sea el correcto

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
                                .ignoringRequestMatchers("/api/**") // Deshabilita CSRF para tus APIs (común para APIs stateless)
                        // Si tu login es tradicional (no JWT), podrías necesitar CSRF.
                        // Por ahora, para simplificar, lo desactivamos para /api/.
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/login").permitAll() // Permite acceso a /api/auth/login sin autenticación
                        .requestMatchers("/api/public/**").permitAll() // Ejemplo: si tienes otros endpoints públicos
                        .anyRequest().authenticated() // Cualquier otra petición requiere autenticación
                )
                // Si vas a manejar tu propio login y no usar el formLogin de Spring Security para esta API:
                .formLogin(formLogin -> formLogin.disable()) // Deshabilita el formLogin por defecto si manejas login vía API
                .httpBasic(httpBasic -> httpBasic.disable()); // Deshabilita HTTP Basic si no lo usas

        // Para APIs stateless (común con JWT), usualmente se configura así:
        // http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}