package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPrograma;
    private String nombre;
    private String descripcion;
    @ManyToMany
    @JoinTable(
            name = "programa_carrera",
            joinColumns = @JoinColumn(name = "codigo_programa"),
            inverseJoinColumns = @JoinColumn(name = "codigo_carrera")
    )
    private List<Carrera> codigoCarrera;

    // Agregando la relación con Curso
    @ManyToMany(mappedBy = "codigoPrograma")
    private List<Curso> cursos;

}
