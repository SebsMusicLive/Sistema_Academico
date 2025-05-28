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
    @Column(name = "codigo_usuario")
    private Long codigoUsuario;

    // Datos personales
    @Column(name = "persona_documento")
    private String personaDocumento;

    private String nombre;
    private String clave;
    private String correo;

    // Rol del usuario
    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_rol", nullable = false)
    private Rol rol;

    @Column(name = "sesion_activa", nullable = false)
    private boolean sesionActiva;

    // Chats en los que participa
    @OneToMany(mappedBy = "codigoUsuario1")
    private List<Chat> chatsComoUsuario1;

    @OneToMany(mappedBy = "codigoUsuario2")
    private List<Chat> chatsComoUsuario2;

    // Mensajes enviados y recibidos
    @OneToMany(mappedBy = "emisor")
    private List<Mensaje> mensajesEnviados;

    @OneToMany(mappedBy = "receptor")
    private List<Mensaje> mensajesRecibidos;

    // Participación en foros
    @ManyToMany(mappedBy = "usuarios")
    private List<Foro> foros;

    // Notificaciones enviadas y recibidas
    @OneToMany(mappedBy = "emisor")
    private List<Notificacion> notificacionesEnviadas;

    @OneToMany(mappedBy = "receptor")
    private List<Notificacion> notificacionesRecibidas;
}
