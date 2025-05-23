package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity


public class CursoHistorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoCurso estadoCurso;

    @ManyToOne
    @JoinColumn(name = "codigo_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "codigo_historial_academico", nullable = false)
    private HistorialAcademico historialAcademico;

    private float calificacionFinal;

}
