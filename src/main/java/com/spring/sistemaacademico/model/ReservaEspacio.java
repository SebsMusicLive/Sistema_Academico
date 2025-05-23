package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservaEspacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_espacio", nullable = false)
    private Espacio espacio;

    private String reservadoPor;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario")
    private Usuario usuario;

    private String estado; // Ej: "pendiente", "aprobada", "rechazada"

}
