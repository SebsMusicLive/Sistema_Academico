package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoNota;

    @ManyToOne
    @JoinColumn(name = "codigo_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "codigo_evaluacion", nullable = false)
    private Evaluacion evaluacion;

    private float calificacion;

    private String comentario;

    private boolean estado;
}
