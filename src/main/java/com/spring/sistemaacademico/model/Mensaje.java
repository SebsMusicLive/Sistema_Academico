package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoMensaje;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    private boolean leido;

    @ManyToOne
    @JoinColumn(name = "codigo_chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "codigo_emisor")
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "codigo_receptor")
    private Usuario receptor;
}
