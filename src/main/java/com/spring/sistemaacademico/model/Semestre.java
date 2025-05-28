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
    private Long codigoSemestre;  // <- Este es el ID correcto

    private int numeroSemestre;
    private Date fechaInicio;
    private Date fechaFin;

    @OneToMany(mappedBy = "codigoSemestre")
    private List<Curso> codigoCurso;
}