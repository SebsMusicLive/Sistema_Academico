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
public class PrestamoRecurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPrestamoRecurso;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_recurso", nullable = false)
    private RecursoAcademico recurso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_usuario", nullable = false)
    private Usuario usuario;
}
