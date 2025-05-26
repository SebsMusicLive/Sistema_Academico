package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.ReservaEspacio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaEspacioService extends CrudService<ReservaEspacio, Long> {

    List<ReservaEspacio> findAll();

    ReservaEspacio save(ReservaEspacio reserva);

    ReservaEspacio update(ReservaEspacio reserva);

    Optional<ReservaEspacio> findById(Long id);

    void deleteById(Long id);

    void deleteAll();

    /**
     * Retorna todas las reservas asociadas a un código de espacio
     */
    List<ReservaEspacio> findByEspacioCodigoEspacio(Long codigoEspacio);

    /**
     * Verifica si hay traslape en una franja horaria con reservas existentes
     */
    boolean existeTraslape(Long espacioId, LocalDateTime inicio, LocalDateTime fin);

    /**
     * Retorna las reservas hechas por un usuario
     */
    List<ReservaEspacio> findByUsuario(Long codigoUsuario);

    /**
     * Retorna las reservas por estado: "pendiente", "aprobada", etc.
     */
    List<ReservaEspacio> findByEstado(String estado);
}
