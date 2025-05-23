package com.spring.sistemaacademico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Espacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEspacio;

    private String nombre;
    private String tipo; // Aula, laboratorio, etc.
    private int capacidad;

    @OneToMany(mappedBy = "espacio", cascade = CascadeType.ALL)
    private List<ReservaEspacio> reservas;
}
