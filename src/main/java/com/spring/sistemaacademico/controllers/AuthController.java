package com.spring.sistemaacademico.controllers;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Data // O @Getter y @Setter aquí mismo
    public static class LoginRequest { // Házla estática y pública
        private String codigo;
        private String documento;
        private String contrasena;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Intento de login recibido:");
        System.out.println("Código: " + loginRequest.getCodigo());       // Verifica estos valores
        System.out.println("Documento: " + loginRequest.getDocumento()); // Verifica estos valores
        System.out.println("Contraseña: " + loginRequest.getContrasena()); // Verifica estos valores

        if ("1152196".equals(loginRequest.getCodigo()) &&
                "1005028827".equals(loginRequest.getDocumento()) &&
                "Johansebas134625@".equals(loginRequest.getContrasena())) {
            return ResponseEntity.ok().body(Map.of(
                    "message", "¡Inicio de sesión exitoso para " + loginRequest.getCodigo() + "!",
                    "token", "este-es-un-token-dummy-para-" + loginRequest.getCodigo()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas. Inténtalo de nuevo."));
        }
    }
}