package com.spring.sistemaacademico.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Foro {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long codigoForo;

    @ManyToOne
    @JoinColumn(name = "codigo_curso")
    private Curso codigoCurso;

    private String titulo;
    private String descripcion;
    private Date fechaCreacion;

    @ManyToMany
    @JoinTable(
            name = "foro_usuario", //Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "codigo_foro"), // Clave foranea hacia foro
            inverseJoinColumns = @JoinColumn(name = "codigo_usuario") // Clave foranea hacia usuario
    )
    private List<Usuario> codigoUsuario;
}
