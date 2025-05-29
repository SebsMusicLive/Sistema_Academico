package com.spring.sistemaacademico.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_notificacion")
    private Long codigoNotificacion;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String mensaje;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @Column(name = "fecha_lectura")
    private LocalDateTime fechaLectura;

    @Column(nullable = false)
    private boolean leido = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_emisor", nullable = false)
    private Usuario emisor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_receptor", nullable = false)
    private Usuario receptor;
}