package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)


public class Docente extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoDocente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_departamento", nullable = false)
    private Departamento codigoDepartamento;


    private String titulo;
    private String especializacion;
    private int cargaHoraria;

    // Agregando la relación con AsignacionDocente
    @OneToMany(mappedBy = "docente") // Relación manejada por el campo 'docente' en AsignacionDocente
    private List<AsignacionDocente> asignaciones;

    // Agregando la relación con Curso
    @OneToMany(mappedBy = "codigoDocente")
    private List<Curso> cursos;

}
