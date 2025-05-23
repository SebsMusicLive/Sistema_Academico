package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCurso;
    private String nombre;
    private int creditos;
    private int horasTeoricas;
    private int horasPracticas;
    @ManyToMany
    @JoinTable(
            name = "curso_estudiante",
            joinColumns = @JoinColumn(name = "codigo_curso"),
            inverseJoinColumns = @JoinColumn(name = "codigo_estudiante")
    )
    private List<Estudiante> estudiantes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_docente", nullable = false)
    private Docente codigoDocente;

    @ManyToMany
    @JoinTable(
            name = "curso_carrera", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name="codigoCurso"), // Clave foranea hacia curso
            inverseJoinColumns = @JoinColumn(name="codigo_carrera") // Clave foranea hacia carrera
    )
    private List<Carrera> codigoCarrera;

    @ManyToMany
    @JoinTable(
            name = "curso_prerequisito",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisito_id")
    )
    private List<Curso> prerequisitos;
    @OneToMany(mappedBy = "codigoCurso")
    private List<RecursoAcademico> recursosAcademicos;
    @ManyToMany
    @JoinTable(
            name = "curso_programa", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "codigo_curso"), // Clave foránea hacia Curso
            inverseJoinColumns = @JoinColumn(name = "codigo_programa") // Clave foránea hacia Programa
    )
    private List<Programa> codigoPrograma;

    @ManyToOne
    @JoinColumn(name = "codigo_semestre", nullable = false)
    private Semestre codigoSemestre;

    private String tipoCurso;
    private String contenido;
    private String objetivo;
    private String competencia;

    private int cuposDisponibles;

    // Agregando la relación con AsignacionDocente
    @OneToMany(mappedBy = "curso")
    private List<AsignacionDocente> asignaciones;

    // Agregando la relación con CursoHistorial
    @OneToMany(mappedBy = "curso")
    private List<CursoHistorial> cursoHistorial;

    // Agregando la relacion con Evaluacion
    @OneToOne(mappedBy = "codigoCurso")
    private Evaluacion evaluacion;

    // Agregando la relacion con Foro
    @OneToMany(mappedBy = "codigoCurso")
    private List<Foro> foros;

    // Agregando la relacion con Horario
    @OneToMany(mappedBy = "codigoCurso")
    private List<Horario> horarios;

    @ManyToOne
    @JoinColumn(name = "codigo_prerrequisito")
    private Curso codigoPrerrequisito;
    public Curso getCodigoPrerrequisito() {
        return codigoPrerrequisito;
    }


    public void setCodigoPrerrequisito(Curso codigoPrerrequisito) {
        this.codigoPrerrequisito = codigoPrerrequisito;
    }

    public int consultarCuposDisponibles() {
        return cuposDisponibles;
    }

    public boolean validarCupos() {
        return cuposDisponibles > 0;
    }

    public void actualizarCupos() {
        if (cuposDisponibles > 0) {
            cuposDisponibles--;
        } else {
            throw new IllegalStateException("No hay cupos disponibles");
        }
    }

    public List<Curso> generarReporteCursosAprobados() {
        List<Curso> cursosAprobados = new ArrayList<>();
        if (cursoHistorial != null) {
            for (CursoHistorial ch : cursoHistorial) {
                if (ch.getEstadoCurso() == EstadoCurso.APROBADO) {
                    cursosAprobados.add(ch.getCurso());
                }
            }
        }
        return cursosAprobados;
    }

    // Nuevos campos
    private int cupoMaximo;   // Límite de cupos
    private int cupoActual;



}

