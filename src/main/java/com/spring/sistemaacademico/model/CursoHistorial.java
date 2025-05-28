package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "curso_historial")
public class CursoHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_curso_historial", nullable = false)
    private Long codigoCursoHistorial;

    @Column(name = "calificacion_final", nullable = false)
    private float calificacionFinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_curso")
    private EstadoCurso estadoCurso;

    @ManyToOne
    @JoinColumn(name = "codigo_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "codigo_historial_academico", nullable = false)
    private HistorialAcademico historialAcademico;
}