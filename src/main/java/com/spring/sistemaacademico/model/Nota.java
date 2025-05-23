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
    private Estudiante estudiante; // Relación con la entidad Estudiante

    @ManyToOne
    @JoinColumn(name = "codigo_evaluacion", nullable = false)
    private Evaluacion evaluacion; // Relación con la entidad Evaluacion

    private float calificacion; // La calificación que el estudiante ha obtenido

    private String comentario; // Comentarios adicionales sobre la nota

    private boolean estado; // Si la nota es válida o no (por ejemplo, si ha sido modificada)
}