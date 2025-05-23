package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecursoAcademico {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long codigoRecursoAcademico;
    private String titulo;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "codigo_curso", nullable = false)
    private Curso codigoCurso;

    private String nombreRecursoAcademico;
    private boolean disponible;
    private String ubicacion;
    private String tipoEspacio;
}
