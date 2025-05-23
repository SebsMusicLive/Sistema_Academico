package com.spring.sistemaacademico.services;

import sistemaAcademico.model.ReservaEspacio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaEspacioService extends CrudService<ReservaEspacio, Long> {

    List<ReservaEspacio> findAll();

    ReservaEspacio save(ReservaEspacio reserva);

    ReservaEspacio update(ReservaEspacio reserva);

    Optional<ReservaEspacio> findById(Long id);  // FALTABA

    void deleteById(Long id);

    void deleteAll();

    List<ReservaEspacio> findByEspacioId(Long codigoEspacio);

    boolean existeTraslape(Long espacioId, LocalDateTime inicio, LocalDateTime fin);

    List<ReservaEspacio> findByEspacioCodigoEspacio(Long codigoEspacio);

    // OPCIONALES: agregar solo si los usarás desde el controlador
    List<ReservaEspacio> findByUsuario(Long codigoUsuario);

    List<ReservaEspacio> findByEstado(String estado);
  

}
