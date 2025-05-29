package com.spring.sistemaacademico.config; // Asegúrate que el nombre del paquete sea el correcto

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica CORS a todas las rutas bajo /api
                        .allowedOrigins("http://localhost:4321") // Permite peticiones desde tu frontend Astro
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Permite todas las cabeceras
                        .allowCredentials(true); // Permite credenciales (cookies, etc.), si las usas
            }
        };
    }
}