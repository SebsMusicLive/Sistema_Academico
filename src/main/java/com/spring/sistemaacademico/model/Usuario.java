package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoUsuario;

    // Datos personales
    private String personaDocumento;
    private String nombre;
    private String clave;

    // Relación con el rol (Estudiante, Docente, Administración, etc.)
    @ManyToOne
    @JoinColumn(name = "codigo_rol")
    private Rol rol;

    // Indica si el usuario tiene una sesión activa
    private boolean sesionActiva;

    // Relación con los chats en los que participa como usuario1
    @OneToMany(mappedBy = "codigoUsuario1")
    private List<Chat> chatsComoUsuario1;

    // Relación con los chats en los que participa como usuario2
    @OneToMany(mappedBy = "codigoUsuario2")
    private List<Chat> chatsComoUsuario2;

    // Relación con los mensajes enviados por este usuario
    @OneToMany(mappedBy = "emisor")
    private List<Mensaje> mensajesEnviados;

    // Relación con los mensajes recibidos por este usuario
    @OneToMany(mappedBy = "receptor")
    private List<Mensaje> mensajesRecibidos;

    // Participación en foros (relación muchos a muchos)
    @ManyToMany(mappedBy = "codigoUsuario")
    private List<Foro> foros;

    // Notificaciones enviadas por este usuario
    @OneToMany(mappedBy = "usuario")
    private List<Notificacion> notificacionesEnviadas;

    @OneToMany(mappedBy = "usuario")  // Cambiar "destinatario" por "usuario"
    private List<Notificacion> notificacionesRecibidas;
}
