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

/*
La anotacion @JoinTable se utiliza para crear y personalizar una tabla intermedia
en una relacion de muchos a muchos entre dos entidades.

 */

public class Asistencia {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long codigoAsistencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_estudiante", nullable = false)
    private Estudiante codigoEstudiante;


    @ManyToOne (optional = false)
    @JoinColumn(name = "codigo_curso",nullable = false )
    private Curso codigoCurso;

    private Date fechaAsistencia;
    private boolean asistencia;

}
