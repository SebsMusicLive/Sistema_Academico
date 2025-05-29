package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Persona {

    @NotNull
    private Long documento;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotBlank
    private String nombre;

    @Email
    private String correo;

    @NotNull
    private String telefono;

    private String direccion;

    private LocalDate fechaNacimiento;
}
