package com.spring.sistemaacademico.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoMatricula;
    private LocalDate fechaMatricula;


    @ManyToMany
    @JoinTable(name = "matricula_curso", joinColumns = @JoinColumn(name = "codigo_matricula"),
            inverseJoinColumns = @JoinColumn(name = "codigo_curso"))
    private List<Curso> codigoCurso;

    @OneToOne
    @JoinColumn (name = "codigo_estudiante")
    @JsonBackReference
    private Estudiante estudiante;

}
