package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// El Data es un atajo de Lombok que genera los métodos getter, setter, equals, hashcode y toString
@Data
// Anotaciones para indicar un constructor vacio y uno lleno
@NoArgsConstructor
@AllArgsConstructor
// Indica que la clase es una entidad, es decir, una tabla de la base de datos
@Entity
public class Carrera {

    // Indica que el identificador de la tabla es codigoCarrera
    @Id
    @Column(name = "codigo_carrera")
    private Long codigoCarrera;


    private String nombreCarrera;
    private int duracion;

    // Indicamos que una carrera no puede existir sin estar asociada a un departamento
    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_departamento", nullable = false)
    private Departamento codigoDepartamento;

    // Agregando la relación con estudiante
    @OneToMany(mappedBy = "codigoCarrera")
    private List<Estudiante> estudiantes;
}
