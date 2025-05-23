package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionDocente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoAsignacionDocente;
    private Date fecha;
    private int cargaHoraria;

    /*
    ¿Que es optional?
    El optional indica que debe existir una relacion entre las tablas, es decir,
    que debe de existir un docente para poder guardar la relacion
    El optional es equivalente en un modelo de datos a una agregacion. Ya que en
    este ejemplo. Tanto Docente como Curso pueden existir sin la necesidad
    de existir una AsignacionDocente.


    ¿Que es JoinColumn?
    Especifica la columna que se va a usar como llave foranea, en este caso
    codigo_docente y codigo_curso, que son las llaves foraneas de la tabla

    ¿Que es nullable?
    Especifica que la llave foranea no puede ser nula, es decir, que no puede haber una

     */
    @ManyToOne (optional = false)
    @JoinColumn(name = "codigo_docente", nullable = false)
    private Docente docente;

    @ManyToOne (optional = false)
    @JoinColumn(name = "codigo_curso",nullable = false)
    private Curso curso;

}
