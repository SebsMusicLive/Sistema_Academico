package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCalificacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigoEstudiante",nullable = false)
    private Estudiante codigoEstudiante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_evaluacion",nullable = false)
    private Evaluacion codigo_evaluacion;

    private float nota;

}


