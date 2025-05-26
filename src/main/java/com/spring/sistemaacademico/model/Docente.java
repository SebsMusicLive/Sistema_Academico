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
public class Docente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoDocente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_departamento", nullable = false)
    private Departamento codigoDepartamento;

    private String titulo;
    private String especializacion;
    private int cargaHoraria;

    private int maxHoras; // ✅ nuevo atributo requerido

    @OneToMany(mappedBy = "docente")
    private List<AsignacionDocente> asignaciones;

    @OneToMany(mappedBy = "codigoDocente")
    private List<Curso> cursos;
}
