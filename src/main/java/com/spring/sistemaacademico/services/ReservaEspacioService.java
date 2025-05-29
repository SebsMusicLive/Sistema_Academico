package com.spring.sistemaacademico.services;

import java.util.List;

import com.spring.sistemaacademico.model.ReservaEspacio;

public interface ReservaEspacioService {
    List<ReservaEspacio> findAll();
    ReservaEspacio findById(Long id);
    ReservaEspacio save(ReservaEspacio reservaEspacio);
    void delete(Long id);

    // Solo la versión correcta:
    List<ReservaEspacio> findByUsuarioCodigoUsuario(Long codigoUsuario);
}