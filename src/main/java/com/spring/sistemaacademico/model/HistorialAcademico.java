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
public class HistorialAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoHistorialAcademico;

    private float promedioGeneral;

    @OneToOne
    @JoinColumn(name = "codigo_estudiante", nullable = false)
    private Estudiante estudiante;

    @OneToMany(mappedBy = "historialAcademico")
    private List<CursoHistorial> cursoHistorial;
}
