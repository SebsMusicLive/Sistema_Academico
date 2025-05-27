package com.spring.sistemaacademico.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_evaluacion;

    private String tipo;
    private float ponderacion;
    private Date fechaEvaluacion;

    @OneToOne
    @JoinColumn(name = "codigo_curso", nullable = false)
    private Curso codigoCurso;

    @OneToMany(mappedBy = "evaluacion")
    @JsonIgnoreProperties("evaluacion")
    private List<Calificacion> calificaciones;
}
