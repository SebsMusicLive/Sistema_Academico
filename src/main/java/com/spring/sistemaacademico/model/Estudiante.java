package com.spring.sistemaacademico.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Estudiante extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEstudiante;

    @ManyToOne
    @JoinColumn(name = "codigo_carrera")
    private Carrera codigoCarrera;

    private int semestre;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("estudiante")
    private List<Calificacion> calificaciones;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private HistorialAcademico historialAcademico;

    @OneToOne(mappedBy = "estudiante", fetch = FetchType.LAZY)
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
