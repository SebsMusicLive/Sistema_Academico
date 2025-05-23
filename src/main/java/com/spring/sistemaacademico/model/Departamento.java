package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoDepartamento;

    private String nombre;

    // Una facultad tiene muchos departamentos
    // La anotacion many to one indica que muchos departamentos pertenecen a una facultad
    @ManyToOne
    @JoinColumn(name = "codigo_facultad")
    private Facultad facultad;

    @OneToMany
    @JoinColumn(name = "codigo_departamento")
    private List<Docente> docentes;

    // Agregamos relacion con carrera
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Carrera> carreras;
}
