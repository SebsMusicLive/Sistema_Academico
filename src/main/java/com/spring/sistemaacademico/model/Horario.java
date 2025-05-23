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
public class Horario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long codigoHorario;

    @ManyToOne
    @JoinColumn(name = "codigo_curso")
    private Curso codigoCurso;

    private Date horaInicio;
    private Date horaFin;
    private String tipoSesion;

}
