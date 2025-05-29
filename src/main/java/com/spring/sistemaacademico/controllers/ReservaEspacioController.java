package com.spring.sistemaacademico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sistemaacademico.model.ReservaEspacio;
import com.spring.sistemaacademico.services.ReservaEspacioService;

@RestController
@RequestMapping("/api/reserva-espacio")
public class ReservaEspacioController {

    @Autowired
    private ReservaEspacioService reservaEspacioService;

    @GetMapping
    public List<ReservaEspacio> getAll() {
        return reservaEspacioService.findAll();
    }

    @GetMapping("/{id}")
    public ReservaEspacio getById(@PathVariable Long id) {
        return reservaEspacioService.findById(id);
    }

    // Cambio importante: usamos findByUsuarioCodigoUsuario
    @GetMapping("/usuario/{codigoUsuario}")
    public List<ReservaEspacio> getByUsuarioCodigoUsuario(@PathVariable Long codigoUsuario) {
        return reservaEspacioService.findByUsuarioCodigoUsuario(codigoUsuario);
    }
}
