package com.spring.sistemaacademico.services;

import com.spring.sistemaacademico.model.Facultad;
import java.util.List;

public interface FacultadService extends CrudService<Facultad, Long> {
    List<Facultad> findByNombre(String nombre);

    Facultad agregarFacultad(Facultad facultad) throws Exception;

    Facultad modificarFacultad(Facultad facultad) throws Exception;

    void eliminarFacultad(Long id) throws Exception;

    void crearDepartamento(Long facultadId, Long departamentoId) throws Exception;

    String generarReporteEstadistico(Long facultadId);
}

