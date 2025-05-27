package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCalificacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_estudiante", nullable = false) // convenciones SQL
    @JsonIgnoreProperties("calificaciones") // evita ciclos si Estudiante tiene lista de calificaciones
    private Estudiante estudiante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_evaluacion", nullable = false)
    @JsonIgnoreProperties("calificaciones") // evita ciclos si Evaluacion tiene lista de calificaciones
    private Evaluacion evaluacion;

    @Min(value = 0, message = "La nota mínima es 0")
    @Max(value = 5, message = "La nota máxima es 5")
    private float nota;
}

