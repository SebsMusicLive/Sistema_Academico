package com.spring.sistemaacademico.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
  Explicacion de etiquetas

    @Entity: Indica que la clase es una entidad, es decir, una tabla de la base de datos
    @Data: Anotación de Lombok que genera los métodos getter, setter, equals, hashcode y toString
    @NoArgsConstructor: Anotación de Lombok que genera un constructor sin argumentos
    @AllArgsConstructor: Anotación de Lombok que genera un constructor con todos los argumentos

    @Id: Indica que el atributo es la clave primaria de la tabla
    @GeneratedValue: Indica que el valor de la clave primaria se generará automáticamente
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Estudiante extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEstudiante;

    // @ManyToOne: Indica que la relación es de muchos a uno
    @ManyToOne
    @JoinColumn(name = "codigo_carrera")
    private Carrera codigoCarrera;

    private int semestre;

    @OneToMany(mappedBy = "codigoEstudiante", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Calificacion> calificaciones;


    @OneToOne(mappedBy = "codigoEstudiante", cascade = CascadeType.ALL)
    private HistorialAcademico historialAcademico;

    // Agregando la relacion con HistorialAcademico
//    @OneToMany(mappedBy = "codigoEstudiante")
//    @JsonManagedReference
//    private List<HistorialAcademico> historialAcademico;


    // Agregando la relación con Matricula
    @OneToOne(mappedBy = "estudiante", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Matricula matricula;



    public List<Curso> generarReporteCursosAprobados() {
        List<Curso> cursosAprobados = new ArrayList<>();
        if (historialAcademico != null && historialAcademico.getCursoHistorial() != null) {
            for (CursoHistorial ch : historialAcademico.getCursoHistorial()) {
                if (ch.getEstadoCurso() == EstadoCurso.APROBADO) {
                    cursosAprobados.add(ch.getCurso());
                }
            }
        }
        return cursosAprobados;
    }


}
