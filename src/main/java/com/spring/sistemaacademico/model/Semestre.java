package com.spring.sistemaacademico.model;

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
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoSemestre;
    private int numeroSemestre;
    private Date fechaInicio;
    private Date fechaFin;

    // Un semestre puede tener varios cursos, por lo que la relación debe ser @OneToMany
    // Cambio de @OneToOne a @OneToMany para permitir múltiples cursos en un semestre
    @OneToMany(mappedBy = "codigoSemestre")
    private List<Curso> codigoCurso;
}
